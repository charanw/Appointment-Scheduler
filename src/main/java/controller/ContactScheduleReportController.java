package controller;


import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import dao.AppointmentQuery;
import dao.ContactQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class ContactScheduleReportController extends Controller {

    @FXML
    private TableColumn<Appointment, Customer> appointmentCustomerColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;

    @FXML
    private TableView<Appointment> appointmentsTable;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<Appointment, Contact> contactColumn;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endColumn;

    @FXML
    private TableColumn<Appointment, String> locationColumn;

    @FXML
    private Button mainMenuButton;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startColumn;

    @FXML
    private Button submitButton;

    @FXML
    private TableColumn<Appointment, String> titleColumn;

    @FXML
    private Label titleLabel;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, User> userColumn;
    @FXML
    private Label totalAppointmentsLabel;

    /**Initializes the contact schedule report controller.
     * Queries the database to add all contacts to the contact combo-box,
     * sets the appointments table's items to null, and sets the columns of the appointments table.*/
    public void initialize() throws SQLException {
        contactComboBox.setItems(ContactQuery.getAllContacts());
        appointmentsTable.setItems(null);
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Contact>("contact"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("end"));
        appointmentCustomerColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Customer>("customer"));
        userColumn.setCellValueFactory(new PropertyValueFactory<Appointment, User>("user"));
    }
    /**Clears the contact combox, sets the appointments table items to null, and resets the total appointments label*/
    @FXML
    void clearButtonClicked() {
    contactComboBox.getSelectionModel().clearSelection();
    appointmentsTable.setItems(null);
    totalAppointmentsLabel.setText("Total Appointments: ");
    }
    /**Closes the window and returns to the main menu*/
    @FXML
    void mainMenuButtonClicked() {
        Stage window = (Stage) mainMenuButton.getScene().getWindow();
        window.close();
    }
    /**Gets the selected contact from the contact combo-box and queries the database to get the all appointments.
     * Then, uses a lambda function as the predicate for the .forEach() method to add the appointments to an observable list that are assigned to the selected contact by comparing the contact ID.
     * Finally, sets the appointment table items to the contact's appointments,
     * counts the number of appointments for the contact, and updates the total appointments label.
     * By using the lambda function in this method, a new query doesn't have to be written and the processing can be done on the local machine instead of the database. This reduces the lines of code needed for this program and hypothetically saves server resources.
     * */
    @FXML
    void submitButtonClicked() throws SQLException {
        int contactId = contactComboBox.getSelectionModel().getSelectedItem().getContactId();
        ObservableList<Appointment> contactAppointments = FXCollections.observableArrayList();

        AppointmentQuery.getAllAppointments().forEach(appointment -> {
            if(appointment.getContact().getContactId() == (contactId)){
                contactAppointments.add(appointment);
            }
        });

        appointmentsTable.setItems(contactAppointments);
        long appointmentCount = contactAppointments.stream().count();
        totalAppointmentsLabel.setText("Total Appointments: " + appointmentCount);
    }
}