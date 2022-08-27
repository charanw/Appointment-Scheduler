package dao;

import com.example.model.Contact;
import com.example.model.Country;
import com.example.model.Customer;
import com.example.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class CustomerQuery {
    public static Customer retrieveCustomer (int customerId) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(customerId)));
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phoneNumber = rs.getString("Phone");
            FirstLevelDivision division = FirstLevelDivisionQuery.retrieveDivision(rs.getInt("Division_ID"));
            Country country = CountryQuery.retrieveCountry(division.getCountry().getCountryId());
            return new Customer(customerId, customerName, phoneNumber, address, postalCode, division, country);
        }
        return null;
    }

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

    public static void updateAppointment(int customerId, String name, String address, String postalCode, String phoneNumber, FirstLevelDivision division) throws SQLException {
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
    public static void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(customerId)));
        ps.execute();
    }
}
