import org.apache.commons.text.WordUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.util.Calendar;

public class PdfBoxExample {

    public static void writeLineOfText(PDPageContentStream contentStream, int tx, int ty, PDFont font, String text) {
        if (font == null) font = PDType1Font.HELVETICA;

        try {
            contentStream.beginText();
            contentStream.setFont(font, 10);
            contentStream.newLineAtOffset(tx, ty); // 595 x 842
            contentStream.showText(text);
            contentStream.endText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeTextColumn(PDPageContentStream contentStream, int tx, int ty, PDFont font, String text, int wrapLength) {
        String lines[] = WordUtils.wrap(text, wrapLength).split("\\r?\\n");

        int posY = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            System.out.println(line);
            posY = ty - i * 15;
            writeLineOfText(contentStream, tx, posY, font, line);
        }
    }

    public static void main(String[] args) throws Exception {
        //Tworzenie nazwy pliku i jego ścieżka // Creating a file name and its path
        Calendar c = Calendar.getInstance();
        String fileName = "files/file_" + c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.MINUTE) + ".pdf";

        //Dodanie dokumentu oraz strony // Adding a document and a page
        PDDocument document = new PDDocument();
        PDPage page1 = new PDPage();
        document.addPage(page1);

        PDPageContentStream contentStream1 = new PDPageContentStream(document, page1);

        writeLineOfText(contentStream1, 40, 100, null, "Hello World!");

        writeTextColumn(contentStream1, 40, 700, null, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                50);

        writeTextColumn(contentStream1, 280, 700, null, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                50);

        contentStream1.close();

        // nowa strona // new page
        PDPage page2 = new PDPage();
        PDPageContentStream contentStream2 = new PDPageContentStream(document, page2);
        document.addPage(page2);

        writeLineOfText(contentStream2, 40, 700, PDType1Font.COURIER_BOLD, "Hello World!");

        contentStream2.close();

        document.save(fileName);
        document.close();
    }
}
