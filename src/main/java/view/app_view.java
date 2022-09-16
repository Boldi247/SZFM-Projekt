package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class app_view extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/appview.fxml"));
        stage.setTitle("Rezsi kalkulátor");
        Scene primaryScene = new Scene(root);
        stage.setScene(primaryScene);
        stage.show();
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello World!");
    }
}
