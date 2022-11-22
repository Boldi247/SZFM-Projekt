package controller;

import exceptions.EmailNotUniqueException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.AuthenticationService;
import service.MysqlService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    private MysqlService mysqlService = new MysqlService();

    @FXML
    private AnchorPane globalStage;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField loginUsernameField;

    @FXML
    private PasswordField loginPasswordField;

    private static String passwordOfUser;
    public static String getPasswordOfUser() {return passwordOfUser;}

    public void onLogin() {
        mysqlService.connectToServer();
        if (mysqlService.login(loginUsernameField.getText(), loginPasswordField.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("SIKERES BEJELENTKEZÉS");
            alert.show();

            passwordOfUser = loginPasswordField.getText();

            showLoggedInScreen();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("SIKERTELEN BEJELENTKEZÉS");
            alert.show();
        }
        mysqlService.closeConnection();
    }

    public void onRegistration() {
        mysqlService.connectToServer();
        try {
            mysqlService.registration(username.getText(), password.getText(), email.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("SIKERES REGISZTRÁCIÓ");
            alert.show();

        } catch (EmailNotUniqueException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("EMAIL CÍM NEM EGYEDI!");
            alert.show();
        }
        mysqlService.closeConnection();
    }

    private void showLoggedInScreen() {
        Stage stage = (Stage) globalStage.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/MethodSelector.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Rezsi kalkulátor");
        Scene primaryScene = new Scene(root);
        stage.setScene(primaryScene);
        stage.show();
    }
}
