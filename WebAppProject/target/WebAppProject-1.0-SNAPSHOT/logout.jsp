<%
    //invalidate session
    if(session.getAttribute("uname") != null) {
        //if session was created
        session.invalidate();
        response.sendRedirect("index.jsp");
    }
    else
    {
        // passing variable "b" with URL
        // lets say "b" is for "user must login"
        response.sendRedirect("index.jsp?b");
    }
%>
