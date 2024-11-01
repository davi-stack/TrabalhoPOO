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
public class TelaCadastroProduto extends JFrame {

    private JTextField  nomeField, precoField, rendimentoField, comprimentoField, litrosField, unidadesField;
    private JTextField metroLinearField, metroQuadradoField, metroCubicoField;
    private JButton cadastrarButton, limparButton, voltarButton;
    private JComboBox unidades;

    public TelaCadastroProduto() {
        configurarJanela();
        inicializarComponentes();
        adicionarComponentesAoLayout();
        setVisible(true);
    }
    private class checkUnidadeChange implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Se nenhuma unidade for selecionada, desabilita todos os campos
            
            if (unidades.getSelectedItem().equals(Unidades.UNIDADE)) {
                metroLinearField.setEnabled(false);
                metroQuadradoField.setEnabled(false);
                metroCubicoField.setEnabled(false);
                unidadesField.setEnabled(true);
                return;
            } 
            if(unidades.getSelectedItem().equals(Unidades.METROLINEAR)){
                metroLinearField.setEnabled(true);
                metroQuadradoField.setEnabled(false);
                metroCubicoField.setEnabled(false);
                unidadesField.setEnabled(false);
                return;
            }
            if(unidades.getSelectedItem().equals(Unidades.METROQUADRADO)){
                metroLinearField.setEnabled(false);
                metroQuadradoField.setEnabled(true);
                metroCubicoField.setEnabled(false);
                unidadesField.setEnabled(false);
                return;
            }
            if(unidades.getSelectedItem().equals(Unidades.METROCUBICO)){
                metroLinearField.setEnabled(false);
                metroQuadradoField.setEnabled(false);
                metroCubicoField.setEnabled(true);
                unidadesField.setEnabled(false);
                return;
            }
            

        }
    }

    private void configurarJanela() {
        setTitle("Cadastro de Produto");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(240, 248, 255)); // Cor de fundo
    }

    private void inicializarComponentes() {
        unidades = new JComboBox(Unidades.values());
        nomeField = new JTextField(20);
        precoField = new JTextField(20);
        rendimentoField = new JTextField(20);
        comprimentoField = new JTextField(20);
        litrosField = new JTextField(20);
        unidadesField = new JTextField(20);
        metroLinearField = new JTextField(20);
        metroQuadradoField = new JTextField(20);
        metroCubicoField = new JTextField(20);

        cadastrarButton = new JButton("Cadastrar");
        limparButton = new JButton("Limpar");
        voltarButton = new JButton("Voltar");

        // Estilização dos botões
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
        adicionarCampo("Rendimento:", rendimentoField, linha++, gbc);
        adicionarCampo("Comprimento:", comprimentoField, linha++, gbc);
        // adicionarCampo("Litros:", litrosField, linha++, gbc);
        // adicionarCampo("Unidades:", unidadesField, linha++, gbc);
        // adicionarCampo("Metro Linear:", metroLinearField, linha++, gbc);
        // adicionarCampo("Metro Quadrado:", metroQuadradoField, linha++, gbc);
        // adicionarCampo("Metro Cúbico:", metroCubicoField, linha++, gbc);

        // Adicionando os botões na parte inferior
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
    private void adicionarCampo(String label, JComboBox comboBox, int linha, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = linha;
        add(new JLabel(label), gbc);

        gbc.gridx = 1;
        add(comboBox, gbc);
    }
    private void limparCampos() {
        nomeField.setText("");
        precoField.setText("");
        rendimentoField.setText("");
        comprimentoField.setText("");
        litrosField.setText("");
        unidadesField.setText("");
        metroLinearField.setText("");
        metroQuadradoField.setText("");
        metroCubicoField.setText("");
    }

    private class CadastrarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lógica de cadastro, por exemplo:
            JOptionPane.showMessageDialog(TelaCadastroProduto.this, "Produto cadastrado com sucesso!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaCadastroProduto::new);
    }

}