package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends OpeningSceneController implements Initializable{
    @FXML
    private TextField aram_a_tarifa;
    @FXML
    private TextField aram_b_tarifa;
    @FXML
    private TextField gaz_fogyasztas;
    @FXML
    private Spinner<Integer> gyermek_db;
    @FXML
    private ChoiceBox<String> honapok;

    private final String[] honapokContent = {"Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"};

    public void initialize(URL arg0, ResourceBundle arg1) {
        honapok.getItems().addAll(honapokContent);
        honapok.setValue(honapokContent[0]);

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0,15);
        valueFactory.setValue(0);
        gyermek_db.setValueFactory(valueFactory);
    }

    public void onExit(ActionEvent event) {Platform.exit();}

    public void calculate(ActionEvent event) {
        if (checkIfUserGaveCorrectInput()) {
            System.out.println(dataModel.getInputHaviAramA());
            System.out.println(dataModel.getInputHaviAramB());
            System.out.println(dataModel.getInputHaviGaz());
            System.out.println(dataModel.getInputGyerekSzam());
            System.out.println(dataModel.getInputHonap());
            System.out.println(dataModel.getCsokkentettarAram());
            System.out.println(dataModel.getPiaciarAram());
            System.out.println(dataModel.getCsokkentettarAram_B());
            System.out.println(dataModel.getPiaciarAram_B());
            System.out.println(dataModel.getCsokkentettarGaz());
            System.out.println(dataModel.getPiaciarGaz());
            System.out.println();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Az adatok vagy nincsenek, vagy nem jól lettek megadva!");
            alert.setTitle("Hiba");
            alert.setResizable(false);
            alert.show();
        }
    }

    private boolean checkIfUserGaveCorrectInput() {
        if (aram_a_tarifa.getText().isEmpty() || aram_b_tarifa.getText().isEmpty() || gaz_fogyasztas.getText().isEmpty()) return false;
        try {
            dataModel.setInputHaviAramA(Integer.parseInt(aram_a_tarifa.getText()));
            dataModel.setInputHaviAramB(Integer.parseInt(aram_b_tarifa.getText()));
            dataModel.setInputHaviGaz(Integer.parseInt(gaz_fogyasztas.getText()));
            dataModel.setInputGyerekSzam(gyermek_db.getValue());
            dataModel.setInputHonap(honapok.getValue());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
