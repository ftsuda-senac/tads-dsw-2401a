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
public class ContatoService {
    
    private static int sequenciaIds = 0;
    
    private final Map<Integer, Contato> mapContatos;
    
    public ContatoService() {
        mapContatos = new LinkedHashMap<>();
        int id = ++sequenciaIds;
        mapContatos.put(id, new Contato(id, "Fulano da Silva", "(11) 98765-1234",
                        "fulano@email.com.br", LocalDate.parse("2000-10-20"), 
                        Collections.emptyList()));
        id = ++sequenciaIds;
        mapContatos.put(id, new Contato(id, "Ciclano de Souza", "(11) 99001-3344", 
                        "ciclano@email.com.br", LocalDate.parse("1999-05-16"), 
                        Collections.emptyList()));
        id = ++sequenciaIds;
        mapContatos.put(id, new Contato(id, "Beltrana dos Santos", "(11) 91028-5432", 
                        "beltrana@email.com.br", LocalDate.parse("2001-07-04"), 
                        Collections.emptyList()));
    }
    
    public List<Contato> findAll() {
        return new ArrayList<>(mapContatos.values());
    }
    
    public Contato findByIdOriginal(int id) {
        return mapContatos.get(id);
    }
    
    public Optional<Contato> findById(int id) {
        return Optional.ofNullable(mapContatos.get(id));
    }
    
    public void incluir(Contato c) {
        int id = ++sequenciaIds;
        c.setId(id);
        mapContatos.put(id, c);
    }
    
    public void alterar(int id, Contato c) {
        c.setId(id);
        mapContatos.put(id, c);
    }
    
    public void excluir(int id) {
        mapContatos.remove(id);
    }
    
}
