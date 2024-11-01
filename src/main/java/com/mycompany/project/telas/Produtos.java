package src.main.java.com.mycompany.project.telas;
import src.main.java.com.mycompany.project.entities.Produto;
import src.main.java.com.mycompany.project.dao.ProdutoDAO;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import src.main.java.com.mycompany.project.telas.TelaCadastroProduto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author Joao Vitor Dantas
 */
public class Produtos extends JFrame {
    private List<Produto> produtos;
    private JTable tabelaProdutos;
    private DefaultTableModel tabelaModel;
    private JButton addProduto;
    private JButton jButton1;
    private JLabel jLabel1;

    public Produtos() {
        initComponents();
        carregarProdutos(); // Carrega os produtos ao inicializar a tela
    }

    private void initComponents() {
        // Inicializando os componentes
        produtos = ProdutoDAO.lerProdutos(); // Carrega os produtos do DAO
        jLabel1 = new JLabel("Produtos");
        addProduto = new JButton("+");
        jButton1 = new JButton("Voltar");

        // Configuração da tabela de produtos
        tabelaModel = new DefaultTableModel(new Object[]{"Código", "Nome", "Preço", "Rendimento", "Unidade"}, 0);
        tabelaProdutos = new JTable(tabelaModel);

        // Adicionando ação para o botão de adicionar produtos
        addProduto.addActionListener(new CadastrarProdutoListener());

        // Ação para o botão de voltar
        jButton1.addActionListener(e -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
            dispose(); // Fecha a tela atual
        });

        // Configuração do layout
        setLayout(new BorderLayout());
        add(jLabel1, BorderLayout.NORTH);

        // Adiciona a tabela dentro de um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior para botões
        JPanel panelButtons = new JPanel();
        panelButtons.add(jButton1);
        panelButtons.add(addProduto);
        add(panelButtons, BorderLayout.SOUTH);

        // Configuração da janela
        setTitle("Lista de Produtos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void carregarProdutos() {
        // Limpa a tabela antes de adicionar novos dados
        tabelaModel.setRowCount(0);

        // Popula a tabela com os produtos do DAO
        for (Produto produto : produtos) {
            tabelaModel.addRow(new Object[]{
                produto.getCodigo(),
                produto.getNome(),
                String.format("%.2f", produto.getPreco()),
                produto.getRendimento(),
                produto.getUnidade()
            });
        }
    }

    // Listener para o botão de adicionar produto
    private class CadastrarProdutoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TelaCadastroProduto telaCadastroProduto = new TelaCadastroProduto();
            telaCadastroProduto.setVisible(true);
            telaCadastroProduto.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // Atualiza a lista de produtos quando a janela de cadastro for fechada
                    produtos = ProdutoDAO.lerProdutos(); // Recarrega os produtos
                    carregarProdutos(); // Atualiza a tabela
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Produtos::new);
    }
}