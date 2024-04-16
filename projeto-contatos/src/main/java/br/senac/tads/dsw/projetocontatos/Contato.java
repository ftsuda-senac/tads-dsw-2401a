package br.senac.tads.dsw.projetocontatos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class Contato {

    private Integer id;

    @NotBlank(message = "Preencha seu nome seu animal")
    @Size(min = 1, max = 100)
    private String nome;

    @Size(min = 0, max = 16)
    private String telefone;

    @NotBlank
    @Size(min = 3, max = 100)
    @Email
    private String email;

    @PastOrPresent
    private LocalDate dataNascimento;

    private List<String> fotos;

    public Contato() {
    }

    public Contato(Integer id, String nome, String telefone, String email, LocalDate dataNascimento, List<String> fotos) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.fotos = fotos;
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

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

}
