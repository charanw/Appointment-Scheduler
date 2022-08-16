package controller;

import com.example.model.main;
import helper.controller;
import helper.userQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
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

    public void printToFile(String userName, String loginType, Timestamp timestamp) throws IOException {
        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        PrintWriter printer = new PrintWriter(fileWriter);
        printer.println(loginType + " login by " +userName + " at " + timestamp);
        printer.close();
    }

    @FXML
    void exitButtonClicked(ActionEvent event) {
    System.exit(0);
    }

    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException, SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String userName = userNameField.getText();
        String password = passwordField.getText();
        if(userQuery.lookupUsername(userName)) {
            if (userQuery.checkPassword(userName,password)) {
                changeScene(event, "/com/example/model/MainMenu.fxml");
                printToFile(userName, "Successful", timestamp);
            } else {
                error("Invalid password. PLease try again.");
                printToFile(userName, "Unsuccessful", timestamp);
            }
        } else {
            error("Invalid username. Please try again.");
            printToFile(userName, "Unsuccessful", timestamp);
        }
    }
}