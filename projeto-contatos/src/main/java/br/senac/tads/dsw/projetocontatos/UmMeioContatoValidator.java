/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UmMeioContatoValidator implements ConstraintValidator<UmMeioContato, ContatoDto> {

    @Override
    public boolean isValid(ContatoDto contato, ConstraintValidatorContext cvc) {
        if ((contato.getEmail() == null || contato.getEmail().isBlank()) &&
                (contato.getTelefone() == null || contato.getTelefone().isBlank())) {
            return false;
        }
        return true;
    }
    
}
