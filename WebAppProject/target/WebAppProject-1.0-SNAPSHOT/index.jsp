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
    <title>Online Math Education - Home</title>
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
            <li><a href="about.html">About</a></li>
            <li><a href="register.jsp">Registration</a></li>
        </ul>
    </nav>
</header>
<div class="signin-form">
    <h2>Sign In</h2>
    <form id="loginForm" action="/api/login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button id="loginButton" type="submit">Sign In</button>
    </form>
</div>
<script>
    document.getElementById("loginForm").addEventListener("submit", function(event) {
        // Prevent the default form submission
        event.preventDefault();

        // Disable the submit button to prevent multiple submissions
        document.getElementById("loginButton").disabled = true;

        // Get form data
        var formData = new URLSearchParams(new FormData(document.getElementById("loginForm")));

        // Send form data asynchronously using AJAX
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "api/login");
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Parse XML response
                    var xmlDoc = xhr.responseXML;
                    var status = xmlDoc.getElementsByTagName("Status")[0].textContent;

                    // Handle response based on status
                    if (status === "Success") {
                        // Redirect user to a different page upon successful login
                        alert("Success!");
                    } else {
                        // Handle invalid login
                        alert("Invalid username or password.");
                        // Enable the submit button
                        document.getElementById("loginButton").disabled = false;
                    }
                } else {
                    // Handle AJAX error
                    alert("Error occurred. Please try again later.");
                    // Enable the submit button
                    document.getElementById("loginButton").disabled = false;
                }
            }
        };
        xhr.send(formData);
    });
</script>
</body>
</html>