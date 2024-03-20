/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplosspring;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExemploHttpServletRequestController {
    
    @GetMapping("/exemplo-httpservletrequest")
    public String exemploDadosRequest(HttpServletRequest request) {
        String x = request.getHeader("user-agent");
        return x;
    }
    
}
