// package src.main.java.com.mycompany.project.telas;
// import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.stream.Collectors;
// import src.main.java.com.mycompany.project.entities.fromMoney.Orcamento;
// import src.main.java.com.mycompany.project.services.*;
// import src.main.java.com.mycompany.project.services.FileGenerator;

// public class ExportServico implements ActionListener {
//     private Orcamento orcamento;
//     private JTextField txtData;
//     private JTextArea campoDescricao;

//     // Construtor para inicializar os campos necessários
//     public ExportServico(Orcamento orcamento, JTextField txtData, JTextArea campoDescricao) {
//         this.orcamento = orcamento;
//         this.txtData = txtData;
//         this.campoDescricao = campoDescricao;
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         // Verificar se o orçamento possui itens e detalhes estão preenchidos
//         if (orcamento.getItens().size() == 0) {
//             JOptionPane.showMessageDialog(null, "O orçamento não possui itens");
//             return;
//         }
//         if (txtData.getText().equals("  /  /    ")) {
//             JOptionPane.showMessageDialog(null, "A data do orçamento não foi preenchida");
//             return;
//         }
//         if (campoDescricao.getText().equals("")) {
//             JOptionPane.showMessageDialog(null, "A descrição do orçamento não foi preenchida");
//             return;
//         }

//         // Exibir diálogo para escolher entre CSV e PDF
//         String[] options = {"CSV", "PDF"};
//         int escolha = JOptionPane.showOptionDialog(
//                 null,
//                 "Escolha o formato de exportação",
//                 "Exportar Orçamento",
//                 JOptionPane.DEFAULT_OPTION,
//                 JOptionPane.INFORMATION_MESSAGE,
//                 null,
//                 options,
//                 options[0]
//         );

//         // Preparar conteúdo do orçamento para exportação
//         StringBuilder conteudo = new StringBuilder();
//         conteudo.append("Data: ").append(txtData.getText()).append("\n");
//         conteudo.append("Descrição: ").append(campoDescricao.getText()).append("\n\n");
//         conteudo.append("Itens:\n");
//         conteudo.append("Nome, Preço\n");
//         conteudo.append(orcamento.getItens().stream()
//                 .map(item -> item.getNomeItem() + ", R$ " + String.format("%.2f", item.getValorTotal()))
//                 .collect(Collectors.joining("\n")));

//         // Verificar escolha do usuário e chamar o método de exportação correspondente
//         if (escolha == 0) { // CSV
//             FileGenerator.generateCsv(conteudo.toString(), "BancoDeDados/CSVs");
//             JOptionPane.showMessageDialog(null, "Orçamento exportado como CSV com sucesso!");
//         } else if (escolha == 1) { // PDF
//             FileGenerator.generatePdf(conteudo.toString(), "BancoDeDados/Pdfs");
//             JOptionPane.showMessageDialog(null, "Orçamento exportado como PDF com sucesso!");
//         }
//     }
// }
