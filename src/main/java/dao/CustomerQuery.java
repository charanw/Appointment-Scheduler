package dao;

import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerQuery {
    /**Queries the database and gets the customer with a given ID
     * @param customerId the ID of the customer to be retrieved
     * @return Returns a new customer object if a customer is found, otherwise returns null
     * */
    public static Customer getCustomer(int customerId) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(customerId)));
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phoneNumber = rs.getString("Phone");
            FirstLevelDivision division = FirstLevelDivisionQuery.getDivisionById(rs.getInt("Division_ID"));
            Country country = CountryQuery.getCountry(division.getCountry().getCountryId());
            return new Customer(customerId, customerName, phoneNumber, address, postalCode, division, country);
        }
        return null;
    }
    /**Queries the database and inserts a new customer
     * @param name the customer's name
     * @param address the customer's street address
     * @param postalCode the customer's postal code
     * @param phoneNumber the customer's phone number
     * @param division the customer's first level division
     * */
    public static void addCustomer(String name, String address, String postalCode, String phoneNumber, FirstLevelDivision division) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) Values (?, ?, ?, ?, ?) ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setString(5, (String.valueOf(division.getDivisionId())));
        ps.execute();
    }
    /**Queries the database and updates an existing customer
     * @param name the customer's name
     * @param address the customer's street address
     * @param postalCode the customer's postal code
     * @param phoneNumber the customer's phone number
     * @param division the customer's first level division
     * */
    public static void updateCustomer(int customerId, String name, String address, String postalCode, String phoneNumber, FirstLevelDivision division) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setString(5, String.valueOf(division.getDivisionId()));
        ps.setString(6, (String.valueOf(customerId)));
        ps.execute();
    }
    /**Queries the database and deletes the customer with a given customer ID
     * @param customerId the ID of the customer to be deleted
     * */
    public static void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(customerId)));
        ps.execute();
    }
    /**Queries the database and gets all customers
     * @return Returns an observable list of customers
     * */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            allCustomers.add(getCustomer(rs.getInt("Customer_ID")));
        }
        return allCustomers;
    }
}
