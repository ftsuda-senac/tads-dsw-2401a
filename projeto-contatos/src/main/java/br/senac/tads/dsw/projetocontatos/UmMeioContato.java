/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Validação para verificar se pelo menos o e-mail ou telefone
 * foi preenchido.
 * Gera erro caso nenhum seja preenchido
 * 
 * @author fernando.tsuda
 */
@Documented
@Constraint(validatedBy = UmMeioContatoValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UmMeioContato {

    String message() default "Pelo menos um meio de contato deve ser informado";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};

}
