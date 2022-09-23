package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpeningScene extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/opening_scene.fxml"));
        stage.setTitle("Rezsi kalkulátor");
        Scene primaryScene = new Scene(root);
        stage.setScene(primaryScene);
        stage.show();
    }
}
