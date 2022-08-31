package controller;

import com.example.model.Contact;
import com.example.model.Customer;
import com.example.model.User;
import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

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

    @FXML
    private Label startTimeZoneLabel;


    @FXML
    private Label endTimeZoneLabel;

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

        startTimeZoneLabel.setText(ZoneId.systemDefault().getDisplayName(TextStyle.SHORT.asStandalone(), Locale.US));
        endTimeZoneLabel.setText(ZoneId.systemDefault().getDisplayName(TextStyle.SHORT.asStandalone(), Locale.US));
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

        LocalDate today = LocalDate.now();
        LocalTime openTime = LocalTime.of(8,0);
        LocalTime closeTime = LocalTime.of(20,0);
        ZoneId businessTimezone = ZoneId.of("America/New_York");

        ZonedDateTime zonedOpenDateTime =  ZonedDateTime.of(today, openTime, businessTimezone);
        ZonedDateTime zonedCloseDateTime =  ZonedDateTime.of(today, closeTime, businessTimezone);

        LocalDateTime localOpenDateTime = zonedOpenDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localCloseDateTime = zonedCloseDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

if (startDateTime.isBefore(endDateTime)) {
    if (!startDateTime.isBefore(localOpenDateTime.minusSeconds(1))) {
        if (!endDateTime.isAfter(localCloseDateTime.plusSeconds(1))) {
            AppointmentQuery.addAppointment(title, description, location, type, startDateTime, endDateTime, customerId, userId, contact);
            changeScene(event, "/com/example/model/MainMenu.fxml");
        } else {
            error("Invalid data. End time must be in-between business hours of 8:00 am and 10:00 pm EST.");
        }
    } else {
        error("Invalid data. Start time must be in-between business hours of 8:00 am and 10:00 pm EST.");
    }
} else {
    error("Invalid data. Start date and time must be before end date and time.");
}
    }

}
