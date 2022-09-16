package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.DataModel;

import java.io.IOException;


public class OpeningSceneController {

    DataModel dataModel = new DataModel();

    @FXML
    TextField csokkentettar;

    @FXML
    TextField piaciar;

    @FXML
    void onExit() {
        Platform.exit();
    }

    @FXML
    void onNext(ActionEvent event) throws IOException {
        try {
            dataModel.setCsokkentettarAram(Integer.parseInt(csokkentettar.getText()));
            dataModel.setPiaciarAram(Integer.parseInt(piaciar.getText()));

            SceneController.sceneSetter(event, SceneController.MAINSCENEPATH);
        }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Számot adj meg!");
            alert.setTitle("Hiba");
            alert.setResizable(false);
            alert.show();
        }
    }
}
