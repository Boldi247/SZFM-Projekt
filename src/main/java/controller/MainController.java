package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
            //TODO!!
            //Át kellene konvertáltatni a textboxokból lekért számokat integerré, és azokat a DataModel classban tárolni
            //Új DataModel példányt nem készíthetünk, mert akkor elvesznek az első Scene-en bekért adatok (piaci ár, rezsicsökkentett ár)
            //Amennyiben nem tudjuk átkonvertálni a szöveget integer-ré, akkor false-t kell adnia a függvénynek, ugyanis akkor a
            //felhasználó valószínűleg betűket is írt az input ablakba.
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
