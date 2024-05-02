package com.example.webappproject.dao;

import com.example.webappproject.mysql.*;

import static com.example.webappproject.mysql.ResultSetToXMLConverter.XMLtoString;

import java.sql.*;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.*;


public class Message {
    public static final int generalID = -1;

    public static String GetGeneralMessages() throws SQLException, ParserConfigurationException {
        return GetMessagesFromChatroom(generalID);
    }

    public static String GetMessagesFromChatroom(int chatroomID) throws SQLException, ParserConfigurationException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String checkSql = "SELECT SenderID,RecieverID,content,TimeSent FROM messages WHERE `RecieverID` = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
                pstmt.setInt(1, chatroomID);
                return ResultSetToXMLConverter.executeQueryToXML(pstmt);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static String SendMessage(int senderID, int receiverID, String content) throws SQLException, ParserConfigurationException, ClassNotFoundException {
        try (Connection conn = MySQLConnection.getConnection()) {
            String insertSql = "INSERT INTO messages (SenderID, RecieverID, content, TimeSent) VALUES (?, ?, ?, ?)";
            java.util.Date date = new java.util.Date();
            Object currDate = new java.sql.Timestamp(date.getTime());
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setInt(1, senderID);
                pstmt.setInt(2, receiverID);
                pstmt.setString(3, content);
                pstmt.setObject(4, currDate);
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
            } catch (SQLException e) {
                // Handle SQL exceptions and return them in XML
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();
                Element root = doc.createElement("SendMessageResult");
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
}
