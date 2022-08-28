package controller;

import com.example.model.Contact;
import com.example.model.Customer;
import com.example.model.User;
import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddAppointmentFormController extends Controller {

    private ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private ObservableList<User> allUsers = FXCollections.observableArrayList();

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField endTimeField;

    @FXML
    private TextField idField;

    @FXML
    private TextField locationField;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField startTimeField;

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<User> userComboBox;

    @FXML
    private TextField typeField;

    public void initialize() throws SQLException {
        allContacts = ContactQuery.getAllContacts();
        contactComboBox.setItems(allContacts);
        contactComboBox.setPromptText("Select a contact:");

        allUsers = UserQuery.getAllUsers();
        userComboBox.setItems(allUsers);
        userComboBox.setPromptText("Select a user:");

        allCustomers = CustomerQuery.getAllCustomers();
        customerComboBox.setItems(allCustomers);
        customerComboBox.setPromptText("Select a customer:");
        }

    @FXML
    void cancelButtonClicked(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/model/MainMenu.fxml");
    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException, SQLException {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        String type = typeField.getText();

        Contact contact = contactComboBox.getSelectionModel().getSelectedItem();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate startDate = startDatePicker.getValue();

        LocalTime startTime;

        try {
            startTime = LocalTime.parse(startTimeField.getText(), formatter);
        } catch (Exception DateTimeParseException) {
            error("Invalid start time entered. Please enter time as HH:mm in 24 hour format.");
            return;
        }

        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

        LocalDate endDate = endDatePicker.getValue();


        LocalTime endTime;

        try {
            endTime = LocalTime.parse(endTimeField.getText(), formatter);
        } catch (Exception DateTimeParseException) {
            error("Invalid end time entered. Please enter time as HH:mm in 24 hour format.");
            return;
        }

        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        Customer customer = customerComboBox.getSelectionModel().getSelectedItem();
        int customerId = customer.getCustomerId();

        User user = userComboBox.getSelectionModel().getSelectedItem();
        int userId = user.getUserId();

        LocalTime openTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York")).toLocalTime();
        LocalTime closeTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(20, 0), ZoneId.of("America/New_York")).toLocalTime();


if (startDateTime.isBefore(endDateTime)) {
    if (startTime.isAfter(openTime.minusSeconds(1)) && endTime.isBefore(closeTime.plusSeconds(1))) {
        AppointmentQuery.addAppointment(title, description, location, type, startDateTime, endDateTime, customerId, userId, contact);
        changeScene(event, "/com/example/model/MainMenu.fxml");
    } else {
        error("Invalid data. Start time must be in-between business hours of 8:00 am and 10:00 pm EST.");
    }
} else {
    error("Invalid data. Start date and time must be before end date and time.");
}
    }

}
