/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author fernando.tsuda
 */
public interface ContatoService {

    void alterar(int id, ContatoDto c);

    void excluir(int id);

    List<ContatoDto> findAll();

    Optional<ContatoDto> findById(int id);

    ContatoDto findByIdOriginal(int id);

    void incluir(ContatoDto c);
    
}
