package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class userQuery {

    public static boolean lookupUsername(String userName) throws SQLException {
        String sql = "SELECT User_Name FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (Objects.equals(userName, rs.getString("User_Name"))){
                return true;
            }
        }
        return false;
    };
    public static boolean checkPassword(String userName, String password) throws SQLException {
        String sql = "SELECT Password FROM users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
        if (Objects.equals(password, rs.getString("Password"))) {
            return true;
        }
        }
        return false;
    };
}
