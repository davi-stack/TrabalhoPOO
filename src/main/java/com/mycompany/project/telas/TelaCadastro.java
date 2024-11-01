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
public class TelaCadastro extends JFrame {

    // Componentes da interface
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField telefoneField;
    private JTextField usernameField;
    private JPasswordField senhaField;
    private JTextField logradouroField;
    private JTextField cidadeField;
    private JComboBox<Estado> estadoComboBox;
    private JTextField cepField;
    private JButton voltarButton;
    private JButton cadastrarButton;

    private void inicializarComponentes() {
        nomeField = new JTextField();
        emailField = new JTextField();
        telefoneField = new JTextField();
        usernameField = new JTextField();
        senhaField = new JPasswordField();
        logradouroField = new JTextField();
        cidadeField = new JTextField();
        estadoComboBox = new JComboBox<>(Estado.values());
        cepField = new JTextField();
        
        voltarButton = new JButton("Voltar");
        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new CadastrarButtonListener());
        cadastrarButton.setBackground(new Color(60, 179, 113));
        cadastrarButton.setForeground(Color.WHITE);
    }
    private void adicionarComponentesAoLayout() {
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
        voltarButton.addActionListener(new VoltarListener());
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
        setLayout(new GridLayout(11, 2, 5, 5));  // 11 linhas, 2 colunas, com espaçamento de 5 pixels
    }
    
    
    
    public void checkCadastro() throws CampoEmBranco {
        // Verifica se todos os campos foram preenchidos
        if (nomeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo 'Nome' é obrigatório!");
            throw new CampoEmBranco("Campo 'Nome' é obrigatório!");
        }
        if (emailField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo 'Email' é obrigatório!");
            throw new CampoEmBranco("Campo 'Email' é obrigatório!");
        }
        if (telefoneField.getText().isEmpty()) {
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
        if (cepField.getText().isEmpty()) {
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

    // Classe interna para lidar com o evento de clique no botão de cadastro
    private class CadastrarButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = nomeField.getText();
            String email = emailField.getText();
            String telefone = telefoneField.getText();
            String username = usernameField.getText();
            String senha = new String(senhaField.getPassword());
            String logradouro = logradouroField.getText();
            String cidade = cidadeField.getText();
            Estado estado = (Estado) estadoComboBox.getSelectedItem();
            String cep = cepField.getText();
            try{
                checkCadastro();
            }catch (CampoEmBranco exeption){
                System.out.println(exeption.getMessage());
                return;
            }

            // Criando objeto Endereco
            Endereco endereco = new Endereco(logradouro, cidade, estado, cep);

            // Criando objeto Prestador com os dados fornecidos
            Prestador prestador = new Prestador(nome, email, telefone, username, senha, endereco);
            try{
                PrestadorDAO.gravarPrestador(prestador);
            }catch (Exception exeption){
                System.out.println(exeption.getMessage());
                return;
            }
            // Exibindo confirmação de cadastro
            JOptionPane.showMessageDialog(TelaCadastro.this, "Prestador cadastrado com sucesso!");
            // Limpando os campos após o cadastro

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaCadastro::new);
    }
}
