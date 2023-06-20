package br.com.ueg.prog.webi.ramal.util;

import br.com.ueg.prog.webi.ramal.model.RamalModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class GerarPdf {

    public byte[] gerarPdf(List<RamalModel> listaRamal) throws Exception {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float margin = 50;
            float pageWidth = page.getMediaBox().getWidth() - 2 * margin;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = pageWidth / 5f;
            float yPosition = yStart;

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);

            getCell(contentStream, margin, yPosition, pageWidth, "Controle de Ramal");
            yPosition -= 30;


            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            getCell(contentStream, margin, yPosition, tableWidth, "id");
            getCell(contentStream, margin + tableWidth, yPosition, tableWidth, "Ramal");
            getCell(contentStream, margin + 2 * tableWidth, yPosition, tableWidth, "Nome do Setor");
            getCell(contentStream, margin + 3 * tableWidth, yPosition, tableWidth, "Responsavel");
            getCell(contentStream, margin + 4 * tableWidth, yPosition, tableWidth, "Email");
            yPosition -= 10;

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);

            for (RamalModel ramalModel : listaRamal) {

                yPosition -= 20;

                getCell(contentStream, margin, yPosition, tableWidth, ramalModel.getId().toString());
                getCell(contentStream, margin + tableWidth, yPosition, tableWidth, ramalModel.getRamal().toString());
                getCell(contentStream, margin + 2 * tableWidth, yPosition, tableWidth, ramalModel.getNomeSetor());
                getCell(contentStream, margin + 3 * tableWidth, yPosition, tableWidth, ramalModel.getNomeResponsavelSetor());
                getCell(contentStream, margin + 4 * tableWidth, yPosition, tableWidth, ramalModel.getEmailDepartamento());
            }

            contentStream.close();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            document.close();

            return baos.toByteArray();
        }
    }

    private void getCell(PDPageContentStream contentStream, float x, float y, float width, String text)
            throws Exception {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.setLineWidth(width);
        contentStream.showText(text);
        contentStream.endText();
    }
}
