package controller;

import com.example.model.Country;
import com.example.model.Customer;
import com.example.model.FirstLevelDivision;
import dao.CountryQuery;
import dao.CustomerQuery;
import dao.FirstLevelDivisionQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ModifyCustomerFormController extends Controller {

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
    public void initialize() throws SQLException {
        countryComboBox.setItems(CountryQuery.retrieveAllCountries());
    }

    @FXML
    void countryComboBoxUpdated(ActionEvent event) throws SQLException {
        firstLevelDivisionComboBox.setItems(FirstLevelDivisionQuery.retrieveDivisions(countryComboBox.getSelectionModel().getSelectedItem().getCountryId()));
    }

    @FXML
    void cancelButtonClicked(ActionEvent event) throws IOException {
        changeScene(event, "/com/example/model/MainMenu.fxml");
    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws IOException, SQLException {
        int customerId = customer.getCustomerId();
        String name = nameField.getText();
        String address = addressField.getText();
        String postalCode = postalCodeField.getText();
        String phoneNumber = phoneNumberField.getText();
        FirstLevelDivision division = firstLevelDivisionComboBox.getSelectionModel().getSelectedItem();
        CustomerQuery.updateCustomer(customerId, name, address, postalCode, phoneNumber, division);
        changeScene(event, "/com/example/model/MainMenu.fxml");
    }

}
