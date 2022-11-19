package exceptions.model;

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

    private int aramAtlag_kWh = 210;
    private int gazAtlag_m3 = 144;
    private int gazAtlag_m3_nagycsalados = 194;
    private int gazAtlagPluszGyerekenkent = 25;


    public int calculateAram() {
        int aTarifaFizetendo = calculateAramATarifa();
        int bTarifaFizetendo = calculateAramBTarifa();

        return aTarifaFizetendo + bTarifaFizetendo;
    }
    
    public int calculateAramATarifa() {
        if (inputHaviAram_A > aramAtlag_kWh) {
            int res = aramAtlag_kWh * aramCsokkentettAr;
            res = res + (inputHaviAram_A-aramAtlag_kWh)*aramPiaciAr;
            return res;
        }
        else {
            return inputHaviAram_A*aramCsokkentettAr;
        }
    }
    
    public int calculateAramBTarifa() {
        if (inputHaviAram_B > aramAtlag_kWh) {
            int res = aramAtlag_kWh * aramCsokkentettAr_B;
            res = res + (inputHaviAram_B-aramAtlag_kWh)*aramPiaciAr_B;
            return res;
        }
        else {
            return inputHaviAram_B*aramCsokkentettAr_B;
        }
    }

    public int calculateGaz() {
        if (inputGyerekSzam < 3) {
            if (inputHaviGaz < gazAtlag_m3) {
                return inputHaviGaz * gazCsokkentettAr;
            } else {
                int diff = inputHaviGaz - gazAtlag_m3;

                return (gazAtlag_m3 * gazCsokkentettAr) + (diff * gazPiaciAr);
            }
        }

        if (inputGyerekSzam == 3) {
            if (inputHaviGaz < gazAtlag_m3_nagycsalados) {
                return inputHaviGaz * gazCsokkentettAr;
            } else {
                int diff = inputHaviGaz - gazAtlag_m3_nagycsalados;

                return (gazAtlag_m3_nagycsalados * gazCsokkentettAr) + (diff * gazPiaciAr);
            }
        }

        if (inputHaviGaz < gazAtlag_m3_nagycsalados + (inputGyerekSzam - 3) * 25) {
            return inputHaviGaz * gazCsokkentettAr;
        } else {
            int diff = inputHaviGaz - (gazAtlag_m3_nagycsalados  + (inputGyerekSzam - 3) * 25);

            return ((gazAtlag_m3_nagycsalados + (inputGyerekSzam - 3) * 25) * gazCsokkentettAr) + (diff * gazPiaciAr);
        }
    }
}
