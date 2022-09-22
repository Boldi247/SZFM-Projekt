package model;

public class DataModel {

    //TODO - valós árak beírása
    private final int def_aram_p = 10;
    private final int def_aram_cs = 10;
    private final int def_gaz_p = 10;
    private final int def_gaz_cs = 10;

    private int piaciarAram;
    private int csokkentettarAram;

    private int piaciarGaz;
    private int csokkentettarGaz;

    private int inputHaviAramA;
    private int inputHaviAramB;
    private int inputHaviGaz;
    private int inputGyerekSzam;
    private String inputHonap;

    public int getDef_aram_cs() {
        return def_aram_cs;
    }
    public int getDef_aram_p() {
        return def_aram_p;
    }
    public int getDef_gaz_cs() {
        return def_gaz_cs;
    }
    public int getDef_gaz_p() {
        return def_gaz_p;
    }

    public int getCsokkentettarAram() {
        return csokkentettarAram;
    }
    public int getPiaciarAram() {
        return piaciarAram;
    }

    public int getCsokkentettarGaz() {
        return csokkentettarGaz;
    }
    public int getPiaciarGaz() {
        return piaciarGaz;
    }

    public void setCsokkentettarAram(int csokkentettarAram) {
        this.csokkentettarAram = csokkentettarAram;
    }
    public void setPiaciarAram(int piaciarAram) {
        this.piaciarAram = piaciarAram;
    }

    public void setCsokkentettarGaz(int csokkentettarGaz) {
        this.csokkentettarGaz = csokkentettarGaz;
    }
    public void setPiaciarGaz(int piaciarGaz) {
        this.piaciarGaz = piaciarGaz;
    }

    public int getInputGyerekSzam() {
        return inputGyerekSzam;
    }
    public int getInputHaviAramA() {
        return inputHaviAramA;
    }
    public int getInputHaviAramB() {
        return inputHaviAramB;
    }
    public int getInputHaviGaz() {
        return inputHaviGaz;
    }
    public String getInputHonap() {
        return inputHonap;
    }

    public void setInputGyerekSzam(int inputGyerekSzam) {
        this.inputGyerekSzam = inputGyerekSzam;
    }
    public void setInputHaviAramA(int inputHaviAramA) {
        this.inputHaviAramA = inputHaviAramA;
    }
    public void setInputHaviAramB(int inputHaviAramB) {
        this.inputHaviAramB = inputHaviAramB;
    }
    public void setInputHaviGaz(int inputHaviGaz) {
        this.inputHaviGaz = inputHaviGaz;
    }
    public void setInputHonap(String inputHonap) {
        this.inputHonap = inputHonap;
    }
}
