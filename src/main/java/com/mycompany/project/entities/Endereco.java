package src.main.java.com.mycompany.project.entities;
import src.main.java.com.mycompany.project.entities.Enums.Estado;
import java.io.Serializable;
public class Endereco implements Serializable {
    String Logradouro;
    String cidade;
    Estado estado;
    String cep;
    public Endereco(String Logradouro, String cidade, Estado estado, String cep) {
        this.Logradouro = Logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }
}
