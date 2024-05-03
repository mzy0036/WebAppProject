package com.example.webappproject.servlet;

import com.example.webappproject.dao.User;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/api/login")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            String loginResult = User.validateLogin(username, password);
            // Assuming that validateLogin XML returns a count of users that match the credentials
            if (loginResult.contains("<userCount>0</userCount>")) {
                response.getWriter().write("<LoginResult><Status>Error</Status><Message>Invalid username or password.</Message></LoginResult>");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("isLoggedIn", true);
                response.getWriter().write("<LoginResult><Status>Success</Status><Message>Login successful.</Message></LoginResult>");
            }
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            response.getWriter().write("<LoginResult><Status>Error</Status><Message>" + e.getMessage() + "</Message></LoginResult>");
        }
    }

    public void destroy() {}
}
