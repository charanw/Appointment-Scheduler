package dao;

import com.example.model.Country;
import com.example.model.Customer;
import com.example.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            return new Customer(customerId, customerName, phoneNumber, address, postalCode, division);
        }
        return null;
    }
}
