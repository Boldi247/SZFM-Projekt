package exceptions.model;

import com.itextpdf.text.DocumentException;
import controller.PdfCreatorController;

import java.io.FileNotFoundException;

public class TempBasedCalcModel {

    //fűtőberendezési állandók
    private final double GAZKAZAN = 0.96;
    private final double FAKAZAN = 1.75;
    private final double KONVEKTOR = 1.16;
    private final double KANDALLO = 1.25;
    private final double HOSZIVATTYU = 0.23;
    private final double ELEKTROMOS_HOSUGARZO = 1;

    //1 nm 20 fokra való felfűtéséhez szükséges energia hőszigetelés minőségének függvényében
    private final int default_haz_20fok_1nm_kituno_kwh = 50;
    private final int default_haz_20fok_1nm_jo_kwh = 67;
    private final int default_haz_20fok_1nm_kozepes_kwh = 85;
    private final int default_haz_20fok_1nm_rossz_kwh = 104;
    private final int default_haz_20fok_1nm_nincs_kwh = 133;

    private final int default_lakas_20fok_1nm_kituno_kwh = 37;
    private final int default_lakas_20fok_1nm_jo_kwh = 50;
    private final int default_lakas_20fok_1nm_kozepes_kwh = 64;
    private final int default_lakas_20fok_1nm_rossz_kwh = 78;
    private final int default_lakas_20fok_1nm_nincs_kwh = 100;

    private int osszErtek;

    PdfCreatorController pdfCreatorController = new PdfCreatorController();

    public void calculateLakas(
            String epuletTipus,
            int alapTerulet,
            int belteriHomerseklet,
            String szigeteles,
            String energiahordozo,
            String futoBerendezes
    ) throws DocumentException, FileNotFoundException {
        osszErtek = calculatePrice(allandoAlkalmazas(futoBerendezes, energiaFelhasznalas(belteriHomerseklet, szigeteles, alapTerulet)), energiahordozo);

        System.out.println("Lakás kalkuláció");
        System.out.println(osszErtek);
        //System.out.println(energiaFelhasznalas(belteriHomerseklet, szigeteles, alapTerulet));

        pdfCreatorController.createPdf(osszErtek, epuletTipus, alapTerulet, belteriHomerseklet, szigeteles, energiahordozo, futoBerendezes);
    }

    public void calculateHaz(
            String epuletTipus,
            int alapTerulet,
            int belteriHomerseklet,
            String szigeteles,
            String energiahordozo,
            String futoBerendezes
    ) throws FileNotFoundException, DocumentException {
        osszErtek = calculatePrice(allandoAlkalmazas(futoBerendezes, energiaFelhasznalas(belteriHomerseklet, szigeteles, alapTerulet)), energiahordozo);

        System.out.println("Ház kalkuláció");
        System.out.println(osszErtek);
        //System.out.println(energiaFelhasznalas(belteriHomerseklet, szigeteles, alapTerulet));

        pdfCreatorController.createPdf(osszErtek, epuletTipus, alapTerulet, belteriHomerseklet, szigeteles, energiahordozo, futoBerendezes);
    }

    //egyelőre double-lel tér vissza, de nyugodtan át lehet írni úgy is hogy kerekítsen egész számra és int legyen, úgyis csak úgy fogunk tudni vele számolni
    private double energiaFelhasznalas(int belteriHomerseklet, String szigeteles, int alapterulet) {
        if (belteriHomerseklet == 20) {
            return switch (szigeteles) {
                case "Kitűnő" -> default_haz_20fok_1nm_kituno_kwh * alapterulet;
                case "Jó" -> default_haz_20fok_1nm_jo_kwh * alapterulet;
                case "Közepes" -> default_haz_20fok_1nm_kozepes_kwh * alapterulet;
                case "rossz" -> default_haz_20fok_1nm_rossz_kwh * alapterulet;
                default -> default_haz_20fok_1nm_nincs_kwh * alapterulet;
            };
        }

        double szazalekosKulonbseg = belteriHomerseklet > 20
                ? 1d + ((((double) belteriHomerseklet - 20d) * 6d) / 100d)
                : 1d - (((20d - (double) belteriHomerseklet) * 6d) / 100d);

        return switch (szigeteles) {
            case "Kitűnő" -> default_haz_20fok_1nm_kituno_kwh * szazalekosKulonbseg * alapterulet;
            case "Jó" -> default_haz_20fok_1nm_jo_kwh * szazalekosKulonbseg * alapterulet;
            case "Közepes" -> default_haz_20fok_1nm_kozepes_kwh * szazalekosKulonbseg * alapterulet;
            case "rossz" -> default_haz_20fok_1nm_rossz_kwh * szazalekosKulonbseg * alapterulet;
            default -> default_haz_20fok_1nm_nincs_kwh * szazalekosKulonbseg * alapterulet;
        };
    }

    //visszater azzal hogy hány forint
    private double calculateAram(double kwh) {
        if (kwh > 2523) {
            double piaci_aras_kwh = kwh - 2523;
            return (piaci_aras_kwh * 70.1) + ((kwh - piaci_aras_kwh) * 36);
        } else return kwh * 36;
    }

    private double calculateGaz(double kwh) {
        if (kwh > 18177) {
            double piaci_aras_kwh = kwh - 18177;
            return (piaci_aras_kwh * 747) + ((kwh - piaci_aras_kwh) * 102);
        } else return kwh * 102;
    }

    private double calculateFa(double kwh) {
        //1 m3 fa kb --> 33000 Ft
        return (kwh / 4.3 / 1000) * 1.2 * 33000;
    }

    private int calculatePrice(double kwh, String energiahordozo) {
        return switch (energiahordozo) {
            case "Áram" -> (int) calculateAram(kwh);
            case "Földgáz" -> (int) calculateGaz(kwh);
            default -> (int) calculateFa(kwh);
        };
    }

    //alkalmazzuk a fűtőberendezések állandóját | "Gázkazán", "Fakazán", "Konvektor", "Kandalló", "Hőszivattyú", "Elektromos hősugárzó"
    private double allandoAlkalmazas(String futoBerendezes, double kwh) {
        return switch (futoBerendezes) {
            case "Gázkazán" -> kwh * GAZKAZAN;
            case "Fakazán" -> kwh * FAKAZAN;
            case "Konvektor" -> kwh * KONVEKTOR;
            case "Kandalló" -> kwh * KANDALLO;
            case "Hőszivattyú" -> kwh * HOSZIVATTYU;
            default -> kwh * ELEKTROMOS_HOSUGARZO;
        };
    }

    //#TODO! megcsinálni fára --> 4.3 kWh/kg
}