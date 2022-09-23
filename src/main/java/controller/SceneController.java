package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private String OPENINGPATH = "/fxml/opening_scene.fxml";
    private String MAINSCENEPATH = "/fxml/appview.fxml";
    private FXMLLoader fxmlLoader;

    public String getMAINSCENEPATH() {
        return MAINSCENEPATH;
    }

    public String getOPENINGPATH() {
        return OPENINGPATH;
    }

    public FXMLLoader getFxmlLoader() {return fxmlLoader;}

public void sceneSetter(ActionEvent event, String filename) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource(filename));

        Parent root = fxmlLoader.load();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
