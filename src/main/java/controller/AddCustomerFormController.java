package controller;

import com.example.model.Country;
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
        String name = nameField.getText();
        String address = addressField.getText();
        String postalCode = postalCodeField.getText();
        String phoneNumber = phoneNumberField.getText();
        FirstLevelDivision division = firstLevelDivisionComboBox.getValue();
        CustomerQuery.addCustomer(name, address, postalCode, phoneNumber, division);
        changeScene(event, "/com/example/model/MainMenu.fxml");
    }

}
