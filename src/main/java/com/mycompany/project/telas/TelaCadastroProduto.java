package src.main.java.com.mycompany.project.telas;
import src.main.java.com.mycompany.project.entities.Produto;
import src.main.java.com.mycompany.project.entities.Enums.Unidades;
import src.main.java.com.mycompany.project.entities.fromMoney.Totalizavel;
import src.main.java.com.mycompany.project.Exceptions.CampoEmBranco;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.java.com.mycompany.project.dao.ProdutoDAO;

import java.text.DecimalFormat;
import java.text.ParseException;
public class TelaCadastroProduto extends JFrame {
    private JTextField nomeField, precoField;
    private JTextField valor;
    private JTextField rendimentoField;
    private JComboBox<Unidades> unidades;
    private JButton cadastrarButton, limparButton, voltarButton;

    public TelaCadastroProduto() {
        configurarJanela();
        inicializarComponentes();
        adicionarComponentesAoLayout();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Cadastro de Produto");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(240, 248, 255));
    }

    private void inicializarComponentes() {
        unidades = new JComboBox<>(Unidades.values());
        nomeField = new JTextField(20);
        precoField = new JTextField(20);
        valor = new JTextField(20);
        rendimentoField = new JTextField(20);
        rendimentoField.setEnabled(false);

        cadastrarButton = new JButton("Cadastrar");
        limparButton = new JButton("Limpar");
        voltarButton = new JButton("Voltar");

        cadastrarButton.setBackground(new Color(60, 179, 113));
        cadastrarButton.setForeground(Color.WHITE);
        limparButton.setBackground(new Color(255, 165, 0));
        limparButton.setForeground(Color.WHITE);
        voltarButton.setBackground(new Color(70, 130, 180));
        voltarButton.setForeground(Color.WHITE);

        cadastrarButton.addActionListener(new CadastrarButtonListener());
        limparButton.addActionListener(e -> limparCampos());
        voltarButton.addActionListener(e -> this.dispose());
    }

    private void adicionarComponentesAoLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        int linha = 0;

        adicionarCampo("Nome:", nomeField, linha++, gbc);
        adicionarCampo("Preço:", precoField, linha++, gbc);
        adicionarCampo("Unidades:", unidades, linha++, gbc);
        adicionarCampo("Valor:", valor, linha++, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = linha++;
        gbc.gridx = 0;
        add(voltarButton, gbc);
        gbc.gridx = 1;
        add(limparButton, gbc);
        gbc.gridx = 2;
        add(cadastrarButton, gbc);
    }

    private void adicionarCampo(String label, JTextField textField, int linha, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = linha;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(textField, gbc);
    }

    private void adicionarCampo(String label, JComboBox<Unidades> comboBox, int linha, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = linha;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(comboBox, gbc);
    }

    private void limparCampos() {
        nomeField.setText("");
        precoField.setText("");
        valor.setText("");
    }

    private class CadastrarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Produto produto = criarProduto();
                ProdutoDAO.gravarProduto(produto);
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                limparCampos();
            } catch (CampoEmBranco | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        }
    }

    private Produto criarProduto() throws CampoEmBranco {
        verificarCampos();

        // Formatar o valor do preço com duas casas decimais
        double preco = formatarPreco(precoField.getText());
        double valorProduto = Double.parseDouble(valor.getText());

        if (valorProduto < 0) {
            throw new CampoEmBranco("Campo 'Valor' não pode ser negativo!");
        }

        return new Produto(
            nomeField.getText(),
            (Unidades) unidades.getSelectedItem(),
            preco,
            valorProduto
        );
    }

    private double formatarPreco(String precoStr) {
        try {
            DecimalFormat df = new DecimalFormat("#.00");
            df.setParseBigDecimal(true);
            return df.parse(precoStr.replace(",", ".")).doubleValue();
        } catch (ParseException e) {
            throw new NumberFormatException("Preço inválido. Use o formato correto.");
        }
    }

    private void verificarCampos() throws CampoEmBranco {
        if (nomeField.getText().isEmpty()) throw new CampoEmBranco("Campo 'Nome' é obrigatório!");
        if (precoField.getText().isEmpty()) throw new CampoEmBranco("Campo 'Preço' é obrigatório!");
        if (valor.getText().isEmpty()) throw new CampoEmBranco("Campo 'Valor' é obrigatório!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaCadastroProduto::new);
    }
}