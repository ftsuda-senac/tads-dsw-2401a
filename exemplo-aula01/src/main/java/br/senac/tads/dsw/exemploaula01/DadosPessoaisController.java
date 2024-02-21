/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemploaula01;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author fernando.tsuda
 */
@Controller
public class DadosPessoaisController {
    
    private final Map<String, String> acessos = new LinkedHashMap<>();
    
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
    
    @GetMapping("/exemplo-dinamico-parametros")
    public String mostrarTelaParametros(
            @RequestParam("nome") String nome, 
            @RequestParam("email") String email,
            @RequestParam("dataNascimento") String dataNascimento,
            @RequestParam("telefone") String telefone,
            HttpServletRequest req,
            Model model) {
        DadosPessoais dados = new DadosPessoais();
        dados.setNome(nome);
        dados.setDataNascimento(LocalDate.parse(dataNascimento));
        dados.setEmail(email);
        dados.setTelefone(telefone);
        
        LocalDateTime dataHoraAcesso = LocalDateTime.now();
        
        // Adiciona o nome, a data e hora do acesso e o endereço IP no Map acessos
        acessos.put(nome, "" + dataHoraAcesso.format(DateTimeFormatter.ISO_DATE_TIME) + "- IP: " + req.getRemoteAddr());
        System.out.println(nome + "," + req.getRemoteAddr() + " - " + dataHoraAcesso);
        
        model.addAttribute("dados", dados);
        model.addAttribute("dataHora", dataHoraAcesso);
        return "modelo-tela-dinamico";
    }
    
    
    @GetMapping("/acessos")
    public String mostrarAcessos(Model model) {
        model.addAttribute("acessos", acessos);
        return "acessos";
    }
    
}
