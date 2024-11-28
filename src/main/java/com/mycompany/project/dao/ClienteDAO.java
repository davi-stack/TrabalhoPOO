package src.main.java.com.mycompany.project.dao;
import src.main.java.com.mycompany.project.entities.Cliente;
import src.main.java.com.mycompany.project.entities.Enums.Estado;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class ClienteDAO {
    public static String FILE_PATH = "BancoDeDados/Clientes.ser";
    private static void criarArquivoSeNecessario() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void salvarClientes(List<Cliente> clientes) {
        criarArquivoSeNecessario();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream
        (FILE_PATH))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Cliente> lerClientes() {
        criarArquivoSeNecessario();
        List<Cliente> clientes = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            clientes = (List<Cliente>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de clientes não encontrado. Criando novo.");
        } catch (EOFException e) {
            System.out.println("Arquivo de clientes vazio. Iniciando com lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clientes;
    }
    public static void gravarCliente(Cliente cliente) {
        List<Cliente> clientes = lerClientes();
        Optional<Cliente> clienteExistente = clientes.stream().filter(c -> c.getCpf().equals(cliente.getCpf())).findFirst();
        if (clienteExistente.isPresent()) {
            clientes.remove(clienteExistente.get());
        }
        clientes.add(cliente);
        salvarClientes(clientes);
    }
    public static void removerCliente(Cliente cliente) {
        List<Cliente> clientes = lerClientes();
        clientes.remove(cliente);
        salvarClientes(clientes);
    }
    public static Cliente buscarClientePorCPF(String cpf) {
        List<Cliente> clientes = lerClientes();
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getCpf().equals(cpf)).findFirst();
        return cliente.orElse(null);
    }
    public static List<Cliente> buscarClientesPorNome(String nome) {
        List<Cliente> clientes = lerClientes();
        return clientes.stream().filter(c -> c.getNome().contains(nome)).toList();
    }
    public static List<Cliente> buscarClientesPorEstado(Estado estado) {
        List<Cliente> clientes = lerClientes();
        return clientes.stream().filter(c -> c.getEstado().equals(estado)).toList();
    }

    public static List<Cliente> buscarClientesPorCidade(String cidade) {
        List<Cliente> clientes = lerClientes();
        return clientes.stream().filter(c -> c.getCidade().contains(cidade)).toList();
    }
    public static List<Cliente> buscarClientesPorCEP(String cep) {
        List<Cliente> clientes = lerClientes();
        return clientes.stream().filter(c -> c.getCep().equals(cep)).toList();
    }

}
