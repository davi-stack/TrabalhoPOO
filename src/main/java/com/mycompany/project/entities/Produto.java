package src.main.java.com.mycompany.project.entities;
import java.io.Serializable;
import src.main.java.com.mycompany.project.entities.fromMoney.Totalizavel;
import java.util.ArrayList;
import src.main.java.com.mycompany.project.entities.Enums.Unidades;
public class Produto implements Serializable,Totalizavel {
    private String codigo;           // Código único do produto
    private String nome;
    private double preco;
    private double rendimento;       // Rendimento em relação à unidade de medida usada (ex: m²/litro, m²/unidade, etc.)
    private double comprimento;      // Em metros
    private double litros;           // Em litros
    private int unidades;            // Em unidades inteiras
    private double metroLinear;      // Em metros lineares
    private double metroQuadrado;    // Em metros quadrados
    private double metroCubico;      // Em metros cúbicos

    // salvar quais unidades de medida o produto tem
    private ArrayList<Unidades> unidadesDeMedida = new ArrayList<Unidades>();

    public double getValorTotal() {
        return preco;
    }
    // Construtor
    public Produto(String codigo, String nome, double preco, double rendimento, double comprimento, double litros, int unidades, double metroLinear, double metroQuadrado, double metroCubico) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.rendimento = rendimento;
        this.comprimento = comprimento;
        this.litros = litros;
        this.unidades = unidades;
        this.metroLinear = metroLinear;
        this.metroQuadrado = metroQuadrado;
        this.metroCubico = metroCubico;
    }
    public Produto(String codigo, String nome, double preco, double rendimento, double comprimento, double litros, int unidades, double metroLinear, double metroQuadrado, double metroCubico, ArrayList<Unidades> unidadesDeMedida) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.rendimento = rendimento;
        this.comprimento = comprimento;
        this.litros = litros;
        this.unidades = unidades;
        this.metroLinear = metroLinear;
        this.metroQuadrado = metroQuadrado;
        this.metroCubico = metroCubico;
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

    // Métodos Get e Set para Comprimento
    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    // Métodos Get e Set para Litros
    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    // Métodos Get e Set para Unidades
    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    // Métodos Get e Set para Metro Linear
    public double getMetroLinear() {
        return metroLinear;
    }

    public void setMetroLinear(double metroLinear) {
        this.metroLinear = metroLinear;
    }

    // Métodos Get e Set para Metro Quadrado
    public double getMetroQuadrado() {
        return metroQuadrado;
    }

    public void setMetroQuadrado(double metroQuadrado) {
        this.metroQuadrado = metroQuadrado;
    }

    // Métodos Get e Set para Metro Cúbico
    public double getMetroCubico() {
        return metroCubico;
    }

    public void setMetroCubico(double metroCubico) {
        this.metroCubico = metroCubico;
    }

}
