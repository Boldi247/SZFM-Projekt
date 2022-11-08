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
        System.out.println(energiaFelhasznalasLakas(belteriHomerseklet, szigeteles, alapTerulet));
    }

    public void calculateHaz(String epuletTipus, int alapTerulet, int belteriHomerseklet, String szigeteles, String energiahordozo, String futoBerendezes) {
        System.out.println("Ház kalkuláció");
        System.out.println(energiaFelhasznalasCsaladiHaz(belteriHomerseklet, szigeteles, alapTerulet));
    }

    //egyelőre double-lel tér vissza, de nyugodtan át lehet írni úgy is hogy kerekítsen egész számra és int legyen, úgyis csak úgy fogunk tudni vele számolni
    private double energiaFelhasznalasCsaladiHaz(int belteriHomerseklet, String szigeteles, int alapterulet) {
        if (belteriHomerseklet == 20) {
            if (szigeteles.equals("Kitűnő")) return default_haz_20fok_1nm_kituno_kwh * alapterulet;
            else if (szigeteles.equals("Jó")) return default_haz_20fok_1nm_jo_kwh * alapterulet;
            else if (szigeteles.equals("Közepes")) return default_haz_20fok_1nm_kozepes_kwh * alapterulet;
            else if (szigeteles.equals("rossz")) return default_haz_20fok_1nm_rossz_kwh * alapterulet;
            else return default_haz_20fok_1nm_nincs_kwh * alapterulet;
        }
        else if (belteriHomerseklet > 20) {
            double szazalekosKulonbseg = 1d + ((((double) belteriHomerseklet - 20d) * 6d)/100d);
            if (szigeteles.equals("Kitűnő")) return default_haz_20fok_1nm_kituno_kwh * szazalekosKulonbseg * alapterulet;
            else if (szigeteles.equals("Jó")) return default_haz_20fok_1nm_jo_kwh * szazalekosKulonbseg * alapterulet;
            else if (szigeteles.equals("Közepes")) return default_haz_20fok_1nm_kozepes_kwh * szazalekosKulonbseg * alapterulet;
            else if (szigeteles.equals("rossz")) return default_haz_20fok_1nm_rossz_kwh * szazalekosKulonbseg * alapterulet;
            else return default_haz_20fok_1nm_nincs_kwh * szazalekosKulonbseg * alapterulet;
        }
        else {
            double szazalekosKulonbseg = 1d - (((20d - (double) belteriHomerseklet) * 6d)/100d);
            if (szigeteles.equals("Kitűnő")) return default_haz_20fok_1nm_kituno_kwh * szazalekosKulonbseg * alapterulet;
            else if (szigeteles.equals("Jó")) return default_haz_20fok_1nm_jo_kwh * szazalekosKulonbseg * alapterulet;
            else if (szigeteles.equals("Közepes")) return default_haz_20fok_1nm_kozepes_kwh * szazalekosKulonbseg * alapterulet;
            else if (szigeteles.equals("rossz")) return default_haz_20fok_1nm_rossz_kwh * szazalekosKulonbseg * alapterulet;
            else return default_haz_20fok_1nm_nincs_kwh * szazalekosKulonbseg * alapterulet;
        }
    }

    private double energiaFelhasznalasLakas(int belteriHomerseklet, String szigeteles, int alapterulet) {
        if (belteriHomerseklet == 20) {
            if (szigeteles.equals("Kitűnő")) return default_lakas_20fok_1nm_kituno_kwh;
            else if (szigeteles.equals("Jó")) return default_lakas_20fok_1nm_jo_kwh;
            else if (szigeteles.equals("Közepes")) return default_lakas_20fok_1nm_kozepes_kwh;
            else if (szigeteles.equals("rossz")) return default_lakas_20fok_1nm_rossz_kwh;
            else return default_lakas_20fok_1nm_nincs_kwh;
        }
        else if (belteriHomerseklet > 20) {
            double szazalekosKulonbseg = 1d + ((((double) belteriHomerseklet - 20d) * 6d)/100d);
            if (szigeteles.equals("Kitűnő")) return default_lakas_20fok_1nm_kituno_kwh * szazalekosKulonbseg;
            else if (szigeteles.equals("Jó")) return default_lakas_20fok_1nm_jo_kwh * szazalekosKulonbseg;
            else if (szigeteles.equals("Közepes")) return default_lakas_20fok_1nm_kozepes_kwh * szazalekosKulonbseg;
            else if (szigeteles.equals("rossz")) return default_lakas_20fok_1nm_rossz_kwh * szazalekosKulonbseg;
            else return default_lakas_20fok_1nm_nincs_kwh * szazalekosKulonbseg;
        }
        else {
            double szazalekosKulonbseg = 1d - (((20d - (double) belteriHomerseklet) * 6d)/100d);
            if (szigeteles.equals("Kitűnő")) return default_lakas_20fok_1nm_kituno_kwh * szazalekosKulonbseg;
            else if (szigeteles.equals("Jó")) return default_lakas_20fok_1nm_jo_kwh * szazalekosKulonbseg;
            else if (szigeteles.equals("Közepes")) return default_lakas_20fok_1nm_kozepes_kwh * szazalekosKulonbseg;
            else if (szigeteles.equals("rossz")) return default_lakas_20fok_1nm_rossz_kwh * szazalekosKulonbseg;
            else return default_lakas_20fok_1nm_nincs_kwh * szazalekosKulonbseg;
        }
    }
}