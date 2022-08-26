package controller;

import com.example.model.*;
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

public class ModifyAppointmentFormController extends Controller {

    private Appointment appointment;

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

    private ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private ObservableList<User> allUsers = FXCollections.observableArrayList();


    void setAppointment(Appointment appointment){
        this.appointment = appointment;
        idField.setText(String.valueOf(appointment.getAppointmentId()));
        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        typeField.setText(appointment.getType());
        contactComboBox.getSelectionModel().select(appointment.getContact());
        startDatePicker.setValue(appointment.getStart().toLocalDate());
        startTimeComboBox.getSelectionModel().select(appointment.getStart().toLocalTime());
        endDatePicker.setValue(appointment.getEnd().toLocalDate());
        endTimeComboBox.getSelectionModel().select(appointment.getEnd().toLocalTime());
        customerComboBox.getSelectionModel().select(appointment.getCustomer());
        userComboBox.getSelectionModel().select(appointment.getUser());
        LocalTime startTime = startTimeComboBox.getSelectionModel().getSelectedItem();
        LocalTime closeTime = ScheduleApplication.getBusinessCloseTime().toLocalTime();
        while (startTime.isBefore(closeTime)) {
            endTimeComboBox.getItems().add(startTime.plusMinutes(15));
            if (startTime.isBefore(closeTime)) {
                startTime = startTime.plusMinutes(15);
            }
        }
    }
    public void initialize() throws SQLException {
        allContacts = ScheduleApplicationQuery.getAllContacts();
        contactComboBox.setItems(allContacts);

        allUsers = ScheduleApplicationQuery.getAllUsers();
        userComboBox.setItems(allUsers);

        allCustomers = ScheduleApplicationQuery.getAllCustomers();
        customerComboBox.setItems(allCustomers);

        LocalTime openTime = ScheduleApplication.getBusinessOpenTime().toLocalTime();
        LocalTime closeTime = ScheduleApplication.getBusinessCloseTime().toLocalTime();
        while (openTime.isBefore(closeTime.plusSeconds(1))) {
            startTimeComboBox.getItems().add(openTime);
            openTime = openTime.plusMinutes(15);
        }

    }

    @FXML
    void startTimeUpdated(ActionEvent event) {
        endTimeComboBox.getItems().clear();
        LocalTime startTime = startTimeComboBox.getSelectionModel().getSelectedItem();
        LocalTime closeTime = ScheduleApplication.getBusinessCloseTime().toLocalTime();
        while(startTime.isBefore(closeTime)) {
            endTimeComboBox.getItems().add(startTime.plusMinutes(15));
            if(startTime.isBefore(closeTime)) {
                startTime = startTime.plusMinutes(15);
            }
        }
    }

    @FXML
    void cancelButtonClicked(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/model/MainMenu.fxml");
    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException, SQLException {
        int appointmentId = appointment.getAppointmentId();
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

        AppointmentQuery.updateAppointment(appointmentId, title, description, location, type, startDateTime, endDateTime, customerId, userId, contact);
        changeScene(event, "/com/example/model/MainMenu.fxml");
    }

}
