<%--
  Created by IntelliJ IDEA.
  User: notst
  Date: 5/1/2024
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <form action="LoginServlet" method="post">
        Username: <input name="username" type="text" required> <br/>
        Password: <input name="password" type="password" required> <br/>
        <input type="submit" name="Login">
    </form>
</body>
</html>
