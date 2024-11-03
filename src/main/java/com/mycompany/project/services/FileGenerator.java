// FileGenerator.java
package src.main.java.com.mycompany.project.services;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
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
        } catch (IOException e) {
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
