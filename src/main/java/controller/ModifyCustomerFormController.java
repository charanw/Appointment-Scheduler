package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ModifyCustomerFormController extends Controller {

    @FXML
    private TextField addressField;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<?> countryComboBox;

    @FXML
    private ComboBox<?> firstLevelDivisionComboBox;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField postalCodeField;

    @FXML
    private Button saveButton;

    @FXML
    void cancelButtonClicked(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/model/MainMenu.fxml");
    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/model/MainMenu.fxml");
    }

}
