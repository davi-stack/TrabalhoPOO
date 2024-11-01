package src.main.java.com.mycompany.project.entities;
import java.io.Serializable;
import src.main.java.com.mycompany.project.entities.fromMoney.Totalizavel;
import java.util.ArrayList;
import src.main.java.com.mycompany.project.entities.Enums.Unidades;
import src.main.java.com.mycompany.project.entities.Enums.CategoriaProduto;
public class Produto implements Serializable,Totalizavel {
    private String codigo;           // Código único do produto
    private String nome;
    private double preco;
    private double rendimento;       // Rendimento em relação à unidade de medida usada (ex: m²/litro, m²/unidade, etc.)
    // private double comprimento;      // Em metros
    // private double litros;           // Em litros
    private Unidades unidade;            // Em unidades inteiras
    // private double metroLinear;      // Em metros lineares
    // private double metroQuadrado;    // Em metros quadrados
    // private double metroCubico;      // Em metros cúbicos
    // private Unidades unidade;
    // salvar quais unidades de medida o produto tem
    private double valor;
    private ArrayList<Unidades> unidadesDeMedida = new ArrayList<Unidades>();
    private ArrayList<CategoriaProduto> categorias = new ArrayList<CategoriaProduto>();
    public void addCategoria(CategoriaProduto categoria){
        categorias.add(categoria);
    }
    public boolean CategoriaAplica(CategoriaProduto categoria){
        return categorias.contains(categoria);
    }
    public double getValorTotal() {
        return preco;
    }
    public Produto(String nome, Unidades unidade, double preco, double valor) {
        this.unidade = unidade;
        this.valor = valor;
        this.preco = preco;
        this.nome = nome;
        //gera um numero aleatorio para o codigo
        this.codigo = String.valueOf(Math.random());

    }
    // Construtor
    public Produto(String codigo, String nome, double preco, double rendimento, double comprimento, double litros, int unidades, double metroLinear, double metroQuadrado, double metroCubico) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.rendimento = rendimento;
        
    }
    public Produto(String codigo, String nome, double preco, double rendimento, double comprimento, double litros, int unidades, double metroLinear, double metroQuadrado, double metroCubico, ArrayList<Unidades> unidadesDeMedida) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.rendimento = rendimento;
        this.unidadesDeMedida = unidadesDeMedida;
    }
    // Métodos Get e Set para Código
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Métodos Get e Set para Nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos Get e Set para Preço
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Métodos Get e Set para Rendimento
    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }
    public double getValor(){
        return valor;
    }
    // Métodos Get e Set para Comprimento
   public Unidades getUnidade(){
       return unidade;
   
   }
    

    
}
