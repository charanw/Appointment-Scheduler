package controller;

import com.example.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public abstract class Controller {

    Stage stage;
    Parent scene;
    User user;
    public void changeScene(ActionEvent event, String path) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(path));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void error(String errorMessage) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setContentText(errorMessage);
        error.showAndWait();
    }
    public boolean confirmation(String confirmationMessage) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setContentText(confirmationMessage);
        Optional<ButtonType> button = confirmation.showAndWait();
        if (button.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }
    public void alert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
}
