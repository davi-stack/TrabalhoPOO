package src.main.java.com.mycompany.project.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
// import src.main.java.com.mycompany.project.telas.ExportServico;
import src.main.java.com.mycompany.project.telas.TelaCliente;
import src.main.java.com.mycompany.project.telas.TelaCadastroProduto;
import src.main.java.com.mycompany.project.telas.TelaServico;
import src.main.java.com.mycompany.project.entities.fromMoney.Orcamento;
import src.main.java.com.mycompany.project.entities.Produto;
import src.main.java.com.mycompany.project.entities.Enums.Unidades;
import src.main.java.com.mycompany.project.entities.fromMoney.Servico;
import src.main.java.com.mycompany.project.entities.fromMoney.Totalizavel;
import src.main.java.com.mycompany.project.dao.ServicosDAO;
import src.main.java.com.mycompany.project.dao.ClienteDAO;
import src.main.java.com.mycompany.project.dao.OrcamentoDAO;
import src.main.java.com.mycompany.project.dao.ProdutoDAO;
import src.main.java.com.mycompany.project.entities.Cliente;
import src.main.java.com.mycompany.project.entities.fromMoney.Diaria;
import src.main.java.com.mycompany.project.entities.fromMoney.Empreita;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class TelaOrcamento extends JFrame {
    private Orcamento orcamento;
    private DefaultTableModel model;

    // Listas de produtos e serviços estáticas
    private static List<Produto> produtosCadastrados = new ArrayList<>();
    private static List<Servico> servicosCadastrados = new ArrayList<>();
    private static List<Cliente> clientesCadastrados = new ArrayList<>();
    private List<JTextArea> listaObservacoes = new ArrayList<>();
    JLabel labelDescricao;
    JFormattedTextField txtData;
    JLabel labelData;
    JButton btnAdicionarCliente;
    JTextField txtNomeCliente;
    JComboBox<String> cbClientes;
    JTextArea campoDescricao;
    JLabel labelCliente;
    JButton btnSalvar;
    JTable tabelaItens;
    private DefaultComboBoxModel<String> modeloClientes;

    public void addProduto(Produto p){
        orcamento.adicionarItem(p);	
        model.addRow(new Object[]{p.getNome(), "R$ " + String.format("%.2f", p.getPreco())});
    }
    public void addServico(Servico s){
        orcamento.adicionarItem(s);
        model.addRow(new Object[]{s.getNomeItem(), "R$ " + String.format("%.2f", s.getValorTotal())});
        
        
    }
    public class salvarAdcion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cliente clienteSelecionado = clientesCadastrados.get(cbClientes.getSelectedIndex());
            orcamento.setCliente(clienteSelecionado.getNome());
            orcamento.setDescricao(campoDescricao.getText());
            orcamento.setObs(listaObservacoes.stream().map(JTextArea::getText).toList());
            orcamento.setData(txtData.getText());
            try{

                OrcamentoDAO.addOrcamento(orcamento);
                return;
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao salvar orçamento");
            }
            //limpar campos
            campoDescricao.setText("");
            txtData.setText("");
            model.setRowCount(0);
            listaObservacoes.clear();
            orcamento = new Orcamento();

            JOptionPane.showMessageDialog(null, "Orçamento salvo com sucesso!");
        }
    }
    public class OpenTelaProduto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaSelecionarProduto(produtosCadastrados, TelaOrcamento.this).setVisible(true);
            
        }
    }
    public class OpenTelaServico implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaSelecionarServico(servicosCadastrados, TelaOrcamento.this).setVisible(true);
        }
    }

    public JPanel criarPainelDetalhesOrcamento() {
        JPanel painelDetalhesOrcamento = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // Campo para a Data com máscara
        labelData = new JLabel("Data:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        painelDetalhesOrcamento.add(labelData, gbc);

        txtData = new JFormattedTextField(criarMascaraData());
        txtData.setColumns(8); // Campo menor
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painelDetalhesOrcamento.add(txtData, gbc);

        // Campo Descrição
        labelDescricao = new JLabel("Descrição:");
        gbc.gridx = 0;
        gbc.gridy = 1;  // Linha seguinte
        gbc.gridwidth = 1;
        painelDetalhesOrcamento.add(labelDescricao, gbc);

        campoDescricao = new JTextArea(5, 20);
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        campoDescricao.setPreferredSize(new Dimension(400, 100)); // Define tamanho preferido maior para expandir
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; // Permitir expansão vertical do campo de descrição
        painelDetalhesOrcamento.add(new JScrollPane(campoDescricao), gbc);

        // Campo Observações
        JLabel labelObservacoes = new JLabel("Observações:");
        gbc.gridx = 0;
        gbc.gridy = 2;  // Próxima linha
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        painelDetalhesOrcamento.add(labelObservacoes, gbc);

        JTextArea campoObservacoes = new JTextArea(5, 20);
        campoObservacoes.setLineWrap(true);
        campoObservacoes.setWrapStyleWord(true);
        campoObservacoes.setPreferredSize(new Dimension(400, 100)); // Define tamanho preferido maior para expandir
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; // Permitir expansão vertical do campo de observações
        painelDetalhesOrcamento.add(new JScrollPane(campoObservacoes), gbc);

        return painelDetalhesOrcamento;
    }
 private void adicionarObservacao(JPanel painelObservacoes) {
        JTextArea txtObs = new JTextArea(2, 25);
        txtObs.setLineWrap(true);
        txtObs.setWrapStyleWord(true);
        txtObs.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        listaObservacoes.add(txtObs); // Armazena a nova observação na lista
        painelObservacoes.add(txtObs); // Adiciona o campo de observação ao painel de observações
    }

    private MaskFormatter criarMascaraData() {
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            return mascaraData;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public TelaOrcamento() {
        // Inicializa o objeto orcamento
        orcamento = new Orcamento();
        setTitle("Sistema de Orçamentos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ProdutoDAO.lerProdutos().forEach(produtosCadastrados::add);
        ServicosDAO.readServicos().forEach(servicosCadastrados::add);
        List<Cliente> todosClientes = ClienteDAO.lerClientes();
        todosClientes.forEach(clientesCadastrados::add);

        JPanel barraSuperior = new JPanel();
        JLabel titulo = new JLabel("Novo Orçamento");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(76, 175, 80));
        btnSalvar.setForeground(Color.WHITE);
        barraSuperior.setLayout(new BorderLayout());
        barraSuperior.add(titulo, BorderLayout.WEST);
        barraSuperior.add(btnSalvar, BorderLayout.EAST);

        // Seção Cliente
        
        // Painel de Cliente
        JPanel clientePanel = criarPainelCliente();

        // Tabela de Itens (Produtos e Serviços)
        String[] colunas = {"Item", "Preço"};
        model = new DefaultTableModel(colunas, 0);
        tabelaItens = new JTable(model);
        tabelaItens.setFillsViewportHeight(true);

        // Adicionar itens do orçamento à tabela
        orcamento.getItens().forEach(i -> model.addRow(new Object[]{i.getNomeItem(), "R$ " + String.format("%.2f", i.getValorTotal())}));
        JScrollPane scrollTabela = new JScrollPane(tabelaItens);

        JButton btnAdicionarProduto = new JButton("Adicionar Produto");
        JButton btnAdicionarServico = new JButton("Adicionar Serviço");
        btnAdicionarProduto.addActionListener(new OpenTelaProduto());
        btnAdicionarServico.addActionListener(new OpenTelaServico());

        JPanel botoesItensPanel = new JPanel();
        botoesItensPanel.add(btnAdicionarProduto);
        botoesItensPanel.add(btnAdicionarServico);

        JPanel tabelaItensPanel = new JPanel(new BorderLayout());
        tabelaItensPanel.add(new JLabel("Itens do Orçamento"), BorderLayout.NORTH);
        tabelaItensPanel.add(scrollTabela, BorderLayout.CENTER);
        tabelaItensPanel.add(botoesItensPanel, BorderLayout.SOUTH);

        // Painel de Produtos e Serviços Cadastrados
        JPanel produtosServicosPanel = new JPanel(new GridLayout(2, 1));

        // Produtos
        JPanel produtosPanel = new JPanel(new BorderLayout());
        produtosPanel.add(new JLabel("Produtos Cadastrados"), BorderLayout.NORTH);
        JList<String> listaProdutos = new JList<>(produtosCadastrados.stream().map(Produto::getNome).toArray(String[]::new));
        listaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProdutos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Produto produtoSelecionado = produtosCadastrados.get(listaProdutos.getSelectedIndex());
                adicionarItemOrcamento(produtoSelecionado.getNome(), produtoSelecionado.getPreco());
            }
        });
        produtosPanel.add(new JScrollPane(listaProdutos), BorderLayout.CENTER);

        JPanel servicosPanel = new JPanel(new BorderLayout());
        servicosPanel.add(new JLabel("Serviços Cadastrados"), BorderLayout.NORTH);
        JList<String> listaServicos = new JList<>(servicosCadastrados.stream().map(Servico::getNomeItem).toArray(String[]::new));
        listaServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaServicos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Servico servicoSelecionado = servicosCadastrados.get(listaServicos.getSelectedIndex());
                adicionarItemOrcamento(servicoSelecionado.getNomeItem(), servicoSelecionado.getValorTotal());
            }
        });
        servicosPanel.add(new JScrollPane(listaServicos), BorderLayout.CENTER);

        produtosServicosPanel.add(produtosPanel);
        produtosServicosPanel.add(servicosPanel);

        JPanel detalhesPanel = criarPainelDetalhesOrcamento();


        // Resumo do Orçamento
        JLabel lblValorTotal = new JLabel("Total: R$ 0,00");
        lblValorTotal.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnImprimir = new JButton("Exportar/Imprimir");
        btnImprimir.setBackground(new Color(255, 87, 51));
        btnImprimir.setForeground(Color.WHITE);
        // btnImprimir.addActionListener(new ExportServico(orcamento, txtData, campoDescricao));
        JPanel resumoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        resumoPanel.add(detalhesPanel);
        resumoPanel.add(lblValorTotal);
        resumoPanel.add(btnImprimir);
    
        // Layout principal
        JPanel layoutPrincipal = new JPanel();
        layoutPrincipal.setLayout(new BorderLayout(10, 10));
        layoutPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        layoutPrincipal.add(barraSuperior, BorderLayout.NORTH);
        layoutPrincipal.add(clientePanel, BorderLayout.WEST);
        layoutPrincipal.add(tabelaItensPanel, BorderLayout.CENTER);
        layoutPrincipal.add(detalhesPanel, BorderLayout.EAST);
        layoutPrincipal.add(produtosServicosPanel, BorderLayout.SOUTH);
        layoutPrincipal.add(resumoPanel, BorderLayout.SOUTH);
        btnSalvar.addActionListener(new salvarAdcion());
        add(layoutPrincipal);
    }
    
    private JPanel criarPainelCliente() {
        JPanel clientePanel = new JPanel();
        clientePanel.setLayout(new BoxLayout(clientePanel, BoxLayout.Y_AXIS));
        clientePanel.setBorder(BorderFactory.createTitledBorder("Cliente"));
        
        JLabel labelCliente = new JLabel("Cliente:");
        txtNomeCliente = new JTextField(15);
        txtNomeCliente.setToolTipText("Buscar Cliente");

        modeloClientes = new DefaultComboBoxModel<>(clientesCadastrados.stream().map(Cliente::getNome).toArray(String[]::new));
        cbClientes = new JComboBox<>(modeloClientes);
        cbClientes.setPreferredSize(new Dimension(200, 25));

        txtNomeCliente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarClientes(txtNomeCliente.getText());
            }
        });

        clientePanel.add(labelCliente);
        clientePanel.add(txtNomeCliente);
        clientePanel.add(cbClientes);

        return clientePanel;
    }

    private void filtrarClientes(String termo) {
        modeloClientes.removeAllElements();
        clientesCadastrados.stream()
            .map(Cliente::getNome)
            .filter(nome -> nome.toLowerCase().contains(termo.toLowerCase()))
            .forEach(modeloClientes::addElement);
    }

    
    
    private void adicionarItemOrcamento(String nome, double preco) {
        model.addRow(new Object[]{nome, "R$ " + String.format("%.2f", preco)});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaOrcamento tela = new TelaOrcamento();
            tela.setVisible(true);
        });
    }
}
