package src.main.java.com.mycompany.project.telas;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaOrcamento extends JFrame {

    public TelaOrcamento() {
        setTitle("Sistema de Orçamentos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Barra Superior
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
        JPanel clientePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelCliente = new JLabel("Cliente:");
        JComboBox<String> cbClientes = new JComboBox<>();
        cbClientes.setPreferredSize(new Dimension(200, 25));
        JTextField txtNomeCliente = new JTextField(15);
        txtNomeCliente.setToolTipText("Novo Cliente");
        JButton btnAdicionarCliente = new JButton("Adicionar Cliente");

        clientePanel.add(labelCliente);
        clientePanel.add(cbClientes);
        clientePanel.add(txtNomeCliente);
        clientePanel.add(btnAdicionarCliente);

        // Tabela de Itens (Produtos e Serviços)
        String[] colunas = {"Item", "Preço"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        JTable tabelaItens = new JTable(model);
        JScrollPane scrollTabela = new JScrollPane(tabelaItens);

        JButton btnAdicionarProduto = new JButton("Adicionar Produto");
        JButton btnAdicionarServico = new JButton("Adicionar Serviço");

        JPanel botoesItensPanel = new JPanel();
        botoesItensPanel.add(btnAdicionarProduto);
        botoesItensPanel.add(btnAdicionarServico);

        JPanel tabelaItensPanel = new JPanel(new BorderLayout());
        tabelaItensPanel.add(new JLabel("Itens do Orçamento"), BorderLayout.NORTH);
        tabelaItensPanel.add(scrollTabela, BorderLayout.CENTER);
        tabelaItensPanel.add(botoesItensPanel, BorderLayout.SOUTH);

        // Detalhes do Orçamento
        JPanel detalhesPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        detalhesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField txtData = new JTextField();
        txtData.setToolTipText("Data (dd/mm/yyyy)");

        JTextField txtDiasValidos = new JTextField();
        txtDiasValidos.setToolTipText("Dias Válidos");

        JTextArea txtDescricao = new JTextArea(3, 20);
        txtDescricao.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtDescricao.setToolTipText("Descrição do Orçamento");

        JTextArea txtObs = new JTextArea(3, 20);
        txtObs.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtObs.setToolTipText("Observações");

        detalhesPanel.add(new JLabel("Data:"));
        detalhesPanel.add(txtData);
        detalhesPanel.add(new JLabel("Dias Válidos:"));
        detalhesPanel.add(txtDiasValidos);
        detalhesPanel.add(new JLabel("Descrição:"));
        detalhesPanel.add(new JScrollPane(txtDescricao));
        detalhesPanel.add(new JLabel("Observações:"));
        detalhesPanel.add(new JScrollPane(txtObs));

        // Resumo do Orçamento
        JLabel lblValorTotal = new JLabel("Total: R$ 0,00");
        lblValorTotal.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnImprimir = new JButton("Exportar/Imprimir");
        btnImprimir.setBackground(new Color(255, 87, 51));
        btnImprimir.setForeground(Color.WHITE);

        JPanel resumoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        resumoPanel.add(lblValorTotal);
        resumoPanel.add(btnImprimir);

        // Layout principal
        JPanel layoutPrincipal = new JPanel();
        layoutPrincipal.setLayout(new BorderLayout(10, 10));
        layoutPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        layoutPrincipal.add(barraSuperior, BorderLayout.NORTH);
        layoutPrincipal.add(clientePanel, BorderLayout.CENTER);
        layoutPrincipal.add(tabelaItensPanel, BorderLayout.WEST);
        layoutPrincipal.add(detalhesPanel, BorderLayout.EAST);
        layoutPrincipal.add(resumoPanel, BorderLayout.SOUTH);

        // Adiciona o layout principal ao JFrame
        add(layoutPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaOrcamento tela = new TelaOrcamento();
            tela.setVisible(true);
        });
    }
}
