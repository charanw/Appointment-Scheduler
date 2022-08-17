package controller;

import com.example.model.User;
import dao.UserQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Locale;

public class LoginFormController extends Controller {
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

    public void initialize(){
        this.user = new User();
        user.setUserLocation(ZoneId.systemDefault());
        user.setUserLanguage(Locale.getDefault());
        locationLabel.setText("Your location is: " + user.getUserLocation());
    }

    public void recordLoginHistory(String userName, String loginType, Timestamp timestamp) throws IOException {
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
        user.setUserName(userNameField.getText());
        user.setPassword(passwordField.getText());
        if (UserQuery.login(user.getUserName(),user.getPassword())) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/model/MainMenu.fxml"));
            loader.load();

            MainMenuController controller = loader.getController();
            controller.setUser(user);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

            recordLoginHistory(user.getUserName(), "Successful", timestamp);
        } else {
            error("Invalid credentials. PLease try again.");
            recordLoginHistory(user.getUserName(), "Unsuccessful", timestamp);
        }
        }
    }