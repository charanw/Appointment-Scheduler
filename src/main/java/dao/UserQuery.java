package dao;

import model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserQuery {
    /**Queries the database and gets the user with a given ID
     * @param userId the ID of the user to retrieve
     * @return Returns a new user object if the user is found, otherwise returns null
     * */
    public static User getUser(int userId) throws SQLException {
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
    /**Queries the database to verify the provided username and password, and returns a new user object if the credentials are valid.
     * First gets all the user_names from the database and loops through them, comparing the provided username.
     * If a match is found, gets the password for the username in the database and compares it to the provided password.
     * If the passwords match, returns a new user object.
     * @param userName the username to check
     * @param password the password to check
     * @return Returns a new user object if a match is found, otherwise returns null.
     * */
    public static User login(String userName, String password) throws SQLException {
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
                            int userId = rs.getInt("User_ID");
                            return new User(userId, userName);
                        }
                    }
                }
            }
        }
        return null;
    }
    /**Queries the database and gets all users.
     * @return returns an observable list of users
     * */
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            allUsers.add(getUser(rs.getInt("User_ID")));
        }
        return allUsers;
    }
}
