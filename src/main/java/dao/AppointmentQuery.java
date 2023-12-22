package dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class AppointmentQuery {
    /**Queries the database and gets the appointment with a given ID
     * @param appointmentId the ID of the appointment to be retrieved
     * @return Returns a new appointment object if an appointment is found, otherwise returns null
     * */
    public static Appointment getAppointment(int appointmentId) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(appointmentId)));
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
             String title = rs.getString("Title");
             String description = rs.getString("Description");
             String location = rs.getString("Location");
             String type = rs.getString("Type");

             LocalDateTime start = rs.getTimestamp("Start").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
             LocalDateTime end = rs.getTimestamp("End").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

             int customerId = rs.getInt("Customer_ID");
             Customer customer = CustomerQuery.getCustomer(customerId);
             int userId = rs.getInt("User_ID");
             User user = UserQuery.getUser(userId);
             int contactId = rs.getInt("Contact_ID");
             Contact contact = ContactQuery.getContact(contactId);
             return new Appointment(appointmentId, title, description, location, type, start, end, customer, user, contact);
        }
        return null;
    }
    /**Queries the database and inserts a new appointment
     * @param title the appointment's title
     * @param description the appointment's description
     * @param location the appointment's location
     * @param type the appointment's type
     * @param start the appointment's start date and time
     * @param end the appointment's end date and time
     * @param customerId the appointment's customer ID
     * @param userId the appointment's user ID
     * @param contactId the appointment's contact ID
     * */
    public static void addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) Values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);

        ps.setTimestamp(5, (Timestamp.valueOf(start)));
        ps.setTimestamp(6, (Timestamp.valueOf(end)));

        ps.setString(7, (String.valueOf(customerId)));
        ps.setString(8, (String.valueOf(userId)));
        ps.setString(9, (String.valueOf(contactId)));
        ps.execute();
    }
    /**Queries the database and updates an existing appointment
     * @param title the appointment's title
     * @param description the appointment's description
     * @param location the appointment's location
     * @param type the appointment's type
     * @param start the appointment's start date and time
     * @param end the appointment's end date and time
     * @param customerId the appointment's customer ID
     * @param userId the appointment's user ID
     * @param contactId the appointment's contact ID
     * */
    public static void updateAppointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);

        ps.setTimestamp(5, (Timestamp.valueOf(start)));
        ps.setTimestamp(6, (Timestamp.valueOf(end)));

        ps.setString(7, (String.valueOf(customerId)));
        ps.setString(8, (String.valueOf(userId)));
        ps.setString(9, (String.valueOf(contactId)));
        ps.setString(10, (String.valueOf(appointmentId)));
        ps.execute();
    }
    /**Queries the database and deletes the appointment with a given appointment ID
     * @param appointmentId the ID of the appointment to be deleted
     * */
    public static void deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(appointmentId)));
        ps.execute();
    }
    /**Queries the database and gets all appointments for a given customer ID
     * @param customerId the ID of the customer appointments to retrieve
     * @return Returns an observable list of appointments
     * */
    public static ObservableList<Appointment> getCustomerAppointments(int customerId) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(customerId)));
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointment> customerAppointments = FXCollections.observableArrayList();
        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            customerAppointments.add(getAppointment(appointmentId));
        }
        return customerAppointments;
    }
    /**Queries the database and gets all appointments
     * @return Returns an observable list of appointments
     * */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            allAppointments.add(getAppointment(rs.getInt("Appointment_ID")));
        }
        return allAppointments;
    }
    /**Queries the database and gets each unique type
     * @return Returns an observable list of Strings representing appointment types
     * */
    public static ObservableList<String> getAllTypes() throws  SQLException {
        ObservableList<String> allTypes = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT Type FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            allTypes.add(rs.getString("Type"));
        }
        return allTypes;
    }
}
