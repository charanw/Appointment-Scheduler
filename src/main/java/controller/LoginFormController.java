package controller;

import helper.controller;
import helper.userQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginFormController extends controller {

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

    @FXML
    void exitButtonClicked(ActionEvent event) {
    System.exit(0);
    }

    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException, SQLException {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        if(userQuery.lookupUsername(userName)) {
            if (Objects.equals(password, userQuery.getPassword(userName))) {
                changeScene(event, "/com/example/model/MainMenu.fxml");
            } else {
                error("Invalid password. PLease try again.");
            }
        } else {
            error("Invalid username. Please try again.");
        }
    }
}