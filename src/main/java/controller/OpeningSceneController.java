package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.DataModel;

import java.io.IOException;


public class OpeningSceneController {

    @FXML
    public TextField piaciar_aram_b;
    private int piaciar_aram_b_value;

    @FXML
    public TextField csokkentettar_aram_b;
    private int csokkentettar_aram_b_value;

    @FXML
    public TextField csokkentettar_aram;
    private int csokkentettar_aram_value;

    @FXML
    public TextField piaciar_aram;
    private int piaciar_aram_value;

    @FXML
    public TextField csokkentettar_gaz;
    private int csokkentettar_gaz_value;

    @FXML
    public TextField piaciar_gaz;
    private int piaciar_gaz_value;

    private SceneController sceneController = new SceneController();

    @FXML
    void onExit() {
        Platform.exit();
    }

    @FXML
    public void onNext(ActionEvent event) throws IOException {
        sceneController.sceneSetter(event, sceneController.getMAINSCENEPATH());
        MainController mainController = sceneController.getFxmlLoader().getController();


        try {
            if (csokkentettar_aram.getText().isEmpty() || piaciar_aram.getText().isEmpty() || csokkentettar_gaz.getText().isEmpty() || piaciar_gaz.getText().isEmpty() || piaciar_aram_b.getText().isEmpty() || csokkentettar_aram_b.getText().isEmpty()) {

                mainController.saveDataFromFirstScene(DataModel.def_aram_p, DataModel.def_aram_cs, DataModel.def_aram_b_p, DataModel.def_aram_b_cs, DataModel.def_gaz_p, DataModel.def_gaz_cs);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Egy vagy több mezőt üresen hagyott: A program alapértelmezett értékekkel fog számolni!");
                alert.setTitle("Alapértelmezett értékek");
                alert.setResizable(false);
                alert.show();
            }
            else {
                setValues();
                mainController.saveDataFromFirstScene(piaciar_aram_value, csokkentettar_aram_value, piaciar_aram_b_value, csokkentettar_aram_b_value, piaciar_gaz_value, csokkentettar_gaz_value);
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Számot adj meg!");
            alert.setTitle("Hiba");
            alert.setResizable(false);
            alert.show();
        }
    }

    private void setValues() {
        piaciar_aram_b_value = Integer.parseInt(piaciar_aram_b.getText());
        csokkentettar_aram_b_value = Integer.parseInt(csokkentettar_aram_b.getText());
        csokkentettar_aram_value = Integer.parseInt(csokkentettar_aram.getText());
        piaciar_aram_value = Integer.parseInt(piaciar_aram.getText());
        csokkentettar_gaz_value = Integer.parseInt(csokkentettar_gaz.getText());
        piaciar_gaz_value = Integer.parseInt(piaciar_gaz.getText());
    }
}
