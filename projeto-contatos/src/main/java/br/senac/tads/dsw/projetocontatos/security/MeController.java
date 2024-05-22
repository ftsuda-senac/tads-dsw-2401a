/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos.security;

import br.senac.tads.dsw.projetocontatos.security.Usuario;
import br.senac.tads.dsw.projetocontatos.security.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/me")
    @SecurityRequirement(name = "bearer-jwt")
    public UsuarioDto obterDados(Authentication auth) {
        Jwt jwt = (Jwt) auth.getPrincipal();
        String username = jwt.getSubject();
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(username);
        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getHashSenha());
    }
    
    @GetMapping("/operador")
    @PreAuthorize("hasAuthority('SCOPE_OPERADOR')")
    @SecurityRequirement(name = "bearer-jwt")
    public UsuarioDto obterDadosOperador(Authentication auth) {
        Jwt jwt = (Jwt) auth.getPrincipal();
        String username = jwt.getSubject();
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(username);
        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getHashSenha());
    }
    
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @SecurityRequirement(name = "bearer-jwt")
    public UsuarioDto obterDadosAdmin(Authentication auth) {
        Jwt jwt = (Jwt) auth.getPrincipal();
        String username = jwt.getSubject();
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(username);
        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getHashSenha());
    }
    
    @GetMapping("/deus")
    @PreAuthorize("hasAuthority('SCOPE_DEUS')")
    @SecurityRequirement(name = "bearer-jwt")
    public UsuarioDto obterDadosDeus(Authentication auth) {
        Jwt jwt = (Jwt) auth.getPrincipal();
        String username = jwt.getSubject();
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(username);
        return new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getHashSenha());
    }
    
    
    public record UsuarioDto(int id, String nome, String email, String hashSenha) {
        
    }
    
            
}
