package com.example.webproject;

import java.sql.*;

public class ConnectionDB {
    String url = "jdbc:mysql://localhost:3306/LEAPDB";
    String username = "root";
    String password = "Ymj123456!";
    Connection connection = null;
    static ConnectionDB instance = null;


    public ConnectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

//    public static synchronized ConnectionDB getInstance() {
//        if(instance == null) {
//            instance = new ConnectionDB();
//        }
//        return instance;
//    }
//
//    public User doLogin(String Email, String Password) throws SQLException {
//        User User = null;
//
//        // Statement
//        String qLogin = "SELECT UserName FROM General_usr WHERE email = '"+ Email +"' AND password = '"+ Password +"'";
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(qLogin);
//
//
//        if(resultSet.next()) {
//            String name = resultSet.getString("name");
//            User = new User(Email, name);
//        }
//        resultSet.close();
//        statement.close();
////        preparedStatement.close();
//        return User;
//
//    }

}

