package src.main.java.com.mycompany.project.entities.fromMoney;

import java.io.Serializable;

public class Empreita extends Servico implements Serializable{
    private double valorEmpreita;
    private int diasParaTerminar;
    public Empreita(double valorEmpreita) {
        this.valorEmpreita = valorEmpreita;
    }
    public Empreita(String nome, double valorEmpreita, int diasParaTerminar) {
        this.valorEmpreita = valorEmpreita;
        this.diasParaTerminar = diasParaTerminar;
    }
    public String getNomeItem(){
        return "Empreita";
    }
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }
    public int getDiasParaTerminar() {
        return diasParaTerminar;
    }
    public void setDiasParaTerminar(int diasParaTerminar) {
        this.diasParaTerminar = diasParaTerminar;
    }

    public double getValorEmpreita() {
        return valorEmpreita;
    }

    public void setValorEmpreita(double valorEmpreita) {
        this.valorEmpreita = valorEmpreita;
    }
    @Override
    public double getValorTotal() {
        return valorEmpreita;
    }
    
}
