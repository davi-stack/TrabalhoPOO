package src.main.java.com.mycompany.project.entities;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable {
    private String cpf;

    // Construtor
    public Cliente(String nome, String email, String telefone, String cpf) {
        super(nome, email, telefone);
        this.cpf = cpf;
    }

    // Métodos Get e Set para CPF
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}