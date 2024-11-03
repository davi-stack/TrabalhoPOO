package src.main.java.com.mycompany.project.dao;
import src.main.java.com.mycompany.project.entities.Produto;
import src.main.java.com.mycompany.project.entities.fromMoney.Servico;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
public class ServicosDAO {
    public static String file = "BancoDeDados/servicos.ser";
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
    public static void saveServicos(ArrayList<Servico> servicos){
        createFileIfNotExists();
        try{
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(file));
            oos.writeObject(servicos);
            oos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Servico> readServicos(){
        createFileIfNotExists();
        ArrayList<Servico> servicos = new ArrayList<>();
       try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            servicos = (ArrayList<Servico>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de produtos não encontrado. Criando novo.");
        } catch (EOFException e) {
            System.out.println("Arquivo de produtos vazio. Iniciando com lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return servicos;
    }
    public static void addServico(Servico servico){
        ArrayList<Servico> servicos = readServicos();
        servicos.add(servico);
        saveServicos(servicos);
    }
    public static void removeServico(Servico servico){
        ArrayList<Servico> servicos = readServicos();
        servicos.remove(servico);
        saveServicos(servicos);
    }
    public static void updateServico(Servico servico){
        ArrayList<Servico> servicos = readServicos();
        for(int i = 0; i < servicos.size(); i++){
            if(servicos.get(i).getNomeServico().equals(servico.getNomeServico())){
                servicos.set(i, servico);
                break;
            }
        }
        saveServicos(servicos);
    }
    public static Servico getServico(String nomeServico){
        ArrayList<Servico> servicos = readServicos();
        for(Servico servico : servicos){
            if(servico.getNomeServico().equals(nomeServico)){
                return servico;
            }
        }
        return null;
    }

}
