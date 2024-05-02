package com.example.webappproject.dao;

import com.example.webappproject.mysql.MySQLConnection;
import com.example.webappproject.mysql.ResultSetToXMLConverter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;


public class Assignment {
    public static String getAllAssignments(boolean onlyActive) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT assignment_id, name, description, course_id, student_id, due_date, open_date FROM assignment";
            if (onlyActive) {
                sql += " WHERE CURDATE() >= open_date AND CURDATE() <= due_date";
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }
    public static String getAssignmentById(int assignmentId) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT assignment_id, name, description, course_id, student_id, due_date, open_date FROM assignment where assignment_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, assignmentId);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String getAssignmentsByCourseId(int courseId, boolean onlyActive) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT assignment_id, name, description, course_id, student_id, due_date, open_date FROM assignment WHERE course_id = ?";
            if (onlyActive) {
                sql += " AND CURDATE() >= open_date AND CURDATE() <= due_date";
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, courseId);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String getAssignmentsByStudentId(int studentId, boolean onlyActive) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT assignment_id, name, description, course_id, student_id, due_date, open_date FROM assignment WHERE student_id = ?";
            if (onlyActive) {
                sql += " AND CURDATE() >= open_date AND CURDATE() <= due_date";
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, studentId);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String createAssignment(String name, String description, int courseId, int studentId, Date dueDate, Date openDate)
            throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String insertSql = "INSERT INTO assignment (name, description, course_id, student_id, due_date, open_date) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, description);
                pstmt.setInt(3, courseId);
                pstmt.setInt(4, studentId);
                pstmt.setDate(5, dueDate);
                pstmt.setDate(6, openDate);

                int result = pstmt.executeUpdate();

                // Create XML document to return
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();
                Element root = doc.createElement("CreateAssignmentResult");
                doc.appendChild(root);

                Element status = doc.createElement("Status");
                status.appendChild(doc.createTextNode(result > 0 ? "Success" : "Failure"));
                root.appendChild(status);

                return ResultSetToXMLConverter.XMLtoString(doc);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions and return them in XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("CreateAssignmentResult");
            doc.appendChild(root);

            Element status = doc.createElement("Status");
            status.appendChild(doc.createTextNode("Error"));
            root.appendChild(status);

            Element message = doc.createElement("Message");
            message.appendChild(doc.createTextNode(e.getMessage()));
            root.appendChild(message);

            return ResultSetToXMLConverter.XMLtoString(doc);
        }
    }

}

