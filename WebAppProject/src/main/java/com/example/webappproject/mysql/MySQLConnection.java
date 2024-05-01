package com.example.webappproject.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    // MySQL Database hosted on Digital Ocean service.
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://kme-do-user-14128544-0.c.db.ondigitalocean.com:25060/kme";

    // Database credentials - doadmin:AVNS_qujJSrFoEOCbFd6EXD- for local/academic testing anyway.
    private static final String USER = "doadmin";
    private static final String PASS = "AVNS_qujJSrFoEOCbFd6EXD-";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
