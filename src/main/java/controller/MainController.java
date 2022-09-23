package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.DataModel;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    @FXML
    private TextField aramTarifa_A;
    @FXML
    private TextField aramTarifa_B;
    @FXML
    private TextField gazFogyasztas;
    @FXML
    private Spinner<Integer> gyermekSzamValaszto;
    @FXML
    private ChoiceBox<String> honapValaszto;

    private final DataModel dataModel = new DataModel();

    private final String[] honapValasztoErtekek = {
        "Január",
        "Február",
        "Március",
        "Április",
        "Május",
        "Június",
        "Július",
        "Augusztus",
        "Szeptember",
        "Október",
        "November",
        "December"
    };

    public void initialize(URL arg0, ResourceBundle arg1) {
        honapValaszto.getItems().addAll(honapValasztoErtekek);
        honapValaszto.setValue(honapValasztoErtekek[0]);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,15);
        gyermekSzamValaszto.setValueFactory(valueFactory);
    }

    public void onExit(ActionEvent event) {
        Platform.exit();
    }

    public void calculate(ActionEvent event) {
        if (isUserInputValid()) {
            //TODO: Actual Calculation
            //TODO: Remove logging
            System.out.println(dataModel.getInputHaviAram_A());
            System.out.println(dataModel.getInputHaviAram_B());
            System.out.println(dataModel.getInputHaviGaz());

            System.out.println(dataModel.getInputGyerekSzam());
            System.out.println(dataModel.getInputHonap());

            System.out.println(dataModel.getAramPiaciAr());
            System.out.println(dataModel.getAramCsokkentettAr());

            System.out.println(dataModel.getAramPiaciAr_B());
            System.out.println(dataModel.getAramCsokkentettAr_B());

            System.out.println(dataModel.getGazPiaciAr());
            System.out.println(dataModel.getGazCsokkentettAr());
            System.out.println();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Az adatok vagy nincsenek, vagy nem jól lettek megadva!");
            alert.setTitle("Hiba");
            alert.setResizable(false);
            alert.show();
        }
    }

    private boolean isUserInputValid() {
        if (aramTarifa_A.getText().isEmpty() || aramTarifa_B.getText().isEmpty() || gazFogyasztas.getText().isEmpty()) {
            return false;
        }

        try {
            dataModel.setInputHaviAram_A(Integer.parseInt(aramTarifa_A.getText()));
            dataModel.setInputHaviAram_B(Integer.parseInt(aramTarifa_B.getText()));
            dataModel.setInputHaviGaz(Integer.parseInt(gazFogyasztas.getText()));
            dataModel.setInputGyerekSzam(gyermekSzamValaszto.getValue());
            dataModel.setInputHonap(honapValaszto.getValue());
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public void saveDataFromFirstScene(
        int aramPiaciAr,
        int aramCsokkentettAr,
        int aramPiaciAr_B,
        int aramCsokkentettAr_B,
        int gazPiaciAr,
        int gazCsokkentettAr
    ){
        dataModel.setAramPiaciAr(aramPiaciAr);
        dataModel.setAramCsokkentettAr(aramCsokkentettAr);
        dataModel.setAramPiaciAr_B(aramPiaciAr_B);
        dataModel.setAramCsokkentettAr_B(aramCsokkentettAr_B);
        dataModel.setGazPiaciAr(gazPiaciAr);
        dataModel.setGazCsokkentettAr(gazCsokkentettAr);
    }
}
