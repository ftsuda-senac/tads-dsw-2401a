/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContatoRepository
        extends JpaRepository<Contato, Integer> {

    Optional<Contato> findByEmail(String email);

    List<Contato> findByTelefone(String telefone);

    List<Contato> findByDataNascimentoAfter(LocalDate data);

    List<Contato> findByNomeContaining(String trechoNome);

    List<Contato> findByNomeContainingAndDataNascimentoAfter(String trechoNome, LocalDate data);

    @Query(nativeQuery = true, value = "SELECT * FROM contato WHERE nome like %?1% AND data_nascimento > ?2")
    List<Contato> obterPorNomeDataNascimento(String trechoNome, LocalDate data);

}
