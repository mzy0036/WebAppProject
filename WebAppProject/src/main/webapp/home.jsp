<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<a href="logout.jsp"> Logout </a>       <!-- Logout link -->
<br/>                                   <!-- new line -->
<%
    //check if session we created exists or not
    if(session.getAttribute("uname") != null) {
        //if session was created
        out.println("Hello, "+ session.getAttribute("uname"));
    }
    else
    {
        // passing variable "b" with URL
        // lets say "b" is for "user must login to go to home.jsp"
        response.sendRedirect("index.jsp?b");
    }
%>

</body>
</html>
