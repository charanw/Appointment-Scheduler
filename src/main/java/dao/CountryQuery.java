package dao;

import model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryQuery {
    /**Queries the database and gets the country with a given ID
     * @param countryId the ID of the country to be retrieved
     * @return Returns a new country object if a country is found, otherwise returns null
     * */
    public static Country getCountry(int countryId) throws SQLException {
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
    /**Queries the database and gets all countries
     * @return Returns an observable list of countries
     * */
    public static ObservableList<Country> getAllCountries() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        while(rs.next()){
            allCountries.add(getCountry(rs.getInt("Country_ID")));
        }
        return allCountries;
    }
}
