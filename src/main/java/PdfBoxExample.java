import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.util.Calendar;

public class PdfBoxExample {
    public static void main(String[] args) throws Exception {
        //Tworzenie nazwy pliku i jego ścieżka // Creating a file name and its path
        Calendar c = Calendar.getInstance();
        String fileName = "files/file_" + c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.MINUTE) + ".pdf";

        //Dodanie dokumentu oraz strony // Adding a document and a page
        PDDocument document = new PDDocument();
        PDPage page1 = new PDPage();
        document.addPage(page1);

        document.save(fileName);
        document.close();
    }
}
