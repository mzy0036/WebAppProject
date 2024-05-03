<%@ page import="com.example.webappproject.dao.User" %>
<%@ page import="javax.xml.parsers.*, org.w3c.dom.*, java.io.*" %>
<%@ page import="org.xml.sax.InputSource" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Math Education - Teachers</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<main>
    <section class="banner">
        <h1>Teachers List</h1>
        <p>Meet Our Qualified Teachers</p>
    </section>
</main>
<header>
    <nav>
        <ul>
            <li><a href="courses.jsp">Courses</a></li>
            <li><a href="teachers.jsp">Teachers</a></li>
            <li><a href="AssignmentList.jsp">Assignments</a></li>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="about.html">About</a></li>
            <li><a href="register.jsp">Registration</a></li>
        </ul>
    </nav>
</header>
<article>
    <table id="styledTable">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
        </tr>
        <%
            String teachersXml = "";
            try {
                teachersXml = User.getAllTeachers();

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new InputSource(new StringReader(teachersXml)));
                NodeList nList = doc.getElementsByTagName("Row");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        String firstname = eElement.getElementsByTagName("firstname").item(0).getTextContent();
                        String lastname = eElement.getElementsByTagName("lastname").item(0).getTextContent();
                        String email = eElement.getElementsByTagName("email").item(0).getTextContent();
        %>
        <tr>
            <td><%= firstname %></td>
            <td><%= lastname %></td>
            <td><%= email %></td>
        </tr>
        <%
                    }
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='4'>Error retrieving teachers: " + e.getMessage() + "</td></tr>");
            }
        %>
    </table>
</article>
</body>
</html>
