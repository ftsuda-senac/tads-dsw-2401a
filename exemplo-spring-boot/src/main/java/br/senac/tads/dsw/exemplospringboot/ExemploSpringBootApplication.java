package br.senac.tads.dsw.exemplospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ExemploSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExemploSpringBootApplication.class, args);
	}
    
}
