package dao;

import model.Country;
import model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FirstLevelDivisionQuery {
    /**Queries the database and gets the first-level division with a given division ID
     * @param divisionId the ID of the first-level division to be retrieved
     * @return Returns a new first-level division object if a division is found, otherwise returns null
     * */
    public static FirstLevelDivision getDivisionById(int divisionId) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf((divisionId)));
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String division = rs.getString("Division");
            Country country = CountryQuery.getCountry(rs.getInt("Country_ID"));
            return new FirstLevelDivision(divisionId, division, country);
        }
        return null;
    }
    /**Queries the database and gets the divisions for a given country ID
     * @param countryId the country ID of the divisions to be retrieved
     * @return Returns an observable list of first-level divisions
     * */
    public static ObservableList<FirstLevelDivision> getDivisionsByCountryId(int countryId) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(countryId));
        ResultSet rs = ps.executeQuery();
        ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();
        while(rs.next()){
            divisions.add(getDivisionById(rs.getInt("Division_ID")));
        }
        return divisions;
    }

}
