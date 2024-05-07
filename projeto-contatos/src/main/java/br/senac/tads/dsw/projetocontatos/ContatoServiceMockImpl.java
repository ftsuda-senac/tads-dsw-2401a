/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ContatoServiceMockImpl implements ContatoService {
    
    private static int sequenciaIds = 0;
    
    private final Map<Integer, ContatoDto> mapContatos;
    
    public ContatoServiceMockImpl() {
        mapContatos = new LinkedHashMap<>();
        int id = ++sequenciaIds;
        mapContatos.put(id, new ContatoDto(id, "Fulano da Silva", "(11) 98765-1234",
                        "fulano@email.com.br", LocalDate.parse("2000-10-20"), 
                        Collections.emptyList()));
        id = ++sequenciaIds;
        mapContatos.put(id, new ContatoDto(id, "Ciclano de Souza", "(11) 99001-3344", 
                        "ciclano@email.com.br", LocalDate.parse("1999-05-16"), 
                        Collections.emptyList()));
        id = ++sequenciaIds;
        mapContatos.put(id, new ContatoDto(id, "Beltrana dos Santos", "(11) 91028-5432", 
                        "beltrana@email.com.br", LocalDate.parse("2001-07-04"), 
                        Collections.emptyList()));
    }
    
    @Override
    public List<ContatoDto> findAll() {
        return new ArrayList<>(mapContatos.values());
    }
    
    @Override
    public ContatoDto findByIdOriginal(int id) {
        return mapContatos.get(id);
    }

    @Override
    public Optional<ContatoDto> findById(int id) {
        return Optional.ofNullable(mapContatos.get(id));
    }
    
    @Override
    public void incluir(ContatoDto c) {
        int id = ++sequenciaIds;
        c.setId(id);
        mapContatos.put(id, c);
    }
    
    @Override
    public void alterar(int id, ContatoDto c) {
        c.setId(id);
        mapContatos.put(id, c);
    }
    
    @Override
    public void excluir(int id) {
        mapContatos.remove(id);
    }
    
}
