package dao;

import com.example.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserQuery {

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
}
