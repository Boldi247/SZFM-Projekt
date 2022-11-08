package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import model.TempBasedCalcModel;

import java.net.URL;
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
    public TextField futottOrak;
    @FXML
    public Button calcButton;
    @FXML
    public ChoiceBox<String> energiahordozo;
    @FXML
    public ChoiceBox<String> futoBerendezes;

    TempBasedCalcModel tempBasedCalcModel = new TempBasedCalcModel();

    @Override
    //setting up spinners and choiceboxes, and setting default values
    public void initialize(URL url, ResourceBundle resourceBundle) {
        epuletTipus.getItems().addAll("Családi ház", "Lakás");
        epuletTipus.setValue("Családi ház");
        szigeteles.getItems().addAll("Nincs", "Rossz", "Közepes", "Jó", "Kitűnő");
        szigeteles.setValue("Közepes");
        alapterulet.setText("100");
        futottOrak.setText("3550");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 30);
        belteriHomerseklet.setValueFactory(valueFactory);
        energiahordozo.getItems().addAll("Áram", "Földgáz", "Fa");
        futoBerendezes.getItems().addAll("Gáz kazán", "Fa kazán", "Konvektor", "Kandalló", "Hőszivattyú", "Elektromos hősugárzó");
    }

    public void calculate(ActionEvent event) {
        if (alapterulet.getText().isEmpty() || futottOrak.getText().isEmpty() || belteriHomerseklet.getValue() == null || energiahordozo.getValue() == null || futoBerendezes.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText("Hiba történt!");
            alert.setContentText("Nem adott meg minden adatot!");
            alert.showAndWait();
        }

        else {
            if (!checkIfInputIsCorrect()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText("Hiba történt!");
                alert.setContentText("Nem megfelelő energiahordozó-fűtőberendezés párt adott meg!");
                alert.showAndWait();
            }
            else {
                int alapterulet = parseInt(this.alapterulet.getText());
                int futottOrak = parseInt(this.futottOrak.getText());
                int belteriHomerseklet = this.belteriHomerseklet.getValue();
                String epuletTipus = this.epuletTipus.getValue();
                String szigeteles = this.szigeteles.getValue();
                String energiahordozo = this.energiahordozo.getValue();
                String futoBerendezes = this.futoBerendezes.getValue();

                if (epuletTipus == "Lakás")
                    tempBasedCalcModel.calculateLakas(epuletTipus, alapterulet, belteriHomerseklet, szigeteles, futottOrak, energiahordozo, futoBerendezes);
                else
                    tempBasedCalcModel.calculateHaz(epuletTipus, alapterulet, belteriHomerseklet, szigeteles, futottOrak, energiahordozo, futoBerendezes);
            }
        }
    }

    private boolean checkIfInputIsCorrect()
    {
        if (energiahordozo.getValue().equals("Áram"))
        {
            if (futoBerendezes.getValue().equals("Hőszivattyú") || futoBerendezes.getValue().equals("Elektromos hősugárzó")) return true;
            else return false;
        }
        else if (energiahordozo.getValue().equals("Földgáz"))
        {
            if (futoBerendezes.getValue().equals("Gáz kazán") || futoBerendezes.getValue().equals("Konvektor")) return true;
            else return false;
        }
        else if (energiahordozo.getValue().equals("Fa"))
        {
            if (futoBerendezes.getValue().equals("Fa kazán") || futoBerendezes.getValue().equals("Kandalló")) return true;
            else return false;
        }
        else return false;
    }
}
