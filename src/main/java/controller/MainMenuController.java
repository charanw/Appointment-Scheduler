package controller;

import com.example.model.*;
import dao.ScheduleApplicationQuery;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MainMenuController extends Controller {

    private User user;
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
    private TableColumn<Appointment, Integer> contactColumn;

    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentCustomerIdColumn;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Customer, Integer> divisionIdColumn;

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
    private TableColumn<Appointment, Integer> userIdColumn;


    public void setUser(User user) {
        this.user = user;
    }
    public void initialize() throws SQLException {

        allCustomersTable.setItems(ScheduleApplicationQuery.getAllCustomers());
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        divisionIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, Integer>, ObservableValue<Integer>>() {
             public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Customer, Integer> p) {
                 ObservableValue<Integer> observableInt = new ReadOnlyObjectWrapper<>(p.getValue().getDivision().getDivisionId());
                 return observableInt;
             }
         });

        allAppointmentsTable.setItems(ScheduleApplicationQuery.getAllAppointments());
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("contactId"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("end"));
        appointmentCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));
    }


    @FXML
    void addAppointmentButtonClicked(ActionEvent event) {
        System.out.println(user.getUserName());
    }

    @FXML
    void addCustomerButtonClicked(ActionEvent event) {

    }

    @FXML
    void appointmentHistoryButtonClicked(ActionEvent event) {

    }

    @FXML
    void deleteAppointmentButtonClicked(ActionEvent event) {

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
    void updateAppointmentButtonClicked(ActionEvent event) {

    }

    @FXML
    void updateCustomerButtonClicked(ActionEvent event) {

    }

    @FXML
    void weekAppointmentToggleSelected(ActionEvent event) {

    }


    @FXML
    void allAppointmentToggleSelected(ActionEvent event) {

    }
}