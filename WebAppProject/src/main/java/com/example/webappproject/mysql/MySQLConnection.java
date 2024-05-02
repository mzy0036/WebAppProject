package com.example.webappproject.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class MySQLConnection {
    // Add the .env to the C:\Program Files (x86)\Apache Software Foundation\Tomcat 10.1\bin directory.
    private static final String ENV_FILE = ".env";

    private static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(ENV_FILE)) {
            props.load(in);
        }
        return props;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Properties props = loadProperties();
            String driver = props.getProperty("JDBC_DRIVER");
            String url = props.getProperty("DB_URL");
            String user = props.getProperty("USER");
            String pass = props.getProperty("PASS");

            Class.forName(driver);
            return DriverManager.getConnection(url, user, pass);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from .env file at expected location: " + new java.io.File(ENV_FILE).getAbsolutePath(), e);
        }
    }
}
