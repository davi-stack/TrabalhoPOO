package src.main.java.com.mycompany.project.telas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.java.com.mycompany.project.entities.Prestador;
import src.main.java.com.mycompany.project.entities.Endereco;
import src.main.java.com.mycompany.project.entities.Enums.Estado;
import src.main.java.com.mycompany.project.Exceptions.CampoEmBranco;
import src.main.java.com.mycompany.project.dao.PrestadorDAO;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class TelaCadastro extends JFrame {

    private JTextField nomeField;
    private JTextField emailField;
    private JFormattedTextField telefoneField;
    private JTextField usernameField;
    private JPasswordField senhaField;
    private JTextField logradouroField;
    private JTextField cidadeField;
    private JComboBox<Estado> estadoComboBox;
    private JFormattedTextField cepField;
    private JButton voltarButton;
    private JButton cadastrarButton;

    private void inicializarComponentes() {
        nomeField = new JTextField();
        emailField = new JTextField();
        
        // Máscara para Telefone
        telefoneField = createFormattedTextField("(##) #####-####");
        
        usernameField = new JTextField();
        senhaField = new JPasswordField();
        logradouroField = new JTextField();
        cidadeField = new JTextField();
        estadoComboBox = new JComboBox<>(Estado.values());

        // Máscara para CEP
        cepField = createFormattedTextField("#####-###");

        voltarButton = new JButton("Voltar");
        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new CadastrarButtonListener());
        cadastrarButton.setBackground(new Color(60, 179, 113));
        cadastrarButton.setForeground(Color.WHITE);
        
        voltarButton.addActionListener(new VoltarListener());
    }

    private void adicionarComponentesAoLayout() {
        setLayout(new GridLayout(11, 2, 5, 5));  // Organiza em 11 linhas e 2 colunas

        add(new JLabel("Nome:"));
        add(nomeField);

        add(new JLabel("Email:"));
        add(emailField);

        add(new JLabel("Telefone:"));
        add(telefoneField);

        add(new JLabel("Username:"));
        add(usernameField);

        add(new JLabel("Senha:"));
        add(senhaField);

        add(new JLabel("Logradouro:"));
        add(logradouroField);

        add(new JLabel("Cidade:"));
        add(cidadeField);

        add(new JLabel("Estado:"));
        add(estadoComboBox);

        add(new JLabel("CEP:"));
        add(cepField);

        add(voltarButton);           // Botão de voltar
        add(cadastrarButton);        // Botão de cadastro
    }

    public TelaCadastro() {
        configurarJanela();
        inicializarComponentes();
        adicionarComponentesAoLayout();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Cadastro de Prestador");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Método para criar campos formatados (CEP e Telefone)
    private JFormattedTextField createFormattedTextField(String mask) {
        try {
            MaskFormatter formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
            return new JFormattedTextField(formatter);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JFormattedTextField();
        }
    }

    public void checkCadastro() throws CampoEmBranco {
        if (nomeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo 'Nome' é obrigatório!");
            throw new CampoEmBranco("Campo 'Nome' é obrigatório!");
        }
        if (emailField.getText().isEmpty() || !emailField.getText().contains("@")) {
            JOptionPane.showMessageDialog(this, "Campo 'Email' é obrigatório e deve conter '@'!");
            throw new CampoEmBranco("Campo 'Email' é obrigatório e deve conter '@'!");
        }
        if (telefoneField.getText().trim().isEmpty() || telefoneField.getText().contains("_")) {
            JOptionPane.showMessageDialog(this, "Campo 'Telefone' é obrigatório!");
            throw new CampoEmBranco("Campo 'Telefone' é obrigatório!");
        }
        if (usernameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo 'Username' é obrigatório!");
            throw new CampoEmBranco("Campo 'Username' é obrigatório!");
        }
        if (senhaField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Campo 'Senha' é obrigatório!");
            throw new CampoEmBranco("Campo 'Senha' é obrigatório!");
        }
        if (logradouroField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo 'Logradouro' é obrigatório!");
            throw new CampoEmBranco("Campo 'Logradouro' é obrigatório!");
        }
        if (cidadeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo 'Cidade' é obrigatório!");
            throw new CampoEmBranco("Campo 'Cidade' é obrigatório!");
        }
        if (cepField.getText().trim().isEmpty() || cepField.getText().contains("_")) {
            JOptionPane.showMessageDialog(this, "Campo 'CEP' é obrigatório!");
            throw new CampoEmBranco("Campo 'CEP' é obrigatório!");
        }
    }

    private class VoltarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
    private void limparCampo() {
        nomeField.setText("");
        emailField.setText("");
        telefoneField.setText("");
        usernameField.setText("");
        senhaField.setText("");
        logradouroField.setText("");
        cidadeField.setText("");
        estadoComboBox.setSelectedIndex(0);
        cepField.setText("");
    }

    private class CadastrarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                checkCadastro();
                
                String nome = nomeField.getText();
                String email = emailField.getText();
                String telefone = telefoneField.getText();
                String username = usernameField.getText();
                String senha = new String(senhaField.getPassword());
                String logradouro = logradouroField.getText();
                String cidade = cidadeField.getText();
                Estado estado = (Estado) estadoComboBox.getSelectedItem();
                String cep = cepField.getText();

                Endereco endereco = new Endereco(logradouro, cidade, estado, cep);
                Prestador prestador = new Prestador(nome, email, telefone, username, senha, endereco);

                PrestadorDAO.gravarPrestador(prestador);
                JOptionPane.showMessageDialog(TelaCadastro.this, "Prestador cadastrado com sucesso!");
                limparCampo();

            } catch (CampoEmBranco exception) {
                System.out.println(exception.getMessage());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(TelaCadastro.this, "Erro ao cadastrar prestador: " + exception.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaCadastro::new);
    }
}
