package model;

import javafx.fxml.FXML;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class DataModelTest {

    DataModel model;

    @BeforeEach
    void setUp() {
        model = new DataModel();
    }

    @AfterEach
    void tearDown() {
        model = null;
        assertNull(model);
    }

    @Test
    //Below Average
    void electricity_B_TestWithDefaultValuesAnd_kWh_inputBelowAverage() {
        model.setInputHaviAram_B(200);

        model.setAramCsokkentettAr_B(DataModel.DEFAULT_ARAM_CSOKKENTETT_AR_B);
        model.setAramPiaciAr_B(DataModel.DEFAULT_ARAM_PIACI_AR_B);

        assertEquals(model.calculateAramBTarifa(), 4600);
    }

    @Test
    //Is average
    void electricity_B_TestWithDefaultValuesAnd_kWh_inputIsAverage() {
        model.setInputHaviAram_B(210);

        model.setAramCsokkentettAr_B(DataModel.DEFAULT_ARAM_CSOKKENTETT_AR_B);
        model.setAramPiaciAr_B(DataModel.DEFAULT_ARAM_PIACI_AR_B);

        assertEquals(model.calculateAramBTarifa(), 4830);
    }

    @Test
    //Above average
    void electricity_B_TestWithDefaultValuesAnd_kWh_inputAboveAverage() {
        model.setInputHaviAram_B(400);

        model.setAramCsokkentettAr_B(DataModel.DEFAULT_ARAM_CSOKKENTETT_AR_B);
        model.setAramPiaciAr_B(DataModel.DEFAULT_ARAM_PIACI_AR_B);

        assertEquals(model.calculateAramBTarifa(), 16800);
    }

    //Below average
    @Test
    void electricityDefaultLowPriceAAndDefaultHighPriceAAndDefaultAverageAAndInputAIsTwoHundredShouldEqualSevenThousandFourHundred() {
        model.setAramCsokkentettAr(DataModel.DEFAULT_ARAM_CSOKKENTETT_AR);
        model.setAramPiaciAr(DataModel.DEFAULT_ARAM_PIACI_AR);
        model.setInputHaviAram_A(200);

        assertEquals(model.calculateAramATarifa(), 7400);
    }

    //Is average
    @Test
    void electricityDefaultLowPriceAAndDefaultHighPriceAAndDefaultAverageAAndInputAIsAverageShouldEqualSevenThousandSevenHundredSeventy() {
        model.setAramCsokkentettAr(DataModel.DEFAULT_ARAM_CSOKKENTETT_AR);
        model.setAramPiaciAr(DataModel.DEFAULT_ARAM_PIACI_AR);
        model.setInputHaviAram_A(210);

        assertEquals(model.calculateAramATarifa(), 7770);
    }

    // Above average
    @Test
    void electricityDefaultLowPriceAAndDefaultHighPriceAAndDefaultAverageAAndInputAIsTwoHundredShouldEqualElevenThousandTwoHundredSeventy() {
        model.setAramCsokkentettAr(DataModel.DEFAULT_ARAM_CSOKKENTETT_AR);
        model.setAramPiaciAr(DataModel.DEFAULT_ARAM_PIACI_AR);
        model.setInputHaviAram_A(260);

        assertEquals(model.calculateAramATarifa(), 11270);
    }

    // Below average and small family
    @Test
    void gasDefaultLowPriceAndDefaultHighPriceAndDefaultAverageAndInputIsNinetyAndIsSmallFamilyShouldEqualNineThousandOneHundredEighty() {
        model.setGazCsokkentettAr(DataModel.DEFAULT_GAZ_CSOKKENTETT_AR);
        model.setGazPiaciAr(DataModel.DEFAULT_GAZ_PIACI_AR);
        model.setInputHaviGaz(90);
        model.setInputGyerekSzam(1);

        assertEquals(model.calculateGaz(), 9180);
    }

    // Above average and small family
    @Test
    void gasDefaultLowPriceAndDefaultHighPriceAndDefaultAverageAndInputIsAverageAndIsSmallFamilyShouldEqualSixteenThousandThreeHundredTwenty() {
        model.setGazCsokkentettAr(DataModel.DEFAULT_GAZ_CSOKKENTETT_AR);
        model.setGazPiaciAr(DataModel.DEFAULT_GAZ_PIACI_AR);
        model.setInputHaviGaz(160);
        model.setInputGyerekSzam(2);

        assertEquals(model.calculateGaz(), 26640);
    }

    // Below average and three children
    @Test
    void gasDefaultLowPriceAndDefaultHighPriceAndDefaultAverageAndInputIsOneHundredSixtyAndHasThreeChildrenShouldEqualSixteenThousandThreeHundredTwenty() {
        model.setGazCsokkentettAr(DataModel.DEFAULT_GAZ_CSOKKENTETT_AR);
        model.setGazPiaciAr(DataModel.DEFAULT_GAZ_PIACI_AR);
        model.setInputHaviGaz(160);
        model.setInputGyerekSzam(3);

        assertEquals(model.calculateGaz(), 16320);
    }

    // Above average and three children
    @Test
    void gasDefaultLowPriceAndDefaultHighPriceAndDefaultAverageAndInputIsTwoHundredFortyAndHasThreeChildrenShouldEqualFiftyFourThousandOneHundredFifty() {
        model.setGazCsokkentettAr(DataModel.DEFAULT_GAZ_CSOKKENTETT_AR);
        model.setGazPiaciAr(DataModel.DEFAULT_GAZ_PIACI_AR);
        model.setInputHaviGaz(240);
        model.setInputGyerekSzam(3);

        assertEquals(model.calculateGaz(), 54150);
    }

    // Below average and big family
    @Test
    void gasDefaultLowPriceAndDefaultHighPriceAndDefaultAverageAndInputIsOneHundredAndIsBigFamilyShouldEqualTenThousandTwoHundred() {
        model.setGazCsokkentettAr(DataModel.DEFAULT_GAZ_CSOKKENTETT_AR);
        model.setGazPiaciAr(DataModel.DEFAULT_GAZ_PIACI_AR);
        model.setInputHaviGaz(100);
        model.setInputGyerekSzam(5);

        assertEquals(model.calculateGaz(), 10200);
    }

    // Above average and big family
    @Test
    void gasDefaultLowPriceAndDefaultHighPriceAndDefaultAverageAndInputIsThreeHundredAndIsBigFamilyShouldEqualSixtyThousandSevenHundredTwenty() {
        model.setGazCsokkentettAr(DataModel.DEFAULT_GAZ_CSOKKENTETT_AR);
        model.setGazPiaciAr(DataModel.DEFAULT_GAZ_PIACI_AR);
        model.setInputHaviGaz(300);
        model.setInputGyerekSzam(5);

        assertEquals(model.calculateGaz(), 66720);
    }
}