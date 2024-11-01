package src.main.java.com.mycompany.project.entities;

import java.io.Serializable;

public abstract class Pessoa implements Serializable{
    protected String nome;
    protected String email;
    protected String telefone;

    // Construtor
    public Pessoa(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // Métodos Get e Set para Nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos Get e Set para Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Métodos Get e Set para Telefone
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}