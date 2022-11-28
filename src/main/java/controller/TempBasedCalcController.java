package controller;

import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import model.TempBasedCalcModel;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class TempBasedCalcController implements Initializable {

    @FXML
    public ChoiceBox<String> epuletTipus;
    @FXML
    public TextField alapterulet;
    @FXML
    public Spinner<Integer> belteriHomerseklet;
    @FXML
    public ChoiceBox<String> szigeteles;
    @FXML
    public Button calcButton;
    @FXML
    public ChoiceBox<String> energiahordozo;
    @FXML
    public ChoiceBox<String> futoBerendezes;
    @FXML
    public Text osszeg;

    TempBasedCalcModel tempBasedCalcModel = new TempBasedCalcModel();

    @Override
    //setting up spinners and choiceboxes, and setting default values
    public void initialize(URL url, ResourceBundle resourceBundle) {
        epuletTipus.getItems().addAll("Családi ház", "Lakás");
        epuletTipus.setValue("Családi ház");
        szigeteles.getItems().addAll("Nincs", "Rossz", "Közepes", "Jó", "Kitűnő");
        szigeteles.setValue("Közepes");
        alapterulet.setText("100");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 30);
        belteriHomerseklet.setValueFactory(valueFactory);
        energiahordozo.getItems().addAll("Áram", "Földgáz", "Fa");
        futoBerendezes.getItems().addAll("Gázkazán", "Fakazán", "Konvektor", "Kandalló", "Hőszivattyú", "Elektromos hősugárzó");
    }

    public void calculate(ActionEvent event) throws DocumentException, FileNotFoundException {
        if (alapterulet.getText().isEmpty() || belteriHomerseklet.getValue() == null || energiahordozo.getValue() == null || futoBerendezes.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText("Hiba történt!");
            alert.setContentText("Nem adott meg minden adatot!");
            alert.showAndWait();
        } else {
            if (!isInputCorrect()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText("Hiba történt!");
                alert.setContentText("Nem megfelelő energiahordozó-fűtőberendezés párt adott meg!");
                alert.showAndWait();
            } else {
                int alapterulet = parseInt(this.alapterulet.getText());
                int belteriHomerseklet = this.belteriHomerseklet.getValue();
                String epuletTipus = this.epuletTipus.getValue();
                String szigeteles = this.szigeteles.getValue();
                String energiahordozo = this.energiahordozo.getValue();
                String futoBerendezes = this.futoBerendezes.getValue();

                if (Objects.equals(epuletTipus, "Lakás"))
                    tempBasedCalcModel.calculateLakas(epuletTipus, alapterulet, belteriHomerseklet, szigeteles, energiahordozo, futoBerendezes);
                else
                    tempBasedCalcModel.calculateHaz(epuletTipus, alapterulet, belteriHomerseklet, szigeteles, energiahordozo, futoBerendezes);
                osszeg.setText(Integer.toString(tempBasedCalcModel.getOsszErtek()) + " Ft");
            }
        }
    }

    private boolean isInputCorrect() {
        return switch (energiahordozo.getValue()) {
            case "Áram" ->
                    futoBerendezes.getValue().equals("Hőszivattyú") || futoBerendezes.getValue().equals("Elektromos hősugárzó");
            case "Földgáz" ->
                    futoBerendezes.getValue().equals("Gázkazán") || futoBerendezes.getValue().equals("Konvektor");
            case "Fa" ->
                    futoBerendezes.getValue().equals("Fakazán") || futoBerendezes.getValue().equals("Kandalló");
            default ->
                    false;
        };
    }
}
