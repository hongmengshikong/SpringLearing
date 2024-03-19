package cn.edu.xcu.jdbc.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static User findUserById(Long id) throws SQLException {
        Connection conn = DbHelper.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from t_user where id=" + id);
        if (rs.next()){
            User user = new User();
            user.setId(id);
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setUserNick(rs.getString("user_nick"));
            user.setAge(rs.getInt("age"));
            user.setEmail(rs.getString("email"));
            return user;
        }
        return null;
    }
    public static List<User> findAllUsers() throws SQLException {
        Connection conn = DbHelper.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from t_user");
        List<User> users= new ArrayList<User>();
        while (rs.next()){
            User user = new User();
            user.setId(rs.getLong(1));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setUserNick(rs.getString("user_nick"));
            user.setAge(rs.getInt("age"));
            user.setEmail(rs.getString("email"));
            users.add(user);
        }
        return users;
    }
}
