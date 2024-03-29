package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public abstract class Controller {

    /**Gets the stage from a button, loads and displays the main menu, and maximizes the window
     * @param event the event generated by the button, used to get the stage
     * */
    public void loadMainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
        stage.setMaximized(true);
    }
    /**Opens a new window with a given fxml resource path, and waits until the window is closed to continue
     * @param path the path of the fxml resource file to display in a new window
     * */
    public void openNewWindow(String path) throws IOException {
        Stage newWindow = new Stage();
        Parent newParent = FXMLLoader.load(getClass().getResource(path));
        Scene newScene = new Scene(newParent);
        newWindow.setScene(newScene);
        newWindow.showAndWait();
    }
    /**Displays an error with a given message
     * @param errorMessage the error message to be displayed
     * */
    public void error(String errorMessage) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeight(400.00);
        error.setContentText(errorMessage);
        error.showAndWait();
    }
    /**Displays a confirmation with a given message
     * @param confirmationMessage the confirmation message to be displayed
     * @return Returns true if the user selects yes, otherwise returns false.
     * */
    public boolean confirmation(String confirmationMessage) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, confirmationMessage, yes, cancel);
        confirmation.setTitle("Confirmation");
        Optional<ButtonType> button = confirmation.showAndWait();
        if (button.get() == yes) {
            return true;
        }
        return false;
    }
    /**Displays an alert with a given message
     * @param alertMessage the alert message to be displayed
     * */
    public void alert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeight(300.00);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
}
