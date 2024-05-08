/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class ContatoServiceBdImpl implements ContatoService {
    
    @Autowired
    private ContatoRepository repository;
    
    @Autowired
    private InteresseRepository interesseRepository;
    
    private ContatoDto convertEntityToDto(Contato entity) {
        ContatoDto dto = new ContatoDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        return dto;
    }

    @Override
    public List<ContatoDto> findAll() {
        List<Contato> entities = repository.findAll();
        List<ContatoDto> resultado = new ArrayList<>();
        for (Contato entity : entities) {
            resultado.add(convertEntityToDto(entity));
        }
        return resultado;
    }
    
    @Override
    public ContatoDto findByIdOriginal(int id) {
        Optional<Contato> optEntity = repository.findById(id);
        if (optEntity.isEmpty()) {
            return null;
        }
        Contato entity = optEntity.get();
        return convertEntityToDto(entity);
    }

    @Override
    public Optional<ContatoDto> findById(int id) {
//        Optional<Contato> optEntity = repository.findById(id);
//        if (optEntity.isEmpty()) {
//            return Optional.empty();
//        }
//        Contato entity = optEntity.get();
//        return Optional.of(convertEntityToDto(entity));
        return repository.findById(id).map(entity -> convertEntityToDto(entity));
    }
    
    private void save(ContatoDto dto) {
        Contato entity = new Contato();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setDataNascimento(dto.getDataNascimento());
        final Set<Interesse> interesses = new HashSet<>();
        interesseRepository.findById(1).ifPresent((interesse) -> interesses.add(interesse));
        interesseRepository.findById(3).ifPresent((interesse) -> interesses.add(interesse));
        entity.setInteresses(interesses);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void incluir(ContatoDto dto) {
        save(dto);
    }
    
    @Override
    @Transactional
    public void alterar(int id, ContatoDto dto) {
        dto.setId(id);
        save(dto);
        
    }

    @Override
    @Transactional
    public void excluir(int id) {
        repository.deleteById(id);
    }

}
