package dao;

import com.example.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public abstract class UserQuery {

    public static User retrieveUser(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, (String.valueOf(userId)));
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        String userName = rs.getString("User_Name");
        return new User(userId, userName);
        }
        return null;
    }
    public static boolean login(String userName, String password) throws SQLException {

        User user;

        String sql = "SELECT User_Name FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            if(userName.equals(rs.getString("User_Name"))){
                sql = "SELECT Password FROM users WHERE User_Name = ?";
                ps = JDBC.connection.prepareStatement(sql);
                ps.setString(1, userName);
                rs = ps.executeQuery();
                if(rs.next()) {
                    if (password.equals(rs.getString("Password"))) {
                        sql = "SELECT User_ID FROM users WHERE User_Name = ?";
                        ps = JDBC.connection.prepareStatement(sql);
                        ps.setString(1, userName);
                        rs = ps.executeQuery();
                        if(rs.next()) {
                            int userId = rs.getInt(1);
                            return true;
                        }
                    }
                }
        }
        }
        return false;
    };

    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            allUsers.add(retrieveUser(rs.getInt("User_ID")));
        }
        return allUsers;
    }
}
