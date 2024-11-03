package src.main.java.com.mycompany.project.entities.fromMoney;
import java.util.ArrayList;
public class Orcamento {
    ArrayList<Totalizavel> itens;
    String id;
    String nomeCliente;
    String data;
    Integer diasValidos;
    String descricao;
    ArrayList<String> obs;
    //Desconto
    double desconto;
    
    //Impostos
    public Orcamento(){
        itens = new ArrayList<Totalizavel>();
    }
    public void adicionarItem(Totalizavel item){
        itens.add(item);
    }
    public double getValorTotal(){
        double total = 0;
        for(Totalizavel item : itens){
            total += item.getValorTotal();
        }
        return total;
    }
    public String describeOrcamento(){
        String descricao = "Orcamento: " + id + "\n";
        descricao += "Cliente: " + nomeCliente + "\n";
        descricao += "Data: " + data + "\n";
        descricao += "Dias Validos: " + diasValidos + "\n";
        descricao += "Descricao: " + descricao + "\n";
        descricao += "Obs: " + obs + "\n";
        descricao += "Itens: \n";
        for(Totalizavel item : itens){
            descricao += item.getNomeItem() + " - R$ " + item.getValorTotal() + "\n";
        }

        descricao += "Total: R$ " + getValorTotal();
        //Adicionar observacoes ao final em tópicos
        /*
         * Exemplo
         * Obs:
         * - Observacao 1: bla bla bla
         * - Observacao 2: bla bla bla
         * - Observacao 3: bla bla bla
         */
        for(int i=0; i<obs.size(); i++){
            descricao += "\n- " + " Observação " + i + ": " + obs.get(i);
        }
        return descricao;
    }

}
