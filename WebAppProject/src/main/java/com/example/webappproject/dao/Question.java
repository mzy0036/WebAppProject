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

import static com.example.webappproject.mysql.ResultSetToXMLConverter.XMLtoString;

public class Question {
    public static String getAllQuestions(boolean includeAnswers, boolean indicateCorrectAnswers) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT q.question_id, q.question_text, q.assignment_id" +
                    (includeAnswers ? ", a.answer_id, a.answer_text" + (indicateCorrectAnswers ? ", a.correct_answer" : "") : "") +
                    " FROM question q" +
                    (includeAnswers ? " JOIN answers a ON q.question_id = a.question_id" : "");
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String getQuestionById(int questionId, boolean includeAnswers, boolean indicateCorrectAnswers) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT q.question_id, q.question_text, q.assignment_id" +
                    (includeAnswers ? ", a.answer_id, a.answer_text" + (indicateCorrectAnswers ? ", a.correct_answer" : "") : "") +
                    " FROM question q" +
                    (includeAnswers ? " LEFT JOIN answers a ON q.question_id = a.question_id" : "") +
                    " WHERE q.question_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, questionId);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }

    public static String getAllQuestionByAssignmentId(int assignmentId, boolean includeAnswers, boolean indicateCorrectAnswers) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String sql = "SELECT q.question_id, q.question_text, q.assignment_id" +
                    (includeAnswers ? ", a.answer_id, a.answer_text" + (indicateCorrectAnswers ? ", a.correct_answer" : "") : "") +
                    " FROM question q" +
                    (includeAnswers ? " LEFT JOIN answers a ON q.question_id = a.question_id" : "") +
                    " WHERE q.assignment_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, assignmentId);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        }
    }


    public static String createQuestion(String questionText, int assignmentId) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String insertSql = "INSERT INTO question (question_text, assignment_id) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, questionText);
                pstmt.setInt(2, assignmentId);

                int result = pstmt.executeUpdate();

                // Create XML document to return
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();
                Element root = doc.createElement("CreateQuestionResult");
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
            Element root = doc.createElement("CreateQuestionResult");
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
