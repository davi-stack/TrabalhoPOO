package src.main.java.com.mycompany.project.telas;

import src.main.java.com.mycompany.project.entities.Produto;
import src.main.java.com.mycompany.project.dao.ProdutoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import src.main.java.com.mycompany.project.entities.Enums.CategoriaProduto;

public class Produtos extends JFrame {
    private List<Produto> produtos;
    private JTable tabelaProdutos;
    private DefaultTableModel tabelaModel;
    private JButton addProduto;
    private JButton voltarButton;
    private JLabel tituloLabel;
    private JComboBox<CategoriaProduto> filtroCategoria;
    private JTextField pesquisaNome;

    public Produtos() {
        initComponents();
        carregarProdutos(); // Carrega os produtos ao inicializar a tela
    }

    private void initComponents() {
        // Inicializando os componentes
        produtos = ProdutoDAO.lerProdutos(); // Carrega os produtos do DAO
        tituloLabel = new JLabel("Lista de Produtos");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Definindo uma fonte mais robusta
        addProduto = new JButton("+");
        voltarButton = new JButton("Voltar");

        // Configuração da tabela de produtos
        tabelaModel = new DefaultTableModel(new Object[]{"Código", "Nome", "Preço", "Rendimento", "Unidade"}, 0);
        tabelaProdutos = new JTable(tabelaModel);
        tabelaProdutos.setFillsViewportHeight(true);
        
        // Configuração do filtro
        filtroCategoria = new JComboBox<>(CategoriaProduto.values());
        pesquisaNome = new JTextField(15);

        // Adicionando ação para o botão de adicionar produtos
        addProduto.addActionListener(new CadastrarProdutoListener());

        // Ação para o botão de voltar
        voltarButton.addActionListener(e -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
            dispose(); // Fecha a tela atual
        });

        // Adicionar listener de ação para filtros
        filtroCategoria.addActionListener(e -> aplicarFiltros());
        pesquisaNome.addActionListener(e -> aplicarFiltros());

        // Configuração do layout
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelSuperior.add(new JLabel("Filtrar por Categoria:"));
        painelSuperior.add(filtroCategoria);
        painelSuperior.add(new JLabel("Pesquisar por Nome:"));
        painelSuperior.add(pesquisaNome);
        
        setLayout(new BorderLayout());
        add(tituloLabel, BorderLayout.NORTH);
        add(painelSuperior, BorderLayout.NORTH); // Move painelSuperior para o topo
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        add(scrollPane, BorderLayout.CENTER);     // scrollPane permanece no centro

        // Painel inferior para botões
        JPanel panelButtons = new JPanel();
        panelButtons.add(voltarButton);
        panelButtons.add(addProduto);
        add(panelButtons, BorderLayout.SOUTH);

        // Configuração da janela
        setTitle("Lista de Produtos");
        setSize(700, 500);
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

    private void aplicarFiltros() {
        String pesquisa = pesquisaNome.getText().toLowerCase();
        CategoriaProduto categoriaSelecionada = (CategoriaProduto) filtroCategoria.getSelectedItem();

        // Limpa a tabela antes de aplicar os filtros
        tabelaModel.setRowCount(0);
        // Adiciona produtos filtrados
        for (Produto produto : produtos) {
            boolean matchesNome = produto.getNome().toLowerCase().contains(pesquisa);
            boolean matchesCategoria = categoriaSelecionada == null || produto.CategoriaAplica(categoriaSelecionada);
            if (matchesNome && matchesCategoria) {
                tabelaModel.addRow(new Object[]{
                    produto.getCodigo(),
                    produto.getNome(),
                    String.format("%.2f", produto.getPreco()),
                    produto.getRendimento(),
                    produto.getUnidade()
                });
            }
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
