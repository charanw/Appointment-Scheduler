package controller;

import com.example.model.*;
import dao.AppointmentQuery;
import dao.ScheduleApplicationQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MainMenuController extends Controller {


    private ObservableList<Customer> allCustomers;
    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button appointmentHistoryButton;

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

    @FXML
    private Label loggedInAsLabel;


    public void setUser(User user) {
        this.user = user;
        loggedInAsLabel.setText("Logged in as: " + user.getUserName());
    }
    public void initialize() throws SQLException {

        allCustomersTable.setItems(ScheduleApplicationQuery.getAllCustomers());
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        divisionColumn.setCellValueFactory(new PropertyValueFactory<Customer, FirstLevelDivision>("division"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Customer, Country>("country"));

        allAppointmentsTable.setItems(ScheduleApplicationQuery.getAllAppointments());
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


    @FXML
    void addAppointmentButtonClicked(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/model/AddAppointmentForm.fxml");
    }

    @FXML
    void addCustomerButtonClicked(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/model/AddCustomerForm.fxml");
    }

    @FXML
    void appointmentHistoryButtonClicked(ActionEvent event) {

    }

    @FXML
    void deleteAppointmentButtonClicked(ActionEvent event) throws SQLException {
        Appointment selectedAppointment = allAppointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null){
        int appointmentId = selectedAppointment.getAppointmentId();
        String appointmentTitle = selectedAppointment.getTitle();
        if (confirmation("Are you sure you want to delete appointment #" + appointmentId + " " + appointmentTitle)){
            AppointmentQuery.deleteAppointment(appointmentId);
            allAppointmentsTable.setItems(ScheduleApplicationQuery.getAllAppointments());
            alert("Appointment #" + appointmentId + " " + appointmentTitle + " deleted.");
        }} else {
            error("No appointment selected. Please select an appointment and try again.");
        }
    }

    @FXML
    void deleteCustomerButtonClicked(ActionEvent event) {

    }

    @FXML
    void logoutButtonClicked(ActionEvent event) throws IOException {
    changeScene(event, "/com/example/model/LoginForm.fxml");
    }

    @FXML
    void monthAppointmentToggleSelected(ActionEvent event) {

    }

    @FXML
    void schedulesButtonClicked(ActionEvent event) {

    }

    @FXML
    void totalAppointmentsButtonClicked(ActionEvent event) {

    }

    @FXML
    void updateAppointmentButtonClicked(ActionEvent event) throws IOException {
        Appointment appointment = allAppointmentsTable.getSelectionModel().getSelectedItem();
        if(appointment != null){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/model/ModifyAppointmentForm.fxml"));
        loader.load();

        ModifyAppointmentFormController controller = loader.getController();
        controller.setAppointment(appointment);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();} else {
            error("No appointment selected. Please select an appointment and try again.");
        }
    }

    @FXML
    void updateCustomerButtonClicked(ActionEvent event) throws IOException {
        Customer customer = allCustomersTable.getSelectionModel().getSelectedItem();
        if(customer != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/model/ModifyCustomerForm.fxml"));
            loader.load();

            ModifyCustomerFormController controller = loader.getController();
            controller.setCustomer(customer);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();} else {
            error("No customer selected. Please select a customer and try again.");
        }
    }

    @FXML
    void weekAppointmentToggleSelected(ActionEvent event) {

    }


    @FXML
    void allAppointmentToggleSelected(ActionEvent event) {

    }
}