package controller;

import model.Appointment;
import dao.AppointmentQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Month;
import java.util.Collections;

public class TotalAppointmentsReportController extends Controller{


    @FXML
    private Button clearButton;

    private ObservableList<Month> months = FXCollections.observableArrayList();

    @FXML
    private Button mainMenuButton;

    @FXML
    private ComboBox<Month> monthComboBox;

    @FXML
    private Label totalLabel;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private Button runReportButton;
    /**Initializes the total appointments report controller. Adds all the Java month enumerations to an observable list and displays them in the month combo-box.
     * Queries the database to get all types and displays the min the type combo-box.*/
    public void initialize() throws SQLException {
        Collections.addAll(months, Month.values());
        monthComboBox.setItems(months);
        typeComboBox.setItems(AppointmentQuery.getAllTypes());
    }
    /**Clears the month and type combo-box, and resets the total label text.*/
    @FXML
    void clearButtonClicked() {
        monthComboBox.getSelectionModel().clearSelection();
        typeComboBox.getSelectionModel().clearSelection();
        totalLabel.setText("Total Appointments: ");
    }
    /**Gets the selected month and type from the combo-boxes, and queries the database to get all appointments.
     * Then, filters all appointments to only those that have a start date within the selected month and match the selected type.*/
    @FXML
    void runReportButtonClicked() throws SQLException {
        Month month = monthComboBox.getSelectionModel().getSelectedItem();
        String type = typeComboBox.getSelectionModel().getSelectedItem();
        ObservableList<Appointment> allAppointments = AppointmentQuery.getAllAppointments();
        long appointmentCount = allAppointments.stream().filter(appointment -> appointment.getType().equals(type) && appointment.getStart().getMonth().equals(month)).count();
        totalLabel.setText("Total Appointments: " + appointmentCount);
    }
    /**Closes the window and returns to the main menu*/
    @FXML
    void mainMenuButtonClicked() throws IOException {
        Stage window = (Stage) mainMenuButton.getScene().getWindow();
        window.close();
    }
}
