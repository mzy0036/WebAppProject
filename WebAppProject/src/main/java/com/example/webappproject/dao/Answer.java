package com.example.webappproject.dao;

import com.example.webappproject.mysql.MySQLConnection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.webappproject.mysql.ResultSetToXMLConverter.XMLtoString;

public class Answer {

    public static class AnswerData {
        String questionText;
        int assignmentId;
        boolean correctAnswer;
        public AnswerData(String questionText, int assignmentId) {
            this.questionText = questionText;
            this.assignmentId = assignmentId;
            this.correctAnswer = false;
        }
    }

    public static String createAnswers(ArrayList<AnswerData> answers) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = MySQLConnection.getConnection();
            String insertSql = "INSERT INTO answers (answer_text, question_id, correct_answer) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(insertSql);

            for (AnswerData answer : answers) {
                pstmt.setString(1, answer.questionText);
                pstmt.setInt(2, answer.assignmentId);
                pstmt.setBoolean(3, answer.correctAnswer);
                pstmt.addBatch();
            }

            int[] results = pstmt.executeBatch();

            // Create XML document to return
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("CreateAnswerResults");
            doc.appendChild(root);

            for (int result : results) {
                Element status = doc.createElement("Status");
                status.appendChild(doc.createTextNode(result > 0 ? "Success" : "Failure"));
                root.appendChild(status);
            }

            return XMLtoString(doc);
        } catch (SQLException e) {
            // Handle SQL exceptions and return them in XML
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("CreateAnswerResults");
            doc.appendChild(root);

            Element status = doc.createElement("Status");
            status.appendChild(doc.createTextNode("Error"));
            root.appendChild(status);

            Element message = doc.createElement("Message");
            message.appendChild(doc.createTextNode(e.getMessage()));
            root.appendChild(message);

            return XMLtoString(doc);
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }
}
