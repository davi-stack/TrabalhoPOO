package src.main.java.com.mycompany.project.entities.fromMoney;

public class Diaria extends Servico {
    String nomeServico;
    public Diaria() {
        super();
    }
    double valorDia;
    int dias;
    public double getValorDia() {
        return valorDia;
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
