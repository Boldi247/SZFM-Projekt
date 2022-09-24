package model;

import lombok.Data;

@Data
public class DataModel {
    //TODO - valós árak beírása
    public static final int DEFAULT_ARAM_PIACI_AR = 70;
    public static final int DEFAULT_ARAM_CSOKKENTETT_AR = 37;
    public static final int DEFAULT_ARAM_PIACI_AR_B = 63;
    public static final int DEFAULT_ARAM_CSOKKENTETT_AR_B = 23;
    public static final int DEFAULT_GAZ_PIACI_AR = 747;
    public static final int DEFAULT_GAZ_CSOKKENTETT_AR = 102;

    private int aramPiaciAr;
    private int aramCsokkentettAr;
    private int aramPiaciAr_B;
    private int aramCsokkentettAr_B;

    private int gazPiaciAr;
    private int gazCsokkentettAr;

    private int inputHaviAram_A;
    private int inputHaviAram_B;
    private int inputHaviGaz;
    private int inputGyerekSzam;
    private String inputHonap;
}
