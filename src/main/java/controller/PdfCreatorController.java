package controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class PdfCreatorController {
    private String ownerPassword = "borsodmegye12";

    public void createPdf(
            int osszErtek,
            String epuletTipus,
            int alapTerulet,
            int belteriHomerseklet,
            String szigeteles,
            String energiahordozo,
            String futoBerendezes
    ) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        SimpleDateFormat formatter = new SimpleDateFormat(" yyyyMMdd-HHmmss");
        Date date = new Date();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(String.format(LoginController.getNameOfUser() + "-haz-szamitas-%s.pdf", formatter.format(date))));

        pdfWriter.setEncryption(LoginController.getPasswordOfUser().getBytes(StandardCharsets.UTF_8), ownerPassword.getBytes(StandardCharsets.UTF_8), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256);

        document.open();

        PdfPTable table = new PdfPTable(2);
        addTableHeader(table);

        table.addCell("Összérték");
        table.addCell(osszErtek + " Ft");
        table.addCell("Épülettípus");
        table.addCell(String.valueOf(epuletTipus));
        table.addCell("Alapterület");
        table.addCell(alapTerulet + " m2");
        table.addCell("Beltéri hőmérséklet");
        table.addCell(belteriHomerseklet + "°C");
        table.addCell("Szigetelés");
        table.addCell(String.valueOf(szigeteles));
        table.addCell("Energiahordozó");
        table.addCell(String.valueOf(energiahordozo));
        table.addCell("Fütöberendezés");
        table.addCell(String.valueOf(futoBerendezes));
        document.add(table);
        document.close();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Bejegyzés", "Érték")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    public void createAramGazPdf(
            String villanyErtek,
            String gazErtek
    ) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date();
        PdfWriter.getInstance(document, new FileOutputStream(String.format(LoginController.getNameOfUser() + "-illany-gaz-szamitas-%s.pdf", formatter.format(date))));

        document.open();

        PdfPTable table = new PdfPTable(2);
        addTableHeader(table);

        table.addCell("Összes villanydíj");
        table.addCell(villanyErtek + " Ft");
        table.addCell("Összes gázdíj");
        table.addCell(gazErtek + " Ft");

        document.add(table);
        document.close();
    }
}
