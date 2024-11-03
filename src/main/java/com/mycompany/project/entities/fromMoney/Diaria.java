package src.main.java.com.mycompany.project.entities.fromMoney;

import java.io.Serializable;

public class Diaria extends Servico implements Serializable {
    String nomeServico;
    public Diaria() {
        super();
    }
    public Diaria(String nomeServico, double valorDia, int dias) {
        super();
        this.nomeServico = nomeServico;
        this.valorDia = valorDia;
        this.dias = dias;
    }
    double valorDia;
    int dias;
    public double getValorDia() {
        return valorDia;
    }
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }
    public String getNomeItem() {
        return "Diaria: " + dias + " dias" + " de " + nomeServico;
    }

    public void setValorDia(double valorDia) {
        this.valorDia = valorDia;
    }
    public int getDias() {
        return dias;
    }
    public void setDias(int dias) {
        this.dias = dias;
    }
    @Override
    public double getValorTotal() {
        return valorDia * dias;
    }

    
}
