package src.main.java.com.mycompany.project.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class FileGenerator {

    private static final String CSV_FILE_PATH = "output.csv";
    private static final String PDF_FILE_PATH = "output.pdf";

    // Gera um arquivo CSV a partir de uma string
    public static void generateCSV(String content) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH))) {
            writer.println(content);
            System.out.println("Arquivo CSV gerado com sucesso em: " + CSV_FILE_PATH);
        } catch (IOException e) {
            handleException("CSV", e);
        }
    }

    // Gera um arquivo PDF a partir de uma string
    public static void generatePDF(String content) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700); // Posição do texto
                contentStream.showText(content);
                contentStream.endText();
            }

            document.save(PDF_FILE_PATH);
            System.out.println("Arquivo PDF gerado com sucesso em: " + PDF_FILE_PATH);
        } catch (IOException e) {
            handleException("PDF", e);
        }
    }

    // Método auxiliar para tratar exceções
    private static void handleException(String fileType, Exception e) {
        System.err.println("Erro ao gerar o arquivo " + fileType + ": " + e.getMessage());
    }
}
