package controller;

import com.example.model.Contact;
import com.example.model.Customer;
import com.example.model.ScheduleApplication;
import com.example.model.User;
import dao.AppointmentQuery;
import dao.ScheduleApplicationQuery;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private ComboBox<LocalTime> endTimeComboBox;

    @FXML
    private TextField idField;

    @FXML
    private TextField locationField;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ComboBox<LocalTime> startTimeComboBox;

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<User> userComboBox;

    @FXML
    private TextField typeField;

    public void initialize() throws SQLException {
        allContacts = ScheduleApplicationQuery.getAllContacts();
        contactComboBox.setItems(allContacts);
        contactComboBox.setPromptText("Select a contact:");

        allUsers = ScheduleApplicationQuery.getAllUsers();
        userComboBox.setItems(allUsers);
        userComboBox.setPromptText("Select a user:");

        allCustomers = ScheduleApplicationQuery.getAllCustomers();
        customerComboBox.setItems(allCustomers);
        customerComboBox.setPromptText("Select a customer:");

        LocalTime time = LocalTime.of(00,00);
        while(time.isBefore(LocalTime.of(12, 00))) {
            startTimeComboBox.getItems().add(time);
            endTimeComboBox.getItems().add(time);
            time = time.plusMinutes(30);
        }
    }


    @FXML
    void startTimeUpdated(ActionEvent event) {

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

        LocalDate startDate = startDatePicker.getValue();
        LocalTime startTime = startTimeComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

        LocalDate endDate = endDatePicker.getValue();
        LocalTime endTime = endTimeComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        Customer customer = customerComboBox.getSelectionModel().getSelectedItem();
        int customerId = customer.getCustomerId();

        User user = userComboBox.getSelectionModel().getSelectedItem();
        int userId = user.getUserId();

        LocalTime openTime = ScheduleApplication.getBusinessOpenTime().toLocalTime();
        LocalTime closeTime = ScheduleApplication.getBusinessCloseTime().toLocalTime();


if (startDateTime.isBefore(endDateTime)) {
    if (startTime.isAfter(openTime) && endTime.isBefore(closeTime)) {
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
