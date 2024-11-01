package src.main.java.com.mycompany.project.dao;

import src.main.java.com.mycompany.project.entities.Produto;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {
    private static final String FILE_PATH_produtos = "BancoDeDados/produtos.ser";
    

    public static void createFileProduct(){
        File file = new File(FILE_PATH_produtos);
        File diretorio = file.getParentFile();
        
        // Cria o diretório se ele não existir
        if (diretorio != null && !diretorio.exists()) {
            diretorio.mkdirs();
        }

        // Cria o arquivo se ele não existir
        try {
            if (!file.exists()) {
                file.createNewFile();
                salvarProdutos(new ArrayList<>()); // Salva uma lista vazia inicialmente
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Produto> lerProdutos(){
        createFileProduct();
        List<Produto> produtos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH_produtos))) {
            produtos = (List<Produto>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de produtos não encontrado. Criando novo.");
        } catch (EOFException e) {
            System.out.println("Arquivo de produtos vazio. Iniciando com lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return produtos;
    }
    
    public static void salvarProdutos(List<Produto> produtos){
        createFileProduct();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH_produtos))) {
            oos.writeObject(produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void gravarProduto(Produto produto){
        List<Produto> produtos = lerProdutos();
        produtos.add(produto);
        salvarProdutos(produtos);
    }
    public static Produto buscarProdutoPorCodigo(String codigo){
        List<Produto> produtos = lerProdutos();
        for (Produto produto : produtos) {
            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return produto;
            }
        }
        return null;
    }
    public static String produtoCSV(){
        List<Produto> produtos = lerProdutos();
        String csv = "Código;Nome;Preço;Rendimento;Comprimento;Litros;Unidades;Metro Linear;Metro Quadrado;Metro Cúbico\n";
        for (Produto produto : produtos) {
            csv += produto.getCodigo() + ";" + produto.getNome() + ";" + produto.getPreco() + ";" + produto.getRendimento() + ";" + produto.getValor()+ "\n";
        }
        return csv;
    }
}
