package dao;

import com.example.model.Country;
import com.example.model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FirstLevelDivisionQuery {

    public static FirstLevelDivision retrieveDivision (int divisionId) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf((divisionId)));
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String division = rs.getString("Division");
            Country country = CountryQuery.retrieveCountry(rs.getInt("Country_ID"));
            return new FirstLevelDivision(divisionId, division, country);
        }
        return null;
    }
    public static ObservableList<FirstLevelDivision> retrieveDivisions (int countryId) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(countryId));
        ResultSet rs = ps.executeQuery();
        ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();
        while(rs.next()){
            divisions.add(retrieveDivision(rs.getInt("Division_ID")));
        }
        return divisions;
    }

}
