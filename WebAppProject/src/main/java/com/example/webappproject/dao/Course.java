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
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.webappproject.mysql.ResultSetToXMLConverter.XMLtoString;
import static com.example.webappproject.mysql.ResultSetToXMLConverter.convertStringToXMLDocument;

public class Course {
    public static String getAllCourses() throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT course_id, course_number, course_name, description, teacher_id FROM course";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String getCoursesById(int courseId) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT course_id, course_number, course_name, description, teacher_id FROM course WHERE course_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, courseId);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String getCourseByTeacherId(int teacherId) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT course_id, course_number, course_name, description, teacher_id FROM course WHERE teacher_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, teacherId);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String getCoursesByStudentId(int studentId) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element root = doc.createElement("Courses");
        doc.appendChild(root);

        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT course_id FROM teacher_student_lookup WHERE student_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, studentId);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int courseId = rs.getInt("course_id");
                    String courseXml = getCoursesById(courseId); // Fetch fuller course content
                    Document courseDoc = convertStringToXMLDocument(courseXml);

                    // Assuming courseDoc is correctly formed and has a root element
                    assert courseDoc != null;
                    Element courseElement = (Element) courseDoc.getDocumentElement().cloneNode(true);
                    doc.adoptNode(courseElement);
                    root.appendChild(courseElement);
                }
            }
        }
        return XMLtoString(doc);
    }


    public static String createCourse(String courseNumber, String courseName, String description, int teacherId) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String insertSql = "INSERT INTO course (course_number, course_name, description, teacher_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, courseNumber);
                pstmt.setString(2, courseName);
                pstmt.setString(3, description);
                pstmt.setInt(4, teacherId);

                int result = pstmt.executeUpdate();

                // Create XML document to return
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();
                Element root = doc.createElement("CreateCourseResult");
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
            Element root = doc.createElement("CreateCourseResult");
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
}
