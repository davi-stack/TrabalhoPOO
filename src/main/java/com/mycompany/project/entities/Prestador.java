package src.main.java.com.mycompany.project.entities;
import java.io.Serializable;
public class Prestador extends Pessoa implements Serializable {
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
