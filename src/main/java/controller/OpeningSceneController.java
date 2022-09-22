package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.DataModel;

import java.io.IOException;


public class OpeningSceneController {

    public DataModel dataModel = new DataModel();

    @FXML
    public TextField piaciar_aram_b;

    @FXML
    public TextField csokkentettar_aram_b;

    @FXML
    private TextField csokkentettar_aram;

    @FXML
    private TextField piaciar_aram;

    @FXML
    private TextField csokkentettar_gaz;

    @FXML
    private TextField piaciar_gaz;

    @FXML
    void onExit() {
        Platform.exit();
    }

    @FXML
    public void onNext(ActionEvent event) throws IOException {

        try {
            if (csokkentettar_aram.getText().isEmpty() || piaciar_aram.getText().isEmpty() || csokkentettar_gaz.getText().isEmpty() || piaciar_gaz.getText().isEmpty()) {
                saveInput(dataModel.getDef_aram_p(), dataModel.getDef_aram_cs(), dataModel.getDef_gaz_p(), dataModel.getDef_gaz_cs());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Egy vagy több mezőt üresen hagyott: A program alapértelmezett értékekkel fog számolni!");
                alert.setTitle("Alapértelmezett értékek");
                alert.setResizable(false);
                alert.show();
            }
            else {
                saveInput();
            }
            SceneController.sceneSetter(event, SceneController.MAINSCENEPATH);
        }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Számot adj meg!");
            alert.setTitle("Hiba");
            alert.setResizable(false);
            alert.show();
        }
    }

    private void saveInput() {
        dataModel.setCsokkentettarAram(Integer.parseInt(csokkentettar_aram.getText()));
        dataModel.setPiaciarAram(Integer.parseInt(piaciar_aram.getText()));
        dataModel.setCsokkentettarGaz(Integer.parseInt(csokkentettar_gaz.getText()));
        dataModel.setPiaciarGaz(Integer.parseInt(piaciar_gaz.getText()));
    }

    private void saveInput(int def_aram_p, int def_aram_cs, int def_gaz_p, int def_gaz_cs) {
        dataModel.setCsokkentettarAram(def_aram_cs);
        dataModel.setPiaciarAram(def_aram_p);
        dataModel.setCsokkentettarGaz(def_gaz_cs);
        dataModel.setPiaciarGaz(def_gaz_p);
    }
}
