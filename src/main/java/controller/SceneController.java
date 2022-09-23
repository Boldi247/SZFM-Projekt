package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;

@Data
public class SceneController {

    private String OPENING_PATH = "/fxml/opening_scene.fxml";
    private String MAINSCENE_PATH = "/fxml/appview.fxml";
    private FXMLLoader fxmlLoader;

public void sceneSetter(ActionEvent event, String filename) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource(filename));

        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
