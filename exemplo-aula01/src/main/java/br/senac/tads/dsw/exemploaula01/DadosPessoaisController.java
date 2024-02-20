/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemploaula01;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author fernando.tsuda
 */
@Controller
public class DadosPessoaisController {
    
    @GetMapping("/exemplo-dinamico")
    public String mostrarTelaDinamica(Model model) {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome("Menino Neymar");
        dados.setDataNascimento(LocalDate.parse("1992-02-05"));
        dados.setEmail("neymar@email.com");
        dados.setTelefone("(12) 99887-9876");
        
        model.addAttribute("dados", dados);
        model.addAttribute("dataHora", LocalDateTime.now());
        
        return "modelo-tela-dinamico";
    }
    
}
