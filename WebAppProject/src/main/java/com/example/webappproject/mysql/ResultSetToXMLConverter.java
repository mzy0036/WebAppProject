package com.example.webappproject.mysql;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.sql.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.sql.SQLException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

// Converts all SQL Queries to XML.
public class ResultSetToXMLConverter {

    public static String executeQueryToXML(PreparedStatement pstmt) throws SQLException, ParserConfigurationException {
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element results = doc.createElement("Results");
        doc.appendChild(results);

        while (rs.next()) {
            Element row = doc.createElement("Row");
            results.appendChild(row);
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);
                Object value = rs.getObject(i);
                if (columnName.equals("COUNT(*)")) {
                    columnName = "COUNT";
                }
                System.out.println(columnName + " = " + value);
                // Ensure valid XML tag names to avoid INVALID_CHARACTER_ERR for illegal XML character being specified
                String xmlTagName = columnName.replaceAll("\\s+|-|\\.", "_").replaceAll("^(\\d)", "_$1");

                Element node = doc.createElement(xmlTagName);
                node.appendChild(doc.createTextNode(value.toString()));
                row.appendChild(node);
            }
        }

        return XMLtoString(doc);
    }

    public static String XMLtoString(Document doc) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
