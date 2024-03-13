/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplosjs;

import java.time.LocalDate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fernando.tsuda
 */
@RestController
@CrossOrigin(origins = { "*" })
public class DadosPessoaisApiController {
    
    @GetMapping("/dados-pessoais")
    public DadosPessoais obterDados() {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome("Seu Madruga");
        dados.setEmail("madruga@email.com");
        dados.setTelefone("(11) 99999-1234");
        dados.setDataNascimento(LocalDate.parse("1950-04-15"));
        return dados;
    }
    
}
