package model;

public class TempBasedCalcModel {

    //fűtőberendezési állandók
    private final double GAZ_KAZAN = 0.96;
    private final double FA_KAZAN = 1.75;
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

    public void calculateLakas(String epuletTipus, int alapTerulet, int belteriHomerseklet, String szigeteles, String energiahordozo, String futoBerendezes) {
        System.out.println("Lakás kalkuláció");
        //System.out.println(energiaFelhasznalasLakas(belteriHomerseklet, szigeteles, alapTerulet));

        System.out.println(calculatePrice(allandoAlkalmazas(futoBerendezes, energiaFelhasznalasLakas(belteriHomerseklet, szigeteles, alapTerulet)), energiahordozo));
    }

    public void calculateHaz(String epuletTipus, int alapTerulet, int belteriHomerseklet, String szigeteles, String energiahordozo, String futoBerendezes) {
        System.out.println("Ház kalkuláció");
        //System.out.println(energiaFelhasznalasCsaladiHaz(belteriHomerseklet, szigeteles, alapTerulet));

        System.out.println(calculatePrice(allandoAlkalmazas(futoBerendezes, energiaFelhasznalasCsaladiHaz(belteriHomerseklet, szigeteles, alapTerulet)), energiahordozo));
    }

    //egyelőre double-lel tér vissza, de nyugodtan át lehet írni úgy is hogy kerekítsen egész számra és int legyen, úgyis csak úgy fogunk tudni vele számolni
    private double energiaFelhasznalasCsaladiHaz(int belteriHomerseklet, String szigeteles, int alapterulet) {
        if (belteriHomerseklet == 20) {
            return switch (szigeteles) {
                case "Kitűnő" -> default_haz_20fok_1nm_kituno_kwh * alapterulet;
                case "Jó" -> default_haz_20fok_1nm_jo_kwh * alapterulet;
                case "Közepes" -> default_haz_20fok_1nm_kozepes_kwh * alapterulet;
                case "rossz" -> default_haz_20fok_1nm_rossz_kwh * alapterulet;
                default -> default_haz_20fok_1nm_nincs_kwh * alapterulet;
            };
        }
        else if (belteriHomerseklet > 20) {
            double szazalekosKulonbseg = 1d + ((((double) belteriHomerseklet - 20d) * 6d)/100d);
            return switch (szigeteles) {
                case "Kitűnő" -> default_haz_20fok_1nm_kituno_kwh * szazalekosKulonbseg * alapterulet;
                case "Jó" -> default_haz_20fok_1nm_jo_kwh * szazalekosKulonbseg * alapterulet;
                case "Közepes" -> default_haz_20fok_1nm_kozepes_kwh * szazalekosKulonbseg * alapterulet;
                case "rossz" -> default_haz_20fok_1nm_rossz_kwh * szazalekosKulonbseg * alapterulet;
                default -> default_haz_20fok_1nm_nincs_kwh * szazalekosKulonbseg * alapterulet;
            };
        }
        else {
            double szazalekosKulonbseg = 1d - (((20d - (double) belteriHomerseklet) * 6d)/100d);
            return switch (szigeteles) {
                case "Kitűnő" -> default_haz_20fok_1nm_kituno_kwh * szazalekosKulonbseg * alapterulet;
                case "Jó" -> default_haz_20fok_1nm_jo_kwh * szazalekosKulonbseg * alapterulet;
                case "Közepes" -> default_haz_20fok_1nm_kozepes_kwh * szazalekosKulonbseg * alapterulet;
                case "rossz" -> default_haz_20fok_1nm_rossz_kwh * szazalekosKulonbseg * alapterulet;
                default -> default_haz_20fok_1nm_nincs_kwh * szazalekosKulonbseg * alapterulet;
            };
        }
    }

