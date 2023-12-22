package controller;

import model.Country;
import model.FirstLevelDivision;
import dao.CountryQuery;
import dao.CustomerQuery;
import dao.FirstLevelDivisionQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddCustomerFormController extends Controller {

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
    /**Initialize the Add Customer Form Controller. Queries the database to get all countries and displays them in the country combo-box.*/
    public void initialize() throws SQLException {
        countryComboBox.setItems(CountryQuery.getAllCountries());
    }
    /**When the country combo-box is updated, gets the selected country and then queries the database to get that country's first level-divisions and displays them in the first-level divisions combo-box.*/
    @FXML
    void countryComboBoxUpdated() throws SQLException {
        firstLevelDivisionComboBox.setItems(FirstLevelDivisionQuery.getDivisionsByCountryId(countryComboBox.getSelectionModel().getSelectedItem().getCountryId()));
    }
    /**Closes the window without saving any changes.*/
    @FXML
    void cancelButtonClicked() {
        Stage window = (Stage) cancelButton.getScene().getWindow();
        window.close();
    }
    /**Saves a new customer to the database. Gets the data from the relevant fields and combo-boxes and makes sure they aren't empty.
     * If they are empty, an error message indicating which field or combo-box is shown.
     * Closes the window and returns to the main menu once the customer has been updated.
     * */
    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException, SQLException {
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
        CustomerQuery.addCustomer(name, address, postalCode, phoneNumber, division);
        Stage window = (Stage) saveButton.getScene().getWindow();
        window.close();
    }

}
