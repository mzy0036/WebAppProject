package com.example.webappproject.servlet;

import com.example.webappproject.dao.User;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "getUsers", value = "/api/users/*")
public class GetUsersServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");

        String pathInfo = request.getPathInfo(); // Get the path info to determine the specific request
        try {
            String usersXml;
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all users
                usersXml = User.getAllUsers();
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2 && pathParts[1].matches("\\d+")) {
                    // Get individual user by ID
                    int userId = Integer.parseInt(pathParts[1]); // Extract user ID from path
                    usersXml = User.getUserById(userId);
                } else if (pathParts.length == 3 && pathParts[1].matches("\\d+") && "assignments".equals(pathParts[2])) {
                    // Get all assignments for a specific user
                    int userId = Integer.parseInt(pathParts[1]); // Extract user ID from path
                    boolean onlyActiveBoolean = Boolean.parseBoolean(request.getParameter("onlyActive"));
                    usersXml = Assignment.getAssignmentsByStudentId(userId, onlyActiveBoolean);
                } else {
                    // Invalid path
                    usersXml = "<Error><Message>Invalid request</Message></Error>";
                }
            }
            response.getWriter().write(usersXml);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            response.getWriter().write("<Error><Message>" + e.getMessage() + "</Message></Error>");
        }
    }

    public void destroy() {}
}
