package dao;

import com.example.model.Appointment;
import com.example.model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactQuery {
    public static Contact retrieveContact (int contactId) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(contactId)));
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String contactName = rs.getString("Contact_Name");
            return new Contact(contactId, contactName);
        }
            return null;
        }
}
