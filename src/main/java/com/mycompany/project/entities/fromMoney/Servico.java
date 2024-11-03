package src.main.java.com.mycompany.project.entities.fromMoney;
import src.main.java.com.mycompany.project.entities.fromMoney.*;


public abstract class Servico implements Totalizavel{
    String nomeServico;
    public abstract double getValorTotal();
    public String getNomeServico(){
        return nomeServico;
    }
}
