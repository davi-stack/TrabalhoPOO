package src.main.java.com.mycompany.project.entities.fromMoney;
import src.main.java.com.mycompany.project.entities.fromMoney.*;
import java.io.Serializable;

public abstract class Servico implements Totalizavel, Serializable{
    String nomeServico;

    public abstract double getValorTotal();
    public String getNomeServico(){
        return nomeServico;
    }
}
