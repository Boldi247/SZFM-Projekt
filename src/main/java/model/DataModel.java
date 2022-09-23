package model;

public class DataModel {

    //TODO - valós árak beírása
    public static final int def_aram_p = 70;
    public static final int def_aram_cs = 37;
    public static final int def_gaz_p = 747;
    public static final int def_gaz_cs = 102;
    public static final int def_aram_b_p = 63;
    public static final int def_aram_b_cs = 23;

    private int piaciarAram;
    private int csokkentettarAram;
    private int piaciarAram_B;
    private int csokkentettarAram_B;

    private int piaciarGaz;
    private int csokkentettarGaz;

    private int inputHaviAramA;
    private int inputHaviAramB;
    private int inputHaviGaz;
    private int inputGyerekSzam;
    private String inputHonap;

    public int getCsokkentettarAram() {
        return csokkentettarAram;
    }
    public int getPiaciarAram() {
        return piaciarAram;
    }

    public int getCsokkentettarAram_B() {
        return csokkentettarAram_B;
    }
    public int getPiaciarAram_B() {
        return piaciarAram_B;
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

    public void setCsokkentettarAram_B(int csokkentettarAram_B) {
        this.csokkentettarAram_B = csokkentettarAram_B;
    }
    public void setPiaciarAram_B(int piaciarAram_B) {
        this.piaciarAram_B = piaciarAram_B;
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
