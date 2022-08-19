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
}
