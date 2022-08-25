package dao;

import com.example.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ScheduleApplicationQuery {

    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> allUsers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            allUsers.add(ContactQuery.retrieveContact(rs.getInt("Contact_ID")));
        }
        return allUsers;
    }
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            allUsers.add(UserQuery.retrieveUser(rs.getInt("User_ID")));
        }
        return allUsers;
    }

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            allCustomers.add(CustomerQuery.retrieveCustomer(rs.getInt("Customer_ID")));
        }
        return allCustomers;
    }

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            allAppointments.add(AppointmentQuery.retrieveAppointment(rs.getInt("Appointment_ID")));
        }
        return allAppointments;
    }
    }
