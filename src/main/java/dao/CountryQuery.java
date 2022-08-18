package dao;

import com.example.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryQuery {
    public com.example.model.Country retrieveCountry (int countryId) throws SQLException {
        String sql = "SELECT * FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String countryName = rs.getString("Country");
            return new Country(countryId, countryName);
        }
        return null;
    }
}
