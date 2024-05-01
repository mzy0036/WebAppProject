package com.example.webappproject.dao;

import com.example.webappproject.mysql.*;
import static com.example.webappproject.mysql.ResultSetToXMLConverter.XMLtoString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class User {
    public static String isUsernameTaken(String username) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String checkSql = "SELECT COUNT(*) FROM user WHERE username = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
                pstmt.setString(1, username);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }


    public static String validateLogin(String username, String password) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String checkSql = "SELECT COUNT(*) AS userCount FROM user WHERE username = ? AND password = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String createUser(String firstName, String lastName, String username, String password, String email, String role) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String insertSql = "INSERT INTO user (firstname, lastname, username, password, email, role) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, username);
                pstmt.setString(4, password);
                pstmt.setString(5, email);
                pstmt.setString(6, role);
                int result = pstmt.executeUpdate();

                // Create XML document to return
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();
                Element root = doc.createElement("CreateUserResult");
                doc.appendChild(root);

                Element status = doc.createElement("Status");
                status.appendChild(doc.createTextNode(result > 0 ? "Success" : "Failure"));
                root.appendChild(status);

                return XMLtoString(doc);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions and return them in XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("CreateUserResult");
            doc.appendChild(root);

            Element status = doc.createElement("Status");
            status.appendChild(doc.createTextNode("Error"));
            root.appendChild(status);

            Element message = doc.createElement("Message");
            message.appendChild(doc.createTextNode(e.getMessage()));
            root.appendChild(message);

            return XMLtoString(doc);
        }
    }

    public static String getUserById(int userId) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT user_id, firstname, lastname, email, role FROM user WHERE user_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String getAllUsers() throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT user_id, firstname, lastname, email, role FROM user";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String getAllTeachers() throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT user_id, firstname, lastname, email, role FROM user WHERE role = 'teacher'";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String getAllStudents() throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT user_id, firstname, lastname, email, role FROM user WHERE role = 'student'";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }
}
