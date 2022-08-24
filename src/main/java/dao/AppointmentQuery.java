package dao;

import com.example.model.Appointment;
import com.example.model.Customer;
import com.example.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AppointmentQuery {

    public static Appointment retrieveAppointment (int appointmentId) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(appointmentId)));
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
             String title = rs.getString("Title");
             String description = rs.getString("Description");
             String location = rs.getString("Location");;
             String type = rs.getString("Type");
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

             LocalDateTime start = LocalDateTime.parse(rs.getString("Start"),formatter);
             LocalDateTime end = LocalDateTime.parse(rs.getString("End"),formatter);

             int customerId = rs.getInt("Customer_ID");
             int userId = rs.getInt("User_ID");
             int contactId = rs.getInt("User_ID");
             return new Appointment(appointmentId, title, description, location, type, start, end, customerId, userId, contactId);
        }
        return null;
    }

    public static void addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO Appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) Values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ps.setString(5, (start.format(formatter)));
        ps.setString(6, (end.format(formatter)));

        ps.setString(7, (String.valueOf(customerId)));
        ps.setString(8, (String.valueOf(userId)));
        ps.setString(9, (String.valueOf(contactId)));
        ps.execute();
    }
    public static void deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(appointmentId)));
        ps.execute();
    }
}
