package br.senac.tads.dsw.projetocontatos.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Permissao implements GrantedAuthority {
    
    @Id
    private String nome;
    
    public Permissao() {
        
    }
    
    public Permissao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
    
}
