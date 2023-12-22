package controller;

import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import dao.CountryQuery;
import dao.CustomerQuery;
import dao.FirstLevelDivisionQuery;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CustomersByLocationReportController extends Controller{


    @FXML
    private Button clearButton;

    @FXML
    private Button mainMenuButton;

    @FXML
    private ComboBox<Country> countryComboBox;

    @FXML
    private Label totalLabel;

    @FXML
    private ComboBox<FirstLevelDivision> firstLevelDivisionComboBox;

    @FXML
    private Button runReportButton;
    /**Initializes the customers by location report controller. Queries the database to get all countries and displays them in the country combo-box.*/
    public void initialize() throws SQLException {
        countryComboBox.setItems(CountryQuery.getAllCountries());
    }
    /**When the country combo-box is updated, gets the selected country and queries the database to get all of the countries first-level divisions and displays them in the first-level divisions combo-box.*/
    @FXML
    void countryComboBoxUpdated() throws SQLException {
        Country country = countryComboBox.getSelectionModel().getSelectedItem();
        if (country != null) {
            int countryId = country.getCountryId();
            firstLevelDivisionComboBox.setItems(FirstLevelDivisionQuery.getDivisionsByCountryId(countryId));
        }
    }
    /**Clears the country and first-level division combo-boxes and resets the total label.*/
    @FXML
    void clearButtonClicked() {
        countryComboBox.getSelectionModel().clearSelection();
        firstLevelDivisionComboBox.getSelectionModel().clearSelection();
        totalLabel.setText("Total Customers: ");
    }
    /**Gets the selected first-level division and queries the database to get all customers. Then filters and counts the customers whose division matches the selected division.
     * Uses a lambda function as the predicate of the .stream().filter() method to simplify filtering the observable list of customers.*/
    @FXML
    void runReportButtonClicked() throws SQLException {
        FirstLevelDivision firstLevelDivision = firstLevelDivisionComboBox.getSelectionModel().getSelectedItem();
        ObservableList<Customer> allCustomers = CustomerQuery.getAllCustomers();
        long customerCount = allCustomers.stream().filter(customer -> customer.getDivision().getDivision().equals(firstLevelDivision.getDivision())).count();
        totalLabel.setText("Total Customers: " + customerCount);
    }
    /**Closes the window and returns to the main menu.*/
    @FXML
    void mainMenuButtonClicked() {
        Stage window = (Stage) mainMenuButton.getScene().getWindow();
        window.close();
    }
}
