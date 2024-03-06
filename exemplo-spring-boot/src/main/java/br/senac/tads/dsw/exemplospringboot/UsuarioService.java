/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplospringboot;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Optional<UsuarioDto> findById(int id) {
        
        Optional<UsuarioEntity> optEntity = repository.findById(id);
        if (optEntity.isEmpty()) {
            return Optional.empty();
        }
        UsuarioEntity entity = optEntity.get();
        UsuarioDto dto = new UsuarioDto();
        dto.setId(id);
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setStatus(entity.getStatus());
        dto.setPerfil(entity.getPerfil());
     
        return Optional.of(dto);
        
        // return Optional.ofNullable(repository.findById(id).map(ent -> {
        // criar/setar dto
        // }));
    }
    
    @Transactional
    public void save(UsuarioDto dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        
        if (dto.getId() == null && dto.getSenha() != null && dto.getSenha().length() > 0) {
            entity.setHashSenha(passwordEncoder.encode(dto.getSenha()));
        }
        
        entity.setStatus(dto.getStatus());
        entity.setPerfil(dto.getPerfil());
        repository.save(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UsuarioEntity> optEntity = repository.findByEmail(email);
        if (optEntity.isEmpty()) {
            throw new UsernameNotFoundException("email não cadastrado");
        }
        return optEntity.get();
    }
    
}
