package src.main.java.com.mycompany.project.telas;

import src.main.java.com.mycompany.project.entities.fromMoney.Empreita;
import src.main.java.com.mycompany.project.dao.ServicosDAO;
import src.main.java.com.mycompany.project.entities.fromMoney.Diaria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaServico extends JFrame {

    private JComboBox<String> tipoServicoDropdown;
    private JPanel painelCampos;
    private JTextField nomeServicoField;
    private JTextField valorEmpreitaField;
    private JTextField diasParaTerminarField;
    private JTextField valorDiaField;
    private JTextField diasField;
    private JButton salvarButton;

    public TelaServico() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Serviço");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel superior com título e dropdown
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JLabel tituloLabel = new JLabel("Cadastro de Serviço", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(50, 50, 150));

        tipoServicoDropdown = new JComboBox<>(new String[]{"Selecione", "Empreita", "Diária"});
        tipoServicoDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        tipoServicoDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCampos();
            }
        });

        topPanel.add(tituloLabel, BorderLayout.NORTH);
        topPanel.add(tipoServicoDropdown, BorderLayout.SOUTH);

        // Painel de campos dinâmicos
        painelCampos = new JPanel();
        painelCampos.setLayout(new GridLayout(6, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createTitledBorder("Detalhes do Serviço"));

        // Campos para Empreita e Diária
        nomeServicoField = new JTextField();
        valorEmpreitaField = new JTextField();
        diasParaTerminarField = new JTextField();
        valorDiaField = new JTextField();
        diasField = new JTextField();

        // Botão Salvar com estilo
        salvarButton = new JButton("Salvar");
        salvarButton.setFont(new Font("Arial", Font.BOLD, 16));
        salvarButton.setBackground(new Color(70, 130, 180));
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFocusPainted(false);
        salvarButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 50, 150), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarServico();
            }
        });

        // Adiciona os painéis ao painel principal
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(painelCampos, BorderLayout.CENTER);
        mainPanel.add(salvarButton, BorderLayout.SOUTH);

        add(mainPanel);
        atualizarCampos();
    }

    private void atualizarCampos() {
        painelCampos.removeAll();
        String tipoSelecionado = (String) tipoServicoDropdown.getSelectedItem();

        painelCampos.add(new JLabel("Nome do Serviço:", JLabel.RIGHT));
        painelCampos.add(nomeServicoField);

        if ("Empreita".equals(tipoSelecionado)) {
            painelCampos.add(new JLabel("Valor da Empreita:", JLabel.RIGHT));
            painelCampos.add(valorEmpreitaField);
            painelCampos.add(new JLabel("Dias para Terminar:", JLabel.RIGHT));
            painelCampos.add(diasParaTerminarField);
        } else if ("Diária".equals(tipoSelecionado)) {
            painelCampos.add(new JLabel("Valor por Dia:", JLabel.RIGHT));
            painelCampos.add(valorDiaField);
            painelCampos.add(new JLabel("Número de Dias:", JLabel.RIGHT));
            painelCampos.add(diasField);
        }

        painelCampos.revalidate();
        painelCampos.repaint();
    }

    private void salvarServico() {
        String tipoSelecionado = (String) tipoServicoDropdown.getSelectedItem();
        String nomeServico = nomeServicoField.getText();

        if ("Empreita".equals(tipoSelecionado)) {
            try {
                double valorEmpreita = Double.parseDouble(valorEmpreitaField.getText());
                int diasParaTerminar = Integer.parseInt(diasParaTerminarField.getText());

                Empreita empreita = new Empreita(valorEmpreita);
                empreita.setNomeServico(nomeServico);
                empreita.setDiasParaTerminar(diasParaTerminar);

                ServicosDAO.addServico(empreita);
                JOptionPane.showMessageDialog(this, "Serviço de Empreita cadastrado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para Empreita.");
            }
        } else if ("Diária".equals(tipoSelecionado)) {
            try {
                double valorDia = Double.parseDouble(valorDiaField.getText());
                int dias = Integer.parseInt(diasField.getText());

                Diaria diaria = new Diaria();
                diaria.setNomeServico(nomeServico);
                diaria.setValorDia(valorDia);
                diaria.setDias(dias);

                ServicosDAO.addServico(diaria);
                JOptionPane.showMessageDialog(this, "Serviço de Diária cadastrado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para Diária.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um tipo de serviço.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaServico().setVisible(true));
    }
}
