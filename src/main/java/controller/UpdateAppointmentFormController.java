package controller;

import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class UpdateAppointmentFormController extends Controller {

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
    private Label startTimezoneLabel;

    @FXML
    private Label endTimezoneLabel;

    private ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private ObservableList<User> allUsers = FXCollections.observableArrayList();
    /**This method takes an appointment, gets the ID, title, description, location, type, contact, start, end, customer, and user,
     * and displays the data in the relevant fields and combo-boxes so that the data can be updated. It gets the dates from the start and end LocalDateTimes and displays them in the appropriate date pickers.
     * It also gets the time from the start and end LocalDateTimes, formats them, and displays them in the appropriate text fields.
     * @param appointment the appointment to be updated
     * */
    void setAppointment(Appointment appointment){
        this.appointment = appointment;
        idField.setText(String.valueOf(appointment.getAppointmentId()));
        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        typeField.setText(appointment.getType());
        contactComboBox.getSelectionModel().select(appointment.getContact());
        startDatePicker.setValue(appointment.getStart().toLocalDate());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        startTimeField.setText(appointment.getStart().toLocalTime().format(formatter));
        endDatePicker.setValue(appointment.getEnd().toLocalDate());
        endTimeField.setText(appointment.getEnd().toLocalTime().format(formatter));
        customerComboBox.getSelectionModel().select(appointment.getCustomer());
        userComboBox.getSelectionModel().select(appointment.getUser());
    }
    /**Initializes the update appointment form controller. Queries the database to get all contacts, users, and customers, and displays them in the appropriate combo-boxes.
     * Sets the time zone label to the default system time zone.
     * */
    public void initialize() throws SQLException {
        allContacts = ContactQuery.getAllContacts();
        contactComboBox.setItems(allContacts);

        allUsers = UserQuery.getAllUsers();
        userComboBox.setItems(allUsers);

        allCustomers = CustomerQuery.getAllCustomers();
        customerComboBox.setItems(allCustomers);

        startTimezoneLabel.setText(ZoneId.systemDefault().getDisplayName(TextStyle.SHORT.asStandalone(), Locale.getDefault()));
        endTimezoneLabel.setText(ZoneId.systemDefault().getDisplayName(TextStyle.SHORT.asStandalone(), Locale.getDefault()));
    }
    /**Closes the window without saving any data*/
    @FXML
    void cancelButtonClicked() {
        Stage window = (Stage) cancelButton.getScene().getWindow();
        window.close();
    }
    /**Saves the updated appointment to the database.
     * First, this method gets the data from the relevant fields and combo-boxes and makes sure they aren't empty.
     * If they are empty, an error message indicating which field or combo-box is shown.
     * It then formats the string from the start and end time fields, and combines it with the dates from the start and end date pickers to create a start and an end LocalDateTime.
     * If the times are not entered with the correct format, an error message indicating which time is displayed.
     * It checks to see if the start and end LocalDateTime have changed. If not, it displays a message informing the user.
     * If the times have changed, it then loops through all the appointments for the selected customer to check for any appointment overlaps.
     * If no changes were made to the appointment time, an alert is displayed informing the user.
     * If the appointment being updated has the same ID as the appointment currently being compared in the loop, the appointment is skipped as an appointment cannot overlap with itself.
     * If the appointment start LocalDateTime is the same or during an existing appointment,
     * if the end LocalDateTime is the same or during an existing appointment,
     * or if the start LocalDateTime is before an existing appointment and the end LocalDateTime is after an existing appointment,
     * then the appointment overlaps with an existing appointment, an error is shown, and new appointment is not saved.
     * Finally, the LocalDateTimes are compared with the business hours of 8:00 am EST to 10:00pm EST, converted from a ZonedDateTime in EST to a LocalDateTime.
     * As long as the start LocalDateTime is not before 8:00am EST, and the end LocalDateTime is not after 10:00 pm EST,
     * the appointment is saved to the database, and the window is closed.
     * If the appointment falls outside business hours a relevant error message is shown.
     * */
    @FXML
    void saveButtonClicked() throws SQLException {
        int appointmentId = appointment.getAppointmentId();
        String title = titleField.getText();
        if (title.isEmpty()){
            error("Invalid data. Title cannot be empty, please enter a title.");
            return;
        }

        String description = descriptionField.getText();
        if (description.isEmpty()){
            error("Invalid data. Description cannot be empty, please enter a description.");
            return;
        }

        String location = locationField.getText();
        if (location.isEmpty()){
            error("Invalid data. Location cannot be empty, please enter a location.");
            return;
        }

        String type = typeField.getText();
        if (type.isEmpty()){
            error("Invalid data. Type cannot be empty, please enter an appointment type.");
            return;
        }

        Contact contact = contactComboBox.getSelectionModel().getSelectedItem();
        if (contact == null){
            error("Invalid data. Please select a contact.");
            return;
        }
        int contactId = contact.getContactId();
        LocalDate startDate = startDatePicker.getValue();
        if (startDate == null){
            error("Invalid data. Please enter a start date.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime startTime;

        try {

            startTime = LocalTime.parse(startTimeField.getText(), formatter);
        } catch (Exception DateTimeParseException) {
            error("Invalid start time entered. Please enter time as HH:mm in 24 hour format.");
            return;
        }

        LocalDateTime newStartDateTime = LocalDateTime.of(startDate, startTime);

        LocalDate endDate = endDatePicker.getValue();
        if (endDate == null){
            error("Invalid data. Please enter an end date.");
            return;
        }

        LocalTime endTime;

        try {
            endTime = LocalTime.parse(endTimeField.getText(), formatter);
        } catch (Exception DateTimeParseException) {
            error("Invalid end time entered. Please enter time as HH:mm in 24 hour format.");
            return;
        }

        LocalDateTime newEndDateTime = LocalDateTime.of(endDate, endTime);

        Customer customer = customerComboBox.getSelectionModel().getSelectedItem();
        if (customer == null){
            error("Invalid data. Please select a customer.");
            return;
        }
        int customerId = customer.getCustomerId();

        User user = userComboBox.getSelectionModel().getSelectedItem();
        if (user == null){
            error("Invalid data. Please select a user.");
            return;
        }
        int userId = user.getUserId();

        ObservableList<Appointment> customerAppointments = AppointmentQuery.getCustomerAppointments(customer.getCustomerId());

        if((newStartDateTime.isEqual(appointment.getStart()) && newEndDateTime.isEqual(appointment.getEnd()))){
            alert("No changes made to this appointment's time and date");
        } else {
            for (Appointment appointment : customerAppointments) {
                if (appointment.getAppointmentId() == this.appointment.getAppointmentId()) {
                    continue;
                }
                if ((newStartDateTime.isAfter(appointment.getStart()) || newStartDateTime.isEqual(appointment.getStart())) && newStartDateTime.isBefore(appointment.getEnd())) {
                    error("This appointment starts during an existing appointment for this customer. The conflicting appointment:\r\n\r\n" +
                            "Appointment ID #:" + appointment.getAppointmentId() + "\r\n" +
                            "Title: " + appointment.getTitle() + "\r\n" +
                            "From: " + appointment.getStart() + "\r\n" +
                            "To: " + appointment.getEnd() + "\r\n" + "\r\n" +
                            "Please select a new start time and try again.");
                    return;
                }
                if (newEndDateTime.isAfter(appointment.getStart()) && (newEndDateTime.isBefore(appointment.getEnd()) || newEndDateTime.isEqual(appointment.getEnd()))) {
                    error("This appointment ends during an existing appointment for this customer. The conflicting appointment:\r\n\r\n" +
                            "Appointment ID #:" + appointment.getAppointmentId() + "\r\n" +
                            "Title: " + appointment.getTitle() + "\r\n" +
                            "From: " + appointment.getStart() + "\r\n" +
                            "To: " + appointment.getEnd() + "\r\n" + "\r\n" +
                            "Please select a new end time and try again.");
                    return;
                }
                if ((newStartDateTime.isBefore(appointment.getStart()) || newEndDateTime.isEqual(appointment.getStart())) && (newEndDateTime.isAfter(appointment.getEnd()) || newEndDateTime.isEqual(appointment.getEnd()))) {
                    error("This appointment overlaps with an existing appointment for this customer. The conflicting appointment:\r\n\r\n" +
                            "Appointment ID #:" + appointment.getAppointmentId() + "\r\n" +
                            "Title: " + appointment.getTitle() + "\r\n" +
                            "From: " + appointment.getStart() + "\r\n" +
                            "To: " + appointment.getEnd() + "\r\n" + "\r\n" +
                            "Please select a new start and end time and try again.");
                    return;
                }
            }
        }

        LocalDate today = LocalDate.now();
        LocalTime openTime = LocalTime.of(8,0);
        LocalTime closeTime = LocalTime.of(22,0);
        ZoneId businessTimezone = ZoneId.of("America/New_York");

        ZonedDateTime zonedOpenDateTime =  ZonedDateTime.of(today, openTime, businessTimezone);
        ZonedDateTime zonedCloseDateTime =  ZonedDateTime.of(today, closeTime, businessTimezone);

        LocalDateTime localOpenDateTime = zonedOpenDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localCloseDateTime = zonedCloseDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

        if (newStartDateTime.isBefore(newEndDateTime)) {
            if (!newStartDateTime.toLocalTime().isBefore(localOpenDateTime.toLocalTime().minusSeconds(1))) {
                if (!newEndDateTime.toLocalTime().isAfter(localCloseDateTime.toLocalTime().plusSeconds(1))) {
                    AppointmentQuery.updateAppointment(appointmentId, title, description, location, type, newStartDateTime, newEndDateTime, customerId, userId, contactId);
                    Stage window = (Stage) saveButton.getScene().getWindow();
                    window.close();
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
