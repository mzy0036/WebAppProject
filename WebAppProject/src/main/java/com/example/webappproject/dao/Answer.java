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

import static com.example.webappproject.mysql.ResultSetToXMLConverter.XMLtoString;

public class Answer {
    public static String createAnswer(String questionText, int assignmentId, boolean correctAnswer) throws SQLException, ClassNotFoundException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String insertSql = "INSERT INTO answers (answer_text, question_id, correct_answer) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, questionText);
                pstmt.setInt(2, assignmentId);
                pstmt.setBoolean(3, correctAnswer);

                int result = pstmt.executeUpdate();

                // Create XML document to return
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();
                Element root = doc.createElement("CreateAnswerResult");
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
            Element root = doc.createElement("CreateAnswerResult");
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
