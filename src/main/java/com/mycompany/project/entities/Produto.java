package com.mycompany.project.entities;

public class Produto {
    private int id;
    private String nome;
    private double preco;

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
    public String getNome(){
        return this.nome;
    }
    public double getPreco(){
        return this.preco;
    }

    // Getters e setters, se necessários
}
