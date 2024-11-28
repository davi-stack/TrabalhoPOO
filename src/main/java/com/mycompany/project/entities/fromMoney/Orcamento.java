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
    public boolean isDataValida(){
        // Vê se a String segue o formato DD/MM/AAAA, com DD < 32, MM < 13 e AAAA > 0
        return data.matches("([0-2][0-9]|3[0-1])/(0[0-9]|1[0-2])/\\d{4}");

    }
    public String getNomeCliente(){
        return nomeCliente;
    }
    public List<String> getObs(){
        return obs;
    }

    public void setData(String data){
        this.data = data;
    }
    public int getDiasValidos(){
        return diasValidos;
    }
    public String getDescricao(){
        return descricao;
    }
    
    public String getData(){
        return data;
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
