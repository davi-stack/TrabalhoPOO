package src.main.java.com.mycompany.project.telas;

import src.main.java.com.mycompany.project.dao.PrestadorDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {

    private JButton jButtonEntrar;
    private JButton jButtonCadastro;
    private JLabel jLabel1;
    private JPanel jPanelLogin;
    private JPasswordField jPasswordField1;
    private JTextField jTextField1;

    public TelaLogin() {
        initComponents();
    }

    private void initComponents() {
        jPanelLogin = new JPanel();
        jLabel1 = new JLabel("Login");
        jButtonEntrar = new JButton("Entrar");
        jButtonCadastro = new JButton("Cadastrar");
        jPasswordField1 = new JPasswordField(15);
        jTextField1 = new JTextField(15);

        // Configuração da janela principal
        setTitle("Tela de Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Tela cheia
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel de fundo azul ocupando toda a tela
        JPanel jPanelBackground = new JPanel();
        jPanelBackground.setLayout(new GridBagLayout());
        jPanelBackground.setBackground(new Color(0, 123, 255)); // Azul

        // Configuração do painel de login centralizado
        jPanelLogin.setBackground(Color.WHITE);
        jPanelLogin.setPreferredSize(new Dimension(300, 300)); // Formato retangular
        jPanelLogin.setLayout(new GridBagLayout());

        // Configuração dos componentes de texto e campos
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setForeground(new Color(0, 123, 255));

        jTextField1.setToolTipText("E-mail");
        jPasswordField1.setToolTipText("Senha");

        // Configuração dos botões
        Dimension buttonSize = new Dimension(120, 35); // Tamanho uniforme para os botões
        jButtonEntrar.setPreferredSize(buttonSize);
        jButtonEntrar.setBackground(new Color(0, 123, 255));
        jButtonEntrar.setForeground(Color.WHITE);
        jButtonEntrar.setFocusPainted(false);

        jButtonCadastro.setPreferredSize(buttonSize);
        jButtonCadastro.setBackground(new Color(0, 123, 255));
        jButtonCadastro.setForeground(Color.WHITE);
        jButtonCadastro.setFocusPainted(false);

        // Layout do painel de login
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        jPanelLogin.add(jLabel1, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        jPanelLogin.add(new JLabel("E-mail:"), gbc);

        gbc.gridx = 1;
        jPanelLogin.add(jTextField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        jPanelLogin.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        jPanelLogin.add(jPasswordField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        jPanelLogin.add(jButtonEntrar, gbc);

        gbc.gridy = 4;
        jPanelLogin.add(jButtonCadastro, gbc);

        // Adicionar o painel de login ao painel de fundo centralizado
        jPanelBackground.add(jPanelLogin);

        // Adicionar o painel de fundo ao frame principal
        setLayout(new BorderLayout());
        add(jPanelBackground, BorderLayout.CENTER);

        // Ação do botão Entrar
        jButtonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean flag = PrestadorDAO.autenticarPrestador(jTextField1.getText(), new String(jPasswordField1.getPassword()));
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
                    return;
                }
                jButton2ActionPerformed(evt);
            }
        });

        // Ação do botão Cadastro
        jButtonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                TelaCadastro telaCadastro = new TelaCadastro();
                telaCadastro.setVisible(true);
            }
        });
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(TelaLogin::new);
    }
}
