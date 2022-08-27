package dao;

import com.example.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryQuery {
    public static Country retrieveCountry (int countryId) throws SQLException {
        String sql = "SELECT * FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(countryId)));
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String countryName = rs.getString("Country");
            return new Country(countryId, countryName);
        }
        return null;
    }

    public static ObservableList<Country> retrieveAllCountries () throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        while(rs.next()){
            allCountries.add(retrieveCountry(rs.getInt("Country_ID")));
        }
        return allCountries;
    }
}
