<%--
  Created by IntelliJ IDEA.
  User: notst
  Date: 5/2/2024
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.webappproject.dao.User" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Math Education - Registration</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
<main>
    <section class="banner">
        <h1>Welcome to Online Math Education for K-12</h1>
        <p>Explore the World of Math</p>
    </section>
</main>
<header>
    <nav>
        <ul>
            <li><a href="courses.jsp">Courses</a></li>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="about.html">About</a></li>
            <li><a href="register.jsp">Registration</a></li>
        </ul>
    </nav>
</header>
<script>
    teachersXml = User.getAllTeachers();
    let table = document.getElementById("teacherTable");
    table.innerHTML = "";

    const headerRow = document.createElement("tr");
    const headers = ["First Name", "Last Name", "Email"];

    headers.forEach((header) => {
        const th = document.createElement("th");
        th.textContent = header;
        headerRow.appendChild(th);
    });

    table.appendChild(headerRow);
    teachersXml.childNodes.forEach((teachersXml) => {
        const row = document.createElement("tr");
        const cells = [
            teachersXml.getElementsByTagName("firstname")[0].textContent,
            teachersXml.getElementsByTagName("lastname")[0].textContent,
            teachersXml.getElementsByTagName("email")[0].textContent,
        ];

        cells.forEach((cell) => {
            const td = document.createElement("td");
            td.textContent = cell;
            row.appendChild(td);
        });

        table.appendChild(row);
    });
</script>
<table id="teacherTable"></table>
</body>
</html>