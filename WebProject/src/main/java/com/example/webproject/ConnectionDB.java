package com.example.webproject;

import java.sql.*;

public class ConnectionDB {
    static String url = "jdbc:mysql://localhost:3306/LEAPDB";
    static String username = "root";
    static String password = "Ymj123456!";
    private static Connection con;


    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return con;
    }
}


