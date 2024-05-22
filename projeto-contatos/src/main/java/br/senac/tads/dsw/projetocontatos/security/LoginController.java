/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos.security;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String generateToken(Usuario usuario) {
        Instant now = Instant.now();
        String scope = usuario.getPermissoes().stream()
                .map(p -> p.getNome())
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(Duration.ofMinutes(3)))
                .subject(usuario.getUsername())
                .claim("nome", usuario.getNome())
                .claim("scope", scope).build();
        JwtEncoderParameters encoderParameters
                = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return jwtEncoder.encode(encoderParameters).getTokenValue();
    }

    @PostMapping
    public ResponseEntity<?> fazerLogin(@RequestBody Credencial credencial) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        credencial.username(),
                        credencial.senha()));
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Usuario usuario = (Usuario) auth.getPrincipal();
        // Força uso do bcrypt para gerar hash da senha {noop}
        if (usuario.getHashSenha().startsWith("{noop}")) {
            String hashBcrypt = passwordEncoder.encode(credencial.senha());
            usuario.setHashSenha(hashBcrypt);
            usuarioRepository.save(usuario);
        }
        String jwt = generateToken(usuario);
        return ResponseEntity.ok(jwt);
    }

    public record Credencial(String username, String senha) {

    }

}
