<%--
  Created by IntelliJ IDEA.
  User: notst
  Date: 5/2/2024
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <li><a href="teachers.jsp">Teachers</a></li>
            <li><a href="about.html">About</a></li>
            <li><a href="index.jsp">Home</a></li>
        </ul>
    </nav>
</header>
<div class="signin-form">
    <h2>Register</h2>
    <form action="api/signup" method="post" class="form-style">
        <div class="form-group">
            First Name: <input name="firstName" type="text" required>
        </div>
        <div class="form-group">
            Last Name: <input name="lastName" type="text" required>
        </div>
        <div class="form-group">
            Username: <input name="username" type="text" required>
        </div>
        <div class="form-group">
            Password: <input name="password" type="password" required>
        </div>
        <div class="form-group">
            Email: <input name="email" type="text" required>
        </div>
        <div class="form-group radio-group">
            Student <input type="radio" name="role" value="student">
            Teacher <input type="radio" name="role" value="teacher">
        </div>
        <button type="submit">Register</button>
    </form>
</div>
</body>
</html>


