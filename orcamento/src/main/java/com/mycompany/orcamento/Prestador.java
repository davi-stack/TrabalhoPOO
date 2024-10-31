/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orcamento;

public class Prestador extends Pessoa {
    private String username;
    private String senha;

    // Construtor
    public Prestador(String nome, String email, String telefone, String username, String senha) {
        super(nome, email, telefone);
        this.username = username;
        this.senha = senha;
    }

    // Métodos Get e Set para Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Métodos Get e Set para Senha
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}

