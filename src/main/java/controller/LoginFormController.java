package controller;

import model.Appointment;
import model.User;
import dao.AppointmentQuery;
import dao.UserQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginFormController extends Controller {

    private Locale userLocale;

    @FXML
    private Button exitButton;

    @FXML
    private Label locationLabel;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField userNameField;

    @FXML
    private Label userNameLabel;

    /**
     * Initialize the login from controller. Gets the system default ZoneId and Locale.
     * If the default system language is French, translates the location label, title label, username label, password label, exit button, and login button.
     * Sets the location label to display the users ZoneId.
     */
    public void initialize() {
        ZoneId userLocation = ZoneId.systemDefault();
        this.userLocale = Locale.getDefault();
        if (userLocale.getLanguage().equals("fr")) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("localization/Nat", userLocale);
            locationLabel.setText(resourceBundle.getString("regionStatement") + userLocation);
            titleLabel.setText(resourceBundle.getString("applicationTitle"));
            userNameLabel.setText(resourceBundle.getString("username"));
            passwordLabel.setText(resourceBundle.getString("password"));
            exitButton.setText(resourceBundle.getString("exit"));
            loginButton.setText(resourceBundle.getString("login"));
        } else {
            locationLabel.setText("Your location is: " + userLocation);
        }
    }

    /**
     * Records a log of the login history including username, if the login was successful, and the time of the login attempt.
     * Saves the log to a text filed titled login_activity in the project directory.
     *
     * @param userName  the username entered during the login attempt
     * @param loginType a string stating whether the login was successful or unsuccessful
     * @param timestamp the timestamp of the login attempt
     */
    public void recordLoginHistory(String userName, String loginType, Timestamp timestamp) throws IOException {
        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        PrintWriter printer = new PrintWriter(fileWriter);
        printer.println(loginType + " login by " + userName + " at " + timestamp + " " + ZoneId.systemDefault().getDisplayName(TextStyle.SHORT_STANDALONE, userLocale));
        printer.close();
    }

    /**
     * Exits the application
     */
    @FXML
    void exitButtonClicked() {
        System.exit(0);
    }

    /**
     * Gets the username and password entered into the respective fields and then calls the login method from the UserQuery class to query the database.
     * If a user is returned, records the login as successful, loads the main menu, and then checks to see if the user has any upcoming appointments within 15 minutes.
     *
     * This is done by querying the database to get all appointments, then using a the .forEach() method with a lambda function as the predicate to compare each appointment's user's ID with the ID of the logged-in user.
     * If the IDS match, they are added to an observable list.
     *
     * This lambda function prevents a new query from needing to be written, ultimately reducing the lines of code and simplifying the program.
     *
     * Two more lambdas are used in this method, one as the predicate for the .anyMatch() method to check if there are any appointments in the userAppointments observable list that are upcoming within 15 minutes.
     * If there are, hasUpcomingAppointment is set to true, and the observable list is filtered using the same conditions with the third lambda as the predicate for the .filter() method.
     * The list of appointments are sorted by their start, and the first appointment is set as the upcoming appointment to alert the user to.
     *
     * The details of the upcoming appointment that was found is then displayed with an alert.
     * If no upcoming appointment is found, an alert message is displayed informing the user no appointments were found.
     * If the default system language is French, the alerts are translated.
     *
     * In the case that no user is returned from the login method the unsuccessful login is recorded and an error message is displayed.
     * The error message is also translated to French if the default system language is French.
     *
     * @param event the event generated by clicking the login button. Used to get the scene and load the main menu.
     */
    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException, SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String userName = userNameField.getText();
        String password = passwordField.getText();
        User user = UserQuery.login(userName, password);
        if (user != null) {
            recordLoginHistory(user.getUserName(), "Successful", timestamp);
            loadMainMenu(event);
            int userId = user.getUserId();

            ObservableList<Appointment> userAppointments = FXCollections.observableArrayList();

            AppointmentQuery.getAllAppointments().forEach(appointment -> {
                if (appointment.getUser().getUserId() == (userId)) {
                    userAppointments.add(appointment);
                }
            });

            LocalDateTime loginDateTime = timestamp.toLocalDateTime();
            boolean hasUpcomingAppointment = userAppointments.stream().anyMatch(appointment -> appointment.getStart().isBefore(loginDateTime.plusMinutes(15)) && appointment.getStart().isAfter(loginDateTime));
                if (hasUpcomingAppointment) {
                    Appointment upcomingAppointment = userAppointments.stream().filter(appointment ->
                            appointment.getStart().isBefore(loginDateTime.plusMinutes(15)) && appointment.getStart().isAfter(loginDateTime))
                            .min(Comparator.comparing(Appointment::getStart)).get();
                    if (userLocale.getLanguage().equals("fr")) {
                        ResourceBundle resourceBundle = ResourceBundle.getBundle("localization/Nat", userLocale);
                        alert(resourceBundle.getString("userId") + " #" + user.getUserId() + " " + user.getUserName() + " " + resourceBundle.getString("upcomingAppointmentMessage") + "\r\n"
                                + resourceBundle.getString("appointmentId") + "#: " + upcomingAppointment.getAppointmentId() + "\r\n"
                                + resourceBundle.getString("Title") + ": " + upcomingAppointment.getTitle() + "\r\n"
                                + resourceBundle.getString("Type") + ": " + upcomingAppointment.getType() + "\r\n"
                                + resourceBundle.getString("From") + ": " + upcomingAppointment.getStart() + "\r\n"
                                + resourceBundle.getString("To") + ": " + upcomingAppointment.getEnd());
                    } else {
                        alert("User ID #" + user.getUserId() + " " + user.getUserName() + " has an upcoming appointment within 15 minutes:\r\n"
                                + "Appointment ID #: " + upcomingAppointment.getAppointmentId() + "\r\n"
                                + "Title: " + upcomingAppointment.getTitle() + "\r\n"
                                + "Type: " + upcomingAppointment.getType() + "\r\n"
                                + "From: " + upcomingAppointment.getStart() + "\r\n"
                                + "To: " + upcomingAppointment.getEnd());
                    }
                } else {
                    if (userLocale.getLanguage().equals("fr")) {
                        ResourceBundle resourceBundle = ResourceBundle.getBundle("localization/Nat", userLocale);
                        alert(resourceBundle.getString("userId") + " #" + user.getUserId() + " " + user.getUserName() + " " + resourceBundle.getString("noUpcomingAppointmentMessage"));
                    } else {
                        alert("User ID #" + user.getUserId() + " " + user.getUserName() + " does not have any appointments within the next 15 minutes.");
                    }
                }
        } else {
            if (userLocale.getLanguage().equals("fr")) {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("localization/Nat", userLocale);
                ButtonType ok = new ButtonType(resourceBundle.getString("OK"), ButtonBar.ButtonData.OK_DONE);
                Alert error = new Alert(Alert.AlertType.ERROR, resourceBundle.getString("loginError"), ok);
                error.setTitle(resourceBundle.getString("error"));
                error.showAndWait();
            } else {
                error("Invalid credentials. Please try again.");
            }
            recordLoginHistory(userName, "Unsuccessful", timestamp);
        }
    }
}