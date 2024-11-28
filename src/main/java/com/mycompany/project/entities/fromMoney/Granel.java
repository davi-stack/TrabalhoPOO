package src.main.java.com.mycompany.project.entities.fromMoney;
import src.main.java.com.mycompany.project.entities.Produto;
import src.main.java.com.mycompany.project.entities.Enums.Unidades;
public class Granel extends Produto{
    double minQuantidade;
    public Granel(String nome, Unidades unidade, double preco, double quantidade){
        super(nome, unidade, preco, quantidade);
    }
    
}
