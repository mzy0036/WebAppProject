<html>
<head><title></title></head>
<body>
<%
    /**
     * assume the user "admin" is administrator
     * the user "faculty" is faculty
     * the user "student" is a student
     * and they all use the same pasword: javaserverpages
     */
    String PASSWORD = "javaserverpages";

    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if(username.equals("admin") && password.equals("admin")) {%>
you are administrator.<br>
<a href="logon.html">Try again.</a>
<%}else if(username.equals("faculty") && password.equals(PASSWORD)){%>
you are faculty.<br>
<a href="logon.html">Try again.</a>
<%}else if(username.equals("student") && password.equals(PASSWORD)){%>
you are a student.<br>
<a href="logon.html">Try again.</a>
<%}else{%>
Logon failed.<br>
<a href="logon.html">Try again.</a>
<%}%>
</body>
</html>
