package com.example.webappproject.servlet;

import com.example.webappproject.dao.User;
import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.xml.parsers.ParserConfigurationException;
import java.sql.SQLException;

@WebServlet(name = "signup", value = "/api/signup")
public class SignupServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        try {
            String usernameCheckResult = User.isUsernameTaken(username);
            System.out.println(usernameCheckResult);
            if (usernameCheckResult.contains("<COUNT>0</COUNT>")) {
                String createUserResult = User.createUser(firstName, lastName, username, password, email, role);
                response.getWriter().write(createUserResult);
            } else {
                response.getWriter().write("<CreateUserResult><Status>Error</Status><Message>Username is already taken.</Message></CreateUserResult>");
            }
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            response.getWriter().write("<CreateUserResult><Status>Error</Status><Message>" + e.getMessage() + "</Message></CreateUserResult>");
        }
    }

    public void destroy() {}
}
