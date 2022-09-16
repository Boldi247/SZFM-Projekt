package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    public static final String OPENINGPATH = "/fxml/opening_scene.fxml";
    public static final String MAINSCENEPATH = "/fxml/appview.fxml";


    public static void sceneSetter(ActionEvent event, String filename) throws IOException {
        Parent root = FXMLLoader.load(SceneController.class.getResource(filename));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rezsi kalkulátor");
        Scene primaryScene = new Scene(root);
        stage.setScene(primaryScene);
        stage.show();
    }
}
