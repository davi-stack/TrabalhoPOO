package src.main.java.com.mycompany.project.dao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import src.main.java.com.mycompany.project.entities.Prestador;
import src.main.java.com.mycompany.project.entities.Produto;
public class PrestadorDAO {

    private static final String FILE_PATH = "BancoDeDados/prestadores.ser";
    private static final String FILE_PATH_produtos = "BancoDeDados/produtos.ser";
    // Método para garantir que o diretório e o arquivo existam
    private static void criarArquivoSeNecessario() {
        File file = new File(FILE_PATH);
        File diretorio = file.getParentFile();
        
        // Cria o diretório se ele não existir
        if (diretorio != null && !diretorio.exists()) {
            diretorio.mkdirs();
        }

        // Cria o arquivo se ele não existir
        try {
            if (!file.exists()) {
                file.createNewFile();
                salvarPrestadores(new ArrayList<>()); // Salva uma lista vazia inicialmente
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para salvar uma lista de prestadores no arquivo
    public static void salvarPrestadores(List<Prestador> prestadores) {
        criarArquivoSeNecessario(); // Garante que o arquivo e diretório existam
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(prestadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para ler todos os prestadores do arquivo
    public static List<Prestador> lerPrestadores() {
        criarArquivoSeNecessario(); // Garante que o arquivo e diretório existam
        List<Prestador> prestadores = new ArrayList<>();
        
        // Tenta ler o arquivo
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            prestadores = (List<Prestador>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de prestadores não encontrado. Criando novo.");
        } catch (EOFException e) {
            System.out.println("Arquivo de prestadores vazio. Iniciando com lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return prestadores;
    }

    // Método para adicionar um novo prestador à lista e salvar
    public static void gravarPrestador(Prestador prestador) {
        List<Prestador> prestadores = lerPrestadores();
        prestadores.add(prestador);
        salvarPrestadores(prestadores);
    }

    // Método para buscar um prestador pelo email
    public static Prestador buscarPrestadorPorEmail(String email) {
        List<Prestador> prestadores = lerPrestadores();
        for (Prestador prestador : prestadores) {
            if (prestador.getEmail().equalsIgnoreCase(email)) {
                return prestador;
            }
        }
        return null;
    }

    // Método para verificar se o email e a senha estão corretos
    public static boolean autenticarPrestador(String email, String senha) {
        Prestador prestador = buscarPrestadorPorEmail(email);
        return prestador != null && prestador.getSenha().equals(senha);
    }

    // Método para listar todos os prestadores (para depuração ou verificação)
    public static void listarPrestadores() {
        List<Prestador> prestadores = lerPrestadores();
        for (Prestador prestador : prestadores) {
            System.out.println("Nome: " + prestador.getNome() + ", Email: " + prestador.getEmail());
        }
    }
    

}