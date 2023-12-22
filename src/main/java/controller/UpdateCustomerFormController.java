package controller;

import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import dao.CountryQuery;
import dao.CustomerQuery;
import dao.FirstLevelDivisionQuery;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateCustomerFormController extends Controller {

    private Customer customer;

    @FXML
    private TextField addressField;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<Country> countryComboBox;

    @FXML
    private ComboBox<FirstLevelDivision> firstLevelDivisionComboBox;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField postalCodeField;

    @FXML
    private Button saveButton;
    /**This method takes a customer, gets the ID, name, address, postal code, phone number, country, and first-level division,
     * and displays the data in the relevant fields and combo-boxes so that the data can be updated.
     * @param customer the customer to be updated
     * */
    public void setCustomer(Customer customer){
        this.customer = customer;
        idField.setText(String.valueOf(customer.getCustomerId()));
        nameField.setText(customer.getCustomerName());
        addressField.setText(customer.getAddress());
        postalCodeField.setText(customer.getPostalCode());
        phoneNumberField.setText(customer.getCustomerPhone());
        countryComboBox.getSelectionModel().select(customer.getCountry());
        firstLevelDivisionComboBox.getSelectionModel().select(customer.getDivision());
    }
    /**Initializes the update customer form controller. Queries the database to get all countries, and displays them in the country combo-box.*/
    public void initialize() throws SQLException {
        countryComboBox.setItems(CountryQuery.getAllCountries());
    }
    /**When the country combo-box is updated, get the selected country and queries the database to get that countries first-level divisions and displays them in the first-level division combo-box. */
    @FXML
    void countryComboBoxUpdated() throws SQLException {
        firstLevelDivisionComboBox.setItems(FirstLevelDivisionQuery.getDivisionsByCountryId(countryComboBox.getSelectionModel().getSelectedItem().getCountryId()));
    }
    /**Closes the window without saving any changes*/
    @FXML
    void cancelButtonClicked() throws IOException {
        Stage window = (Stage) cancelButton.getScene().getWindow();
        window.close();
    }
    /**Saves the updated customer to the database. Gets the data from the relevant fields and combo-boxes and makes sure they aren't empty.
     * If they are empty, an error message indicating which field or combo-box is shown.
     * Closes the window and returns to the main menu once the customer has been updated.
     * */
    @FXML
    void saveButtonClicked() throws SQLException {
        int customerId = customer.getCustomerId();
        String name = nameField.getText();
        if (name.isEmpty()) {
            error("Name cannot be empty. Please enter a name and try again.");
            return;
        }
        String address = addressField.getText();
        if (address.isEmpty()) {
            error("Address cannot be empty. Please enter a address and try again.");
            return;
        }
        String postalCode = postalCodeField.getText();
        if (postalCode.isEmpty()) {
            error("Postal code cannot be empty. Please enter a postal code and try again.");
            return;
        }
        String phoneNumber = phoneNumberField.getText();
        if (phoneNumber.isEmpty()) {
            error("Phone number cannot be empty. Please enter a phone number and try again.");
            return;
        }
        FirstLevelDivision division = firstLevelDivisionComboBox.getValue();
        if (division == null) {
            error("First-level division cannot be empty. Please select a first-level division and try again.");
            return;
        }
        CustomerQuery.updateCustomer(customerId, name, address, postalCode, phoneNumber, division);
        Stage window = (Stage) saveButton.getScene().getWindow();
        window.close();
    }

}
