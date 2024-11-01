package src.main.java.com.mycompany.project.entities;
import src.main.java.com.mycompany.project.entities.Enums.Estado;
import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable {
    private String cpf;
    private String nome, email;
    private String telefone;
    private Endereco endereco;
    // Construtor
    public Cliente(String nome, String email, String telefone, String cpf) {
        super(nome, email, telefone);
        this.cpf = cpf;
    }

    public Cliente(String nome, String email, String telefone, String cpf, Endereco endereco) {
        super(nome, email, telefone);
        this.cpf = cpf;
        this.endereco = endereco;
    }
    public Cliente(){
        super("","","");
        
    }
    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos Get e Set para CPF
    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public Estado getEstado(){
        return endereco.estado;
    }
    public String getCidade(){
        return endereco.cidade;
    }   
    public String getLogradouro(){
        return endereco.Logradouro;
    }
    public String getCep(){
        return endereco.cep;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}