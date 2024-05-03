<%--
  Created by IntelliJ IDEA.
  User: notst
  Date: 5/2/2024
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Math Education - Courses</title>
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
            <li><a href="teachers.jsp">Teachers</a></li>
            <li><a href="AssignmentList.jsp">Assignments</a></li>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="about.html">About</a></li>
            <li><a href="register.jsp">Registration</a></li>
        </ul>
    </nav>
</header>
<script>

    function fetchCourses() {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", `${pageContext.request.contextPath}/api/courses/`, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let xml = xhr.responseXML.childNodes[0];
                updateCoursesTable(xml);
            }
        };
        xhr.send();
    }

    function updateCoursesTable(courses) {
        let table = document.getElementById("styledTable")
        table.innerHTML = "";

        const headerRow = document.createElement("tr");
        const headers = ["Course Number", "Course Name", "Description" ];

        headers.forEach((header) => {
            const th = document.createElement("th");
            th.textContent = header;
            headerRow.appendChild(th);
        });

        table.appendChild(headerRow);
        courses.childNodes.forEach((courses) => {
            const row = document.createElement("tr");
            const cells = [
                courses.getElementsByTagName("course_number")[0].textContent,
                courses.getElementsByTagName("course_name")[0].textContent,
                courses.getElementsByTagName("description")[0].textContent,
            ];

            cells.forEach((cell) => {
                const td = document.createElement("td");
                td.textContent = cell;
                row.appendChild(td);
            });

            table.appendChild(row);
        });
    }
    document.addEventListener("DOMContentLoaded", fetchCourses);
</script>
<table id="styledTable"></table>
</body>
</html>