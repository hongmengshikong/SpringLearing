package cn.edu.xcu.jdbc.entity;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbHelper {
    private static Connection conn;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/mybatis";
            String username="root";
            String password="123456";
            conn= DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return conn;
    }
}