    private double energiaFelhasznalasLakas(int belteriHomerseklet, String szigeteles, int alapterulet) {
        if (belteriHomerseklet == 20) {
            return switch (szigeteles) {
                case "Kitűnő" -> default_lakas_20fok_1nm_kituno_kwh * alapterulet;
                case "Jó" -> default_lakas_20fok_1nm_jo_kwh * alapterulet;
                case "Közepes" -> default_lakas_20fok_1nm_kozepes_kwh * alapterulet;
                case "rossz" -> default_lakas_20fok_1nm_rossz_kwh * alapterulet;
                default -> default_lakas_20fok_1nm_nincs_kwh * alapterulet;
            };
        }
        else if (belteriHomerseklet > 20) {
            double szazalekosKulonbseg = 1d + ((((double) belteriHomerseklet - 20d) * 6d)/100d);
            return switch (szigeteles) {
                case "Kitűnő" -> default_lakas_20fok_1nm_kituno_kwh * szazalekosKulonbseg * alapterulet;
                case "Jó" -> default_lakas_20fok_1nm_jo_kwh * szazalekosKulonbseg * alapterulet;
                case "Közepes" -> default_lakas_20fok_1nm_kozepes_kwh * szazalekosKulonbseg * alapterulet;
                case "rossz" -> default_lakas_20fok_1nm_rossz_kwh * szazalekosKulonbseg * alapterulet;
                default -> default_lakas_20fok_1nm_nincs_kwh * szazalekosKulonbseg * alapterulet;
            };
        }
        else {
            double szazalekosKulonbseg = 1d - (((20d - (double) belteriHomerseklet) * 6d)/100d);
            return switch (szigeteles) {
                case "Kitűnő" -> default_lakas_20fok_1nm_kituno_kwh * szazalekosKulonbseg * alapterulet;
                case "Jó" -> default_lakas_20fok_1nm_jo_kwh * szazalekosKulonbseg * alapterulet;
                case "Közepes" -> default_lakas_20fok_1nm_kozepes_kwh * szazalekosKulonbseg * alapterulet;
                case "rossz" -> default_lakas_20fok_1nm_rossz_kwh * szazalekosKulonbseg * alapterulet;
                default -> default_lakas_20fok_1nm_nincs_kwh * szazalekosKulonbseg * alapterulet;
            };
        }
    }

    //visszater azzal hogy hány forint
    private double calculateAram(double kwh) {
        if (kwh > 2523) {
            double piaci_aras_kwh = kwh - 2523;
            return (piaci_aras_kwh * 70.1) + ((kwh-piaci_aras_kwh) * 36);
        }
        else return kwh * 36;
    }

    private double calculateGaz(double kwh) {
        if (kwh > 18177) {
            double piaci_aras_kwh = kwh - 18177;
            return (piaci_aras_kwh * 747) + ((kwh - piaci_aras_kwh) * 102);
        }
        else return kwh * 102;
    }

    private double calculateFa(double kwh) {
        //1 m3 fa kb --> 33000 Ft
        return (kwh/4.3/1000)*1.2*33000;
    }

    private int calculatePrice(double kwh, String energiahordozo) {
        if (energiahordozo.equals("Áram")) {
            return (int) calculateAram(kwh);
        }
        else if (energiahordozo.equals("Földgáz")) {
            return (int) calculateGaz(kwh);
        }
        else {
            return (int) calculateFa(kwh);
        }
    }

    //alkalmazzuk a fűtőberendezések állandóját | "Gáz kazán", "Fa kazán", "Konvektor", "Kandalló", "Hőszivattyú", "Elektromos hősugárzó"
    private double allandoAlkalmazas(String futoBerendezes, double kwh) {
        return switch (futoBerendezes) {
            case "Gáz kazán" -> kwh * GAZ_KAZAN;
            case "Fa kazán" -> kwh * FA_KAZAN;
            case "Konvektor" -> kwh * KONVEKTOR;
            case "Kandalló" -> kwh * KANDALLO;
            case "Hőszivattyú" -> kwh * HOSZIVATTYU;
            default -> kwh * ELEKTROMOS_HOSUGARZO;
        };
    }

    //#TODO! megcsinálni fára --> 4.3 kWh/kg
}