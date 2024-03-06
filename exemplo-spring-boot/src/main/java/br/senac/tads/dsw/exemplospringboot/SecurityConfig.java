/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplospringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http.headers()
                .cacheControl().disable() // Desabilita controle de cache
                .frameOptions().sameOrigin() // evita problema ao abrir console do H2
                .and().csrf().disable()
                .authorizeRequests()
                .requestMatchers("/favicon/**", "/webjars/**", "/css/**", "/js/**",
                        "/font/**", "/", "/index.html", "/h2-console/**").permitAll()
                .requestMatchers("/usuarios/incluir").permitAll()
                .anyRequest().authenticated()
               .and()
                .formLogin();

        // @formatter:on
        return http.build();
    }

}
