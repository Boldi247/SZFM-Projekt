package model;

public class TempBasedCalcModel {

    private final double GAZ_KAZAN = 0.96;
    private final double FA_KAZAN = 1.75;
    private final double KONVEKTOR = 1.16;
    private final double KANDALLO = 1.25;
    private final double HOSZIVATTYU = 0.23;
    private final double ELEKTROMOS_HOSUGARZO = 1;

    public void calculateLakas(String epuletTipus, int alapTerulet, int belteriHomerseklet, String szigeteles, int futottOrak, String energiahordozo, String futoBerendezes) {
        System.out.println("Lakás kalkuláció");
    }

    public void calculateHaz(String epuletTipus, int alapTerulet, int belteriHomerseklet, String szigeteles, int futottOrak, String energiahordozo, String futoBerendezes) {
        System.out.println("Ház kalkuláció");
    }
}
