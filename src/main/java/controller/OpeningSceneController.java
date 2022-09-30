package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.DataModel;
import service.AuthenticationService;

import java.io.IOException;

public class OpeningSceneController {
    @FXML
    private TextField aramPiaciAr_B;
    private int aramPiaciAr_B_Ertek;

    @FXML
    private TextField aramCsokkentettAr_B;
    private int aramCsokkentettAr_B_Ertek;

    @FXML
    private TextField aramPiaciAr;
    private int aramPiaciAr_Ertek;

    @FXML
    private TextField aramCsokkentettAr;
    private int aramCsokkentettAr_Ertek;

    @FXML
    private TextField gazPiaciAr;
    private int gazPiaciAr_Ertek;

    @FXML
    private TextField gazCsokkentettAr;
    private int gazCsokkentettAr_Ertek;

    private final SceneController sceneController = new SceneController();

    @FXML
    public void onExit() {
        Platform.exit();
    }

    @FXML
    public void onNext(ActionEvent event) throws IOException {

        if (AuthenticationService.getInstance().isLoggedIn()) {

            sceneController.sceneSetter(event, sceneController.getMAINSCENE_PATH());
            MainController mainController = sceneController.getFxmlLoader().getController();


            try {
                if (
                        aramCsokkentettAr.getText().isEmpty()
                                || aramPiaciAr.getText().isEmpty()
                                || gazCsokkentettAr.getText().isEmpty()
                                || gazPiaciAr.getText().isEmpty()
                                || aramPiaciAr_B.getText().isEmpty()
                                || aramCsokkentettAr_B.getText().isEmpty()
                ) {
                    mainController.saveDataFromFirstScene(
                            DataModel.DEFAULT_ARAM_PIACI_AR,
                            DataModel.DEFAULT_ARAM_CSOKKENTETT_AR,
                            DataModel.DEFAULT_ARAM_PIACI_AR_B,
                            DataModel.DEFAULT_ARAM_CSOKKENTETT_AR_B,
                            DataModel.DEFAULT_GAZ_PIACI_AR,
                            DataModel.DEFAULT_GAZ_CSOKKENTETT_AR
                    );

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Egy vagy több mezőt üresen hagyott: A program alapértelmezett értékekkel fog számolni!");
                    alert.setTitle("Alapértelmezett értékek");
                    alert.setResizable(false);
                    alert.show();
                } else {
                    setValues();

                    mainController.saveDataFromFirstScene(
                            aramPiaciAr_Ertek,
                            aramCsokkentettAr_Ertek,
                            aramPiaciAr_B_Ertek,
                            aramCsokkentettAr_B_Ertek,
                            gazPiaciAr_Ertek,
                            gazCsokkentettAr_Ertek
                    );
                }

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Számot adj meg!");
                alert.setTitle("Hiba");
                alert.setResizable(false);
                alert.show();
            }
        }
    }

    private void setValues() {
        aramPiaciAr_Ertek = Integer.parseInt(aramPiaciAr.getText());
        aramCsokkentettAr_Ertek = Integer.parseInt(aramCsokkentettAr.getText());
        aramPiaciAr_B_Ertek = Integer.parseInt(aramPiaciAr_B.getText());
        aramCsokkentettAr_B_Ertek = Integer.parseInt(aramCsokkentettAr_B.getText());
        gazPiaciAr_Ertek = Integer.parseInt(gazPiaciAr.getText());
        gazCsokkentettAr_Ertek = Integer.parseInt(gazCsokkentettAr.getText());
    }
}
