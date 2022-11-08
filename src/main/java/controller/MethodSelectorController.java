package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MethodSelectorController {

    SceneController sceneController = new SceneController();

    public void normal(ActionEvent event) throws IOException {
        sceneController.sceneSetter(event, sceneController.getOPENING_PATH());
    }

    public void hofokAlapu(ActionEvent event) throws IOException {
        sceneController.sceneSetter(event, sceneController.getHOFOK_ALAPU_PATH());
    }

    public void onExit(ActionEvent event) {
        Platform.exit();
    }
}
