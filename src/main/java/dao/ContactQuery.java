package dao;

import model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactQuery {
    /**Queries the database and gets the contact with a given ID
    * @param contactId the ID of the contact to be retrieved
    * @return Returns a new contact object if a contact is found, otherwise returns null
    * */
    public static Contact getContact(int contactId) throws SQLException {
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
    /**Queries the database and gets all contacts
     * @return Returns an observable list of contacts
     * */
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            allContacts.add(getContact(rs.getInt("Contact_ID")));
        }
        return allContacts;
    }
}
