// FileGenerator.java
package src.main.java.com.mycompany.project.services;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import com.opencsv.CSVWriter;

import src.main.java.com.mycompany.project.entities.fromMoney.Orcamento;
import src.main.java.com.mycompany.project.entities.fromMoney.Totalizavel;

import java.awt.Desktop;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.File;

public class FileGenerator {

    /**
     * Gera um arquivo PDF com o texto fornecido.
     *
     * @param text Conteúdo que será inserido no PDF.
     * @param filePath Caminho completo onde o arquivo PDF será salvo.
     */
    public static void generatePdf(String text, String filePath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
    
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
    
                // Carrega a fonte (atualize o caminho se necessário)
                PDType0Font font = PDType0Font.load(document, new File("C:\\Windows\\Fonts\\arial.ttf"));
                contentStream.setFont(font, 12);
                contentStream.newLineAtOffset(100, 700); // Posição inicial
    
                // Divide o texto em linhas com base nas quebras de linha
                String[] lines = text.split("\n");
                for (String line : lines) {
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -15); // Move para a próxima linha
                }
    
                contentStream.endText();
            }
    
            document.save(filePath);
            System.out.println("PDF gerado com sucesso em: " + filePath);
    
            // Abre o PDF automaticamente
            File pdfFile = new File(filePath);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("Abertura automática do PDF não suportada neste sistema.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void generatePdfFromOrcamento(Orcamento orcamento, String filePath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
    
            // Carregar as fontes
            PDType0Font font;
            PDType0Font boldFont;
            try {
                font = PDType0Font.load(document, new File("C:\\Windows\\Fonts\\arial.ttf"));
                boldFont = PDType0Font.load(document, new File("C:\\Windows\\Fonts\\arialbd.ttf"));
            } catch (IOException e) {
                System.out.println("Erro ao carregar fontes. Verifique o caminho das fontes Arial e Arial Negrito.");
                e.printStackTrace();
                return;
            }
    
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(font, 12);
    
                // Título
                contentStream.beginText();
                contentStream.setFont(boldFont, 16);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Orçamento Detalhado");
                contentStream.endText();
    
                // Informações principais do orçamento (com tratamento de null)
                contentStream.beginText();
                contentStream.setFont(font, 12);
                contentStream.newLineAtOffset(50, 720);
    
                contentStream.showText("ID: " + (orcamento.getId() != null ? orcamento.getId() : "Não especificado"));
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Cliente: " + (orcamento.getNomeCliente() != null ? orcamento.getNomeCliente() : "Não especificado"));
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Data: " + (orcamento.getData() != null ? orcamento.getData() : "Não especificado"));
                contentStream.newLineAtOffset(0, -15);

                // contentStream.showText("Validade: " + (orcamento.getDiasValidos() != null ? orcamento.getDiasValidos() + " dias" : "Não especificado"));
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Descrição: " + (orcamento.getDescricao() != null ? orcamento.getDescricao() : "Não especificada"));
                contentStream.endText();
    
                // Tabela de Itens
                float tableTopY = 650;
                float marginLeft = 50;
                float marginRight = page.getMediaBox().getWidth() - 50;
                float rowHeight = 20;
                float cellMargin = 5;
    
                // Cabeçalho da Tabela
                contentStream.setFont(boldFont, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(marginLeft + cellMargin, tableTopY);
                contentStream.showText("Item");
                contentStream.endText();
    
                contentStream.beginText();
                contentStream.newLineAtOffset(marginLeft + 300 + cellMargin, tableTopY);
                contentStream.showText("Preço");
                contentStream.endText();
    
                // Desenhar linhas da tabela (Cabeçalho)
                contentStream.setLineWidth(1f);
                contentStream.moveTo(marginLeft, tableTopY + 5);
                contentStream.lineTo(marginRight, tableTopY + 5);
                contentStream.stroke();
    
                contentStream.moveTo(marginLeft, tableTopY - rowHeight + 5);
                contentStream.lineTo(marginRight, tableTopY - rowHeight + 5);
                contentStream.stroke();
    
                // Adicionar Itens à Tabela (com tratamento de null)
                contentStream.setFont(font, 10);
                float currentY = tableTopY - rowHeight;
    
                for (Totalizavel item : orcamento.getItens()) {
                    String nomeItem = item.getNomeItem() != null ? item.getNomeItem() : "Item não especificado";
                    String valorItem;
                    try {
                         valorItem = String.format("R$ %.2f", item.getValorTotal());
                    } catch (NullPointerException e) {
                        valorItem = "Valor não especificado";
                    }
                    // String valorItem = item.getValorTotal() != null ? String.format("R$ %.2f", item.getValorTotal()) : "Valor não especificado";
    
                    // Nome do Item
                    contentStream.beginText();
                    contentStream.newLineAtOffset(marginLeft + cellMargin, currentY - cellMargin);
                    contentStream.showText(nomeItem);
                    contentStream.endText();
    
                    // Preço do Item
                    contentStream.beginText();
                    contentStream.newLineAtOffset(marginLeft + 300 + cellMargin, currentY - cellMargin);
                    contentStream.showText(valorItem);
                    contentStream.endText();
    
                    // Desenhar linhas da tabela
                    currentY -= rowHeight;
                    contentStream.moveTo(marginLeft, currentY + 5);
                    contentStream.lineTo(marginRight, currentY + 5);
                    contentStream.stroke();
                }
    
                // Linha final da tabela
                contentStream.moveTo(marginLeft, currentY + 5);
                contentStream.lineTo(marginRight, currentY + 5);
                contentStream.stroke();
    
                // Total do orçamento (com tratamento de null)
                contentStream.beginText();
                contentStream.setFont(boldFont, 12);
                contentStream.newLineAtOffset(marginLeft, currentY - 20);
                try {
                    contentStream.showText("Total: " + String.format("R$ %.2f", orcamento.getValorTotal()));
                } catch (NullPointerException e) {
                    contentStream.showText("Total: Não especificado");
                }
                // contentStream.showText("Total: " + (orcamento.getValorTotal() != null ? String.format("R$ %.2f", orcamento.getValorTotal()) : "Não especificado"));
                contentStream.endText();
    
                // Observações (com tratamento de null)
                contentStream.beginText();
                contentStream.setFont(boldFont, 12);
                contentStream.newLineAtOffset(marginLeft, currentY - 60);
                contentStream.showText("Observações:");
                contentStream.endText();
    
                currentY -= 75;
                contentStream.setFont(font, 10);
                List<String> observacoes = orcamento.getObs();
                if (observacoes != null && !observacoes.isEmpty()) {
                    for (int i = 0; i < observacoes.size(); i++) {
                        contentStream.beginText();
                        contentStream.newLineAtOffset(marginLeft + cellMargin, currentY);
                        contentStream.showText("- " + observacoes.get(i));
                        contentStream.endText();
                        currentY -= rowHeight;
                    }
                } else {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(marginLeft + cellMargin, currentY);
                    contentStream.showText("Nenhuma observação disponível.");
                    contentStream.endText();
                }
            }
    
            // Salva o documento PDF
            document.save(filePath);
            System.out.println("PDF gerado com sucesso em: " + filePath);
    
            // Abre o PDF automaticamente
            File pdfFile = new File(filePath);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("Abertura automática do PDF não suportada neste sistema.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o PDF. Verifique os detalhes.");
            e.printStackTrace();
        }
    }

    /**
     * Gera um arquivo CSV com o texto fornecido.
     *
     * @param text Conteúdo que será inserido no CSV.
     * @param filePath Caminho completo onde o arquivo CSV será salvo.
     */
    public static void generateCsv(String text, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            String[] line = { text };  // Cria uma linha com o texto
            writer.writeNext(line);    // Escreve a linha no CSV
            System.out.println("CSV gerado com sucesso em: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso dos métodos
        String texto = "Este é o conteúdo do arquivo.";
        
        // Gerar PDF
        generatePdf(texto, "arquivo.pdf");
        
        // Gerar CSV
        generateCsv(texto, "arquivo.csv");
        
        System.out.println("Arquivos gerados com sucesso!");
    }
}
