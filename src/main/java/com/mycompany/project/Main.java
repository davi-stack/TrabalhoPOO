package com.mycompany.project;
import com.mycompany.project.entities.Produto;
import java.util.ArrayList;
import java.util.List;
public class Main{
    public static void main(String [] args){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1, "Coca-Cola", 5.0));
        produtos.add(new Produto(2, "Pepsi", 4.5));
        produtos.add(new Produto(3, "Fanta", 4.0));
        produtos.add(new Produto(4, "Guaraná", 3.5));
        produtos.add(new Produto(5, "Sukita", 3.0));
        produtos.add(new Produto(6, "Sprite", 2.5));
        produtos.add(new Produto(7, "Kuat", 2.0));
        produtos.add(new Produto(8, "Schweppes", 1.5));
        produtos.add(new Produto(9, "Tubaína", 1.0));
        produtos.add(new Produto(10, "Dolly", 0.5));
        for(Produto produto : produtos){
            System.out.println(produto.getNome() + " - R$ " + produto.getPreco());
        }
    }
}