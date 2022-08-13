package helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class controller {
    public void changeScene(ActionEvent event, String path) throws IOException {
        Stage stage;
        Parent scene;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(path));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void error(String errorMessage) {
        Alert warning = new Alert(Alert.AlertType.ERROR);
        warning.setTitle("Error");
        warning.setContentText(errorMessage);
        warning.showAndWait();
    }
}
