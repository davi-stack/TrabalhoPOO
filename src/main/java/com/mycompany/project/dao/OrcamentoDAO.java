package src.main.java.com.mycompany.project.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import src.main.java.com.mycompany.project.entities.fromMoney.Orcamento;

public class OrcamentoDAO {
    public static final String file = "BancoDeDados/orcamento.ser";
    private static void createFileIfNotExists(){
        createDirIfNotExists();
        File f = new File(file);
        if(!f.exists()){
            try{
                f.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    private static void createDirIfNotExists(){
        File f = new File(file);
        File dir = f.getParentFile();
        if(dir != null && !dir.exists()){
            dir.mkdirs();
        }
    }
    //ler todos os orçamentos
    public static List<Orcamento> readOrcamentos(){
        createFileIfNotExists();
        List<Orcamento> orcamentos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            orcamentos = (List<Orcamento>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de orçamentos não encontrado. Criando novo.");
        } catch (EOFException e) {
            System.out.println("Arquivo de orçamentos vazio. Iniciando com lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orcamentos;
    }
    //salvar todos os orçamentos
    public static void saveOrcamentos(ArrayList<Orcamento> orcamentos){
        createFileIfNotExists();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(orcamentos);
            oos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //adicionar um orçamento
    public static void addOrcamento(Orcamento orcamento){
        ArrayList<Orcamento> orcamentos = (ArrayList<Orcamento>) readOrcamentos();
        orcamentos.add(orcamento);
        saveOrcamentos(orcamentos);
    }
    //remover um orçamento
    public static void removeOrcamento(Orcamento orcamento){
        ArrayList<Orcamento> orcamentos = (ArrayList<Orcamento>) readOrcamentos();
        orcamentos.remove(orcamento);
        saveOrcamentos(orcamentos);
    }
    //buscar um orçamento pelo id
    public static Orcamento buscarOrcamentoPorId(int id){
        List<Orcamento> orcamentos = readOrcamentos();
        for(Orcamento orcamento : orcamentos){
            if(orcamento.getId().equals(id)){
                return orcamento;
            }
        }
        return null;
    }

}