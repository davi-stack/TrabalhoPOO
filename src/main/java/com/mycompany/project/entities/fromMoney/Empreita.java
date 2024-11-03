package src.main.java.com.mycompany.project.entities.fromMoney;

public class Empreita extends Servico{
    private double valorEmpreita;

    public Empreita(double valorEmpreita) {
        this.valorEmpreita = valorEmpreita;
    }
    public String getNomeItem(){
        return "Empreita";
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
