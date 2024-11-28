package src.main.java.com.mycompany.project.telas;

import src.main.java.com.mycompany.project.entities.Cliente;
import src.main.java.com.mycompany.project.telas.TelaCliente;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private JComboBox<String> menuDropdown;
    private JButton abrirButton;
    private JLabel jLabel1;

    public TelaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        // Definindo o layout principal e estilos
        setTitle("Gerador de Orçamentos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));
        setLayout(new BorderLayout());

        // Painel principal para adicionar padding e bordas
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Label de título centralizado
        jLabel1 = new JLabel("Gerador de Orçamentos", SwingConstants.CENTER);
        jLabel1.setFont(new Font("SansSerif", Font.BOLD, 16));
        jLabel1.setForeground(new Color(60, 63, 65));
        mainPanel.add(jLabel1, BorderLayout.NORTH);

        // Painel para o menu dropdown e botão
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        controlPanel.setBackground(new Color(240, 240, 240));

        // Dropdown de opções
        menuDropdown = new JComboBox<>(new String[]{"Selecione", "Produtos", "Orçamento", "Clientes", "Serviços"});
        menuDropdown.setPreferredSize(new Dimension(150, 30));
        menuDropdown.setBackground(Color.WHITE);
        menuDropdown.setFont(new Font("SansSerif", Font.PLAIN, 14));
        controlPanel.add(menuDropdown);

        // Botão para abrir a seleção
        abrirButton = new JButton("Abrir");
        abrirButton.setPreferredSize(new Dimension(80, 30));
        abrirButton.setBackground(new Color(220, 220, 220));
        abrirButton.setFocusPainted(false);
        abrirButton.addActionListener(evt -> abrirMenuSelecionado());
        controlPanel.add(abrirButton);

        mainPanel.add(controlPanel, BorderLayout.CENTER);

        // Adicionando o painel principal ao JFrame
        add(mainPanel);

        pack();
    }

    private void abrirMenuSelecionado() {
        String opcaoSelecionada = (String) menuDropdown.getSelectedItem();

        switch (opcaoSelecionada) {
            case "Produtos":
                Produtos produtos = new Produtos();
                produtos.setVisible(true);
                break;
            case "Orçamento":
                TelaOrcamento orcamento = new TelaOrcamento();
                orcamento.setVisible(true);
                break;
            case "Clientes":
                TelaCliente cliente = new TelaCliente();
                cliente.setVisible(true);
                break;
            case "Serviços":
                TelaServico servico = new TelaServico();
                servico.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Selecione uma opção válida.");
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
