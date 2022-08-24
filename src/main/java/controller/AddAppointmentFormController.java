package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddAppointmentFormController extends Controller {

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<?> contactComboBox;

    @FXML
    private ComboBox<?> customerComboBox;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<?> endTimeComboBox;

    @FXML
    private TextField idField;

    @FXML
    private TextField locationField;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ComboBox<?> startTimeComboBox;

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<?> userComboBox;

    @FXML
    void cancelButtonClicked(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/model/MainMenu.fxml");
    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/model/MainMenu.fxml");
    }

}
