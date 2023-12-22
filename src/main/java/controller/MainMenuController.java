package controller;

import dao.AppointmentQuery;
import dao.CustomerQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class MainMenuController extends Controller {


    private ObservableList<Customer> allCustomers;
    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button customerLocationReportButton;

    @FXML
    private ToggleGroup appointmentToggle;

    @FXML
    private Button deleteAppointment;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private Button logoutButton;

    @FXML
    private RadioButton monthAppointmentToggle;

    @FXML
    private Button schedulesButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Button totalAppointmentsButton;

    @FXML
    private Button updateAppointmentButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private RadioButton weekAppointmentToggle;

    @FXML
    private RadioButton allAppointmentToggle;
    @FXML
    private TableView<Appointment> allAppointmentsTable;

    @FXML
    private TableView<Customer> allCustomersTable;


    @FXML
    private TableColumn<Customer, String> addressColumn;


    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;

    @FXML
    private TableColumn<Appointment, Contact> contactColumn;

    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;

    @FXML
    private TableColumn<Appointment, Customer> appointmentCustomerColumn;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Customer, FirstLevelDivision> divisionColumn;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endColumn;

    @FXML
    private TableColumn<Appointment, String> locationColumn;

    @FXML
    private TableColumn<Customer,String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> postalCodeColumn;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startColumn;

    @FXML
    private TableColumn<Appointment, String> titleColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, User> userColumn;

    @FXML
    private TableColumn<Customer, Country> countryColumn;
    /**Initializes the main menu. Queries the database to load data into the all customers and all appointment tables.*/
    public void initialize() throws SQLException {

        allCustomersTable.setItems(CustomerQuery.getAllCustomers());
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        divisionColumn.setCellValueFactory(new PropertyValueFactory<Customer, FirstLevelDivision>("division"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Customer, Country>("country"));

        allAppointmentsTable.setItems(AppointmentQuery.getAllAppointments());
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

    /**Opens a new window with the add appointment form*/
    @FXML
    void addAppointmentButtonClicked() throws IOException, SQLException {
        openNewWindow("/view/AddAppointmentForm.fxml");
        allAppointmentsTable.setItems(AppointmentQuery.getAllAppointments());
        allAppointmentToggle.setSelected(true);
    }
    /**Opens a new window with the add customer form*/
    @FXML
    void addCustomerButtonClicked() throws IOException, SQLException {
        openNewWindow("/view/AddCustomerForm.fxml");
        allCustomersTable.setItems(CustomerQuery.getAllCustomers());
    }
    /**Opens a new window with the customer location report*/
    @FXML
    void customerLocationReportButtonClicked() throws IOException {
        openNewWindow("/view/CustomersByLocationReport.fxml");
    }
    /**Gets the selected appointment from the all appointments table and queries the database to delete the selected appointment.
     * Displays an error message if no appointment is selected.
     * Displays a confirmation alert with the appointment's details, and then displays a message confirming the appointment has been deleted.
     * Refreshes the all appointments table once the appointment has been deleted.
     * */
    @FXML
    void deleteAppointmentButtonClicked() throws SQLException {
        Appointment selectedAppointment = allAppointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null){
        int appointmentId = selectedAppointment.getAppointmentId();
        String appointmentTitle = selectedAppointment.getTitle();
        String appointmentType = selectedAppointment.getType();
        if (confirmation("Are you sure you want to cancel this appointment:\r\n"
                + "ID #: " + appointmentId + "\r\n"
                + "Title: " + appointmentTitle + "\r\n"
                + "Type: " + appointmentType + "\r\n"
                + "From: " + selectedAppointment.getStart() + "\r\n"
                + "To: " + selectedAppointment.getEnd()))
        {
            AppointmentQuery.deleteAppointment(appointmentId);
            allAppointmentsTable.setItems(AppointmentQuery.getAllAppointments());
            allAppointmentToggle.setSelected(true);
            alert("Appointment #" + appointmentId + " " + appointmentTitle + " deleted.");
        }} else {
            error("No appointment selected. Please select an appointment and try again.");
        }
    }
    /**Gets the selected customer from the all customers table and queries the database to delete the selected customer.
     * Displays an error message if no customer is selected.
     * Displays a confirmation alert with the customer's details, and then displays a message confirming the customer has been deleted.
     * Refreshes the all appointments table once the appointment has been deleted.
     * */
    @FXML
    void deleteCustomerButtonClicked(ActionEvent event) throws SQLException {
        Customer customer = allCustomersTable.getSelectionModel().getSelectedItem();
        if (customer != null) {
            int customerId = customer.getCustomerId();
            ObservableList<Appointment> customerAppointments = AppointmentQuery.getCustomerAppointments(customerId);
            if (confirmation("Are you sure you want to delete customer  #" + customerId + " " + customer.getCustomerName() + " and all associated appointments?")) {
                for (Appointment appointment : customerAppointments) {
                    AppointmentQuery.deleteAppointment(appointment.getAppointmentId());
                }
                CustomerQuery.deleteCustomer(customerId);
                allCustomersTable.setItems(CustomerQuery.getAllCustomers());
                allAppointmentsTable.setItems(AppointmentQuery.getAllAppointments());
                allAppointmentToggle.setSelected(true);
            }} else {
                error("No customer selected. Please select a customer and try again.");
            }
        }
    /**Gets the stage from the event generated when the logout button is clicked and then loads and displays the login menu.*/
    @FXML
    void logoutButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
        stage.centerOnScreen();
    }
    /**Opens a new window with the contact schedule report*/
    @FXML
    void schedulesButtonClicked() throws IOException {
        openNewWindow("/view/ContactScheduleReport.fxml");
    }
    /**Opens a new window with the total appointments report*/
    @FXML
    void totalAppointmentsButtonClicked() throws IOException {
        openNewWindow("/view/TotalAppointmentsReport.fxml");
    }
    /**Gets the selected appointment from the all appointments table and then loads the update appointment form and gets the controller.
     * Displays an error if no appointment is selected.
     * Calls the setAppointment method from the update appointment form controller and passes in the selected appointment.
     * Then opens a new window with the update appointment form.
     * Refreshes the all appointments table when the new window is closed.*/
    @FXML
    void updateAppointmentButtonClicked(ActionEvent event) throws IOException, SQLException {
        Appointment appointment = allAppointmentsTable.getSelectionModel().getSelectedItem();
        if(appointment != null){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/UpdateAppointmentForm.fxml"));
        loader.load();

        UpdateAppointmentFormController controller = loader.getController();
        controller.setAppointment(appointment);

        Stage newWindow = new Stage();
        Parent scene = loader.getRoot();
        newWindow.setScene(new Scene(scene));
        newWindow.showAndWait();

        allAppointmentsTable.setItems(AppointmentQuery.getAllAppointments());
        allAppointmentToggle.setSelected(true);
        } else {
            error("No appointment selected. Please select an appointment and try again.");
        }
    }
    /**Gets the selected customer from the all customers table and then loads the update customer form and gets the controller.
     * Displays an error if no customer is selected.
     * Calls the setCustomer method from the update customer form controller and passes in the selected customer.
     * Then opens a new window with the update customer form.
     * Refreshes the all customers table when the new window is closed.*/
    @FXML
    void updateCustomerButtonClicked() throws IOException, SQLException {
        Customer customer = allCustomersTable.getSelectionModel().getSelectedItem();
        if(customer != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateCustomerForm.fxml"));
            loader.load();

            UpdateCustomerFormController controller = loader.getController();
            controller.setCustomer(customer);

            Stage newWindow = new Stage();
            Parent scene = loader.getRoot();
            newWindow.setScene(new Scene(scene));
            newWindow.showAndWait();

            allCustomersTable.setItems(CustomerQuery.getAllCustomers());
        } else {
            error("No customer selected. Please select a customer and try again.");
        }
    }
    /**When the month appointment toggle is selected, filters the all appointments table to only display appointments with the current month.
     * Uses a lambda function as the predicate of the .stream().filter() method. The lambda function gets the current month from the current date and compares it to the month for each appointment.
     * If they are equal, they are added to the filtered observable list. Using the lambda function reduces the amount of code that would otherwise be needed to write a for loop to achieve the same result, and makes for more readable code.
     * */
    @FXML
    void monthAppointmentToggleSelected() throws SQLException {
        ObservableList<Appointment> allAppointments = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> appointmentsThisMonth = allAppointments.stream().filter(appointment ->
                ((appointment.getStart().toLocalDate().getMonth().equals(LocalDate.now().getMonth()))))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        allAppointmentsTable.setItems(appointmentsThisMonth);
    }
    /**When the week appointment toggle is selected, filters the all appointments table to only display upcoming appointments within 7 days.
     * The lambda function used here gets the current date and compares it to the LocalDate for each appointment. If the current dates match,
     * or if the date of the appointment is after the current date and before the current date plus 7 days, it is added to the filtered list of appointments.
     * This lambda uses 3 conditions compared to the lambda used above, and the code below is also simplified by the use of a lambda function.
     * */
    @FXML
    void weekAppointmentToggleSelected() throws SQLException {
        ObservableList<Appointment> allAppointments = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> appointmentsThisWeek = allAppointments.stream().filter(appointment ->
                ((appointment.getStart().toLocalDate().isEqual(LocalDate.now()) ||
                        ((appointment.getStart().isAfter(LocalDateTime.now())) &&
                                (appointment.getStart().isBefore(LocalDateTime.now().plusDays(7)))))))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        allAppointmentsTable.setItems(appointmentsThisWeek);
    }
    /**When the all appointment toggle is selected, this method resets the all appointments table by querying the database and displaying all appointments again.  */
    @FXML
    void allAppointmentToggleSelected() throws SQLException {
        allAppointmentsTable.setItems(AppointmentQuery.getAllAppointments());
    }
}