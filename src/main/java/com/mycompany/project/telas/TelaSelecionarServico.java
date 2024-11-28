package src.main.java.com.mycompany.project.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import src.main.java.com.mycompany.project.entities.fromMoney.Servico;
import java.util.List;
import src.main.java.com.mycompany.project.dao.ServicosDAO;
public class TelaSelecionarServico extends JFrame {
    private JList<String> listaServicos;
    private DefaultListModel<String> modelListaServicos;
    private List<Servico> servicosCadastrados;
    private TelaOrcamento telaOrcamento;  // Instância de TelaOrcamento para chamada do método

    public class OpenTelaServico implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaServico().setVisible(true);
        }
    }

    public TelaSelecionarServico(List<Servico> servicos, TelaOrcamento telaOrcamento) {
        this.servicosCadastrados = ServicosDAO.readServicos();
        this.telaOrcamento = telaOrcamento;
       
        setTitle("Selecionar Serviços");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Serviços Disponíveis", JLabel.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        // Lista de serviços com seleção múltipla
        modelListaServicos = new DefaultListModel<>();
        servicos.forEach(servico -> modelListaServicos.addElement(servico.getNomeItem()));
        listaServicos = new JList<>(modelListaServicos);
        listaServicos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaServicos);
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

        // Botão para cadastrar novo serviço
        JButton btnCadastrarNovo = new JButton("Novo Serviço");
        btnCadastrarNovo.setIcon(new ImageIcon("path/to/icon.png")); // Coloque o caminho do ícone aqui
        btnCadastrarNovo.addActionListener(new OpenTelaServico());
        botoesPanel.add(btnCadastrarNovo);

        add(botoesPanel, BorderLayout.SOUTH);
    }

    private void adicionarSelecionadosAoOrcamento() {
        // Obter serviços selecionados pelo nome
        List<String> servicosSelecionadosNomes = listaServicos.getSelectedValuesList();
        
        // Mapear os nomes para os objetos Servico correspondentes
        List<Servico> servicosSelecionados = servicosCadastrados.stream()
            .filter(servico -> servicosSelecionadosNomes.contains(servico.getNomeItem()))
            .collect(Collectors.toList());

        // Adicionar os serviços ao orçamento usando a instância de TelaOrcamento
        servicosSelecionados.forEach(servico -> telaOrcamento.addServico(servico));

        // Fechar a tela de seleção
        dispose();
    }
}
