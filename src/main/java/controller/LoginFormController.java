package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private Button exitButton;

    @FXML
    private Label locationLabel;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField userNameField;

    @FXML
    private Label userNameLabel;

    public void changeScene(ActionEvent event, String path) throws IOException {
        Stage stage;
        Parent scene;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(path));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void exitButtonClicked(ActionEvent event) {
    System.exit(0);
    }

    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException {
    changeScene(event,"/com/example/model/MainMenu.fxml");
    }
}