package src.main.java.com.mycompany.project.telas;

import src.main.java.com.mycompany.project.entities.Cliente;
import src.main.java.com.mycompany.project.entities.Enums.Estado;
import src.main.java.com.mycompany.project.entities.Endereco;
import src.main.java.com.mycompany.project.dao.ClienteDAO;
import javax.swing.JOptionPane;
/**
 *
 * @author Joao Vitor Dantas
 */
public class TelaCliente extends javax.swing.JFrame {

    // Variáveis de componentes da interface
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldCPF;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextTelefone;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelTelefone;

    // //campos para cidades, estado e CEP
    // Campos adicionais para cidade, estado e CEP
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JComboBox<Estado> jComboBoxEstado;
    private javax.swing.JTextField jTextFieldCEP;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelCEP;
    


    public TelaCliente() {
        initComponents();
    }
    
    private void CadastrarCliente(Cliente c) {
        // Cadastrar cliente chamando o método de gravação
        
        try {
            ClienteDAO.gravarCliente(c);
        } catch (Exception e) {
            e.printStackTrace();
            // Mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente!");
            return;
        }
        // Mensagem de sucesso
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
    }
    private void initComponents() {
        // Instância dos componentes da interface
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelNome = new javax.swing.JLabel("Nome");
        jLabelCPF = new javax.swing.JLabel("CPF");
        jLabelEmail = new javax.swing.JLabel("E-mail");
        jLabelEndereco = new javax.swing.JLabel("Endereço");
        jLabelTelefone = new javax.swing.JLabel("Telefone");
        jLabelCidade = new javax.swing.JLabel("Cidade");
        jLabelEstado = new javax.swing.JLabel("Estado");
        jLabelCEP = new javax.swing.JLabel("CEP");
    
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldCPF = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldEndereco = new javax.swing.JTextField();
        jTextTelefone = new javax.swing.JTextField();
        jTextFieldCidade = new javax.swing.JTextField();
        jComboBoxEstado = new javax.swing.JComboBox<>(Estado.values());
        jTextFieldCEP = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
    
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jButton1.setText("Voltar");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));
    
        jLabel2 = new javax.swing.JLabel("Novo Cliente");
    
        // Configuração de placeholder para os campos de texto
        jTextFieldNome.setToolTipText("Nome");
        jTextFieldCPF.setToolTipText("CPF");
        jTextFieldEmail.setToolTipText("E-mail");
        jTextFieldEndereco.setToolTipText("Endereço (logradouro)");
        jTextTelefone.setToolTipText("Telefone");
        jTextFieldCidade.setToolTipText("Cidade");
        jComboBoxEstado.setToolTipText("Estado");
        jTextFieldCEP.setToolTipText("CEP");
    
        // Tornar os campos vazios inicialmente
        jTextFieldNome.setText("");
        jTextFieldCPF.setText("");
        jTextFieldEmail.setText("");
        jTextFieldEndereco.setText("");
        jTextTelefone.setText("");
        jTextFieldCidade.setText("");
        jTextFieldCEP.setText("");
    
        jButton2.setText("Cadastrar");
        jButton2.addActionListener(evt -> {
            Cliente c = new Cliente();
            c.setNome(jTextFieldNome.getText());
            c.setCpf(jTextFieldCPF.getText());
            c.setEmail(jTextFieldEmail.getText());
            Estado estadoSelecionado = Estado.valueOf(jComboBoxEstado.getSelectedItem().toString());
            Endereco endereco = new Endereco(jTextFieldEndereco.getText(), jTextFieldCidade.getText(),
                                             estadoSelecionado, jTextFieldCEP.getText());
            c.setEndereco(endereco);
            c.setTelefone(jTextTelefone.getText());
            CadastrarCliente(c);
            limparCampos();
        });
    
        configurarLayout();
    }
    
    void configurarLayout() {
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
    
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelNome)
                                .addComponent(jLabelCPF)
                                .addComponent(jLabelEmail)
                                .addComponent(jLabelEndereco)
                                .addComponent(jLabelTelefone)
                                .addComponent(jLabelCidade)
                                .addComponent(jLabelEstado)
                                .addComponent(jLabelCEP))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldNome)
                                .addComponent(jTextFieldCPF)
                                .addComponent(jTextFieldEmail)
                                .addComponent(jTextFieldEndereco)
                                .addComponent(jTextTelefone)
                                .addComponent(jTextFieldCidade)
                                .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelNome)
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelCPF)
                        .addComponent(jTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelEmail)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelEndereco)
                        .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelTelefone)
                        .addComponent(jTextTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelCidade)
                        .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelEstado)
                        .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelCEP)
                        .addComponent(jTextFieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jButton2)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                    .addContainerGap(20, Short.MAX_VALUE))
        );
    
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    
        pack();
    }
    
    private void limparCampos() {
        //lavel do lado do campo
        
        jTextFieldNome.setText("");
        jTextFieldCPF.setText("");
        jTextFieldEmail.setText("");
        jTextFieldEndereco.setText("");
        jTextTelefone.setText("");
        jTextFieldCidade.setText("");
        jComboBoxEstado.setSelectedIndex(0);
        jTextFieldCEP.setText("");
        
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaCliente().setVisible(true));
    }
}
