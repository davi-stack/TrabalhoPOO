package src.main.java.com.mycompany.project.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import src.main.java.com.mycompany.project.entities.Produto;

public class TelaSelecionarProduto extends JFrame {
    private JList<String> listaProdutos;
    private DefaultListModel<String> modelListaProdutos;
    private List<Produto> produtosCadastrados;
    private TelaOrcamento telaOrcamento;  // Instância de TelaOrcamento para chamada do método
    public class OpenTelaProduto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaCadastroProduto().setVisible(true);
        }
    }
    public TelaSelecionarProduto(List<Produto> produtos, TelaOrcamento telaOrcamento) {
        this.produtosCadastrados = produtos;
        this.telaOrcamento = telaOrcamento;

        setTitle("Selecionar Produtos");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Produtos Disponíveis", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        // Lista de produtos com seleção múltipla
        modelListaProdutos = new DefaultListModel<>();
        produtos.forEach(produto -> modelListaProdutos.addElement(produto.getNome()));
        listaProdutos = new JList<>(modelListaProdutos);
        listaProdutos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaProdutos);
        add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel botoesPanel = new JPanel(new FlowLayout());

        // Botão Adicionar ao Orçamento
        JButton btnAdicionar = new JButton("Adicionar ao Orçamento");
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarSelecionadosAoOrcamento();
            }
        });
        botoesPanel.add(btnAdicionar);

        // Botão para cadastrar novo produto
        JButton btnCadastrarNovo = new JButton("Novo Produto");
        btnCadastrarNovo.addActionListener(new OpenTelaProduto());
        btnCadastrarNovo.setIcon(new ImageIcon("path/to/icon.png")); // Coloque o caminho do ícone aqui
        btnCadastrarNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastroProduto().setVisible(true);
            }
        });
        botoesPanel.add(btnCadastrarNovo);

        add(botoesPanel, BorderLayout.SOUTH);
    }

    private void adicionarSelecionadosAoOrcamento() {
        // Obter produtos selecionados pelo nome
        List<String> produtosSelecionadosNomes = listaProdutos.getSelectedValuesList();
        
        // Mapear os nomes para os objetos Produto correspondentes
        List<Produto> produtosSelecionados = produtosCadastrados.stream()
            .filter(produto -> produtosSelecionadosNomes.contains(produto.getNome()))
            .collect(Collectors.toList());

        // Adicionar os produtos ao orçamento usando a instância de TelaOrcamento
        produtosSelecionados.forEach(produto -> telaOrcamento.addProduto(produto));

        // Fechar a tela de seleção
        dispose();
    }
}
