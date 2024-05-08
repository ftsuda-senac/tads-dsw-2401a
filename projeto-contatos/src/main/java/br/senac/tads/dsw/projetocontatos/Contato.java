package br.senac.tads.dsw.projetocontatos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

// Classe de entidade que representa as informações da tabela contato do banco
// dentro da aplicação Spring Boot

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Preencha seu nome seu animal")
    @Size(min = 1, max = 100)
    private String nome;

    @Size(min = 0, max = 16)
    private String telefone;

    @Size(min = 0, max = 100)
    @Email
    private String email;

    @PastOrPresent
    private LocalDate dataNascimento;
    
    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL)
    private Set<Foto> fotos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contato_interesse",
            joinColumns = @JoinColumn(name = "contato_id"),
            inverseJoinColumns = @JoinColumn(name = "interesse_id"))
    private Set<Interesse> interesses;

    public Contato() {
    }

    public Contato(Integer id, String nome, String telefone, String email, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Set<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(Set<Foto> fotos) {
        this.fotos = fotos;
    }

    public Set<Interesse> getInteresses() {
        return interesses;
    }

    public void setInteresses(Set<Interesse> interesses) {
        this.interesses = interesses;
    }

}
