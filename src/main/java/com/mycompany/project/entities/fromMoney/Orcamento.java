package src.main.java.com.mycompany.project.entities.fromMoney;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Orcamento implements Serializable {
    ArrayList<Totalizavel> itens;
    String id;
    String nomeCliente;
    String data;
    Integer diasValidos;
    String descricao;
    List<String> obs;
    Date dataValidade;
    //Desconto
    double desconto;
    public ArrayList<Totalizavel> getItem(int index){
        return itens;
    }
    public ArrayList<Totalizavel> getItens(){
        return itens;
    }
    @Override
    public String toString(){
        return describeOrcamento();
    }
    //Impostos
    public Orcamento(){
        id = Math.random() + "";
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
    public void setCliente(String nomeCliente){
        this.nomeCliente = nomeCliente;
    }
    public String getId(){
        return id;
    }
    public void setData(String data){
        this.data = data;
    }
    public void setDiasValidos(Integer diasValidos){
        this.diasValidos = diasValidos;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setObs(List<String> obs){
        this.obs = obs;
    }
    public void setDataValidade(Date dataValidade){
        this.dataValidade = dataValidade;
    }

}
