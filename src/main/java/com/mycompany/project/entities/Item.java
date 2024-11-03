package src.main.java.com.mycompany.project.entities;
import src.main.java.com.mycompany.project.entities.fromMoney.*;
public class Item implements Totalizavel {
    Produto produto;
    double quantidade;
    public Item(Produto produto, double quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }
    @Override
    public double getValorTotal(){
        return produto.getPreco() * quantidade;
    }
    @Override
    public String getNomeItem(){
        return produto.getNome();
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
