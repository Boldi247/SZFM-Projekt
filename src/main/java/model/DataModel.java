package model;

public class DataModel {
    private int piaciarAram;
    private int csokkentettarAram;

    private int piaciarGaz;
    private int csokkentettarGaz;

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
}
