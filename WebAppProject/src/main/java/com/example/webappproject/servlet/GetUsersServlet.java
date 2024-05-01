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
            } else if (pathInfo.equals("/teachers")) {
                // Get all teachers
                usersXml = User.getAllTeachers();
            } else if (pathInfo.equals("/students")) {
                // Get all students
                usersXml = User.getAllStudents();
            } else if (pathInfo.matches("/\\d+")) { // Regex to match a path that is exactly one or more digits
                // Get individual user by ID
                int userId = Integer.parseInt(pathInfo.substring(1)); // Extract user ID from path
                usersXml = User.getUserById(userId);
            } else {
                // Invalid path
                usersXml = "<Error><Message>Invalid request</Message></Error>";
            }
            response.getWriter().write(usersXml);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            response.getWriter().write("<Error><Message>" + e.getMessage() + "</Message></Error>");
        }
    }

    public void destroy() {}
}
