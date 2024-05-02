package com.example.webappproject.servlet;

import com.example.webappproject.dao.Assignment;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import javax.xml.parsers.ParserConfigurationException;
import java.text.SimpleDateFormat;

@WebServlet(name = "Assignments", value = "/api/assignments/*")
public class AssignmentsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        boolean onlyActiveBoolean = Boolean.parseBoolean(request.getParameter("onlyActive"));

        String pathInfo = request.getPathInfo(); // Get the path info to determine the specific request
        try {
            String coursesXml;
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all assignments
                coursesXml = Assignment.getAllAssignments(onlyActiveBoolean);
            } else if (pathInfo.matches("/\\d+")) { // Regex to match a path that is exactly one or more digits
                // Get individual assignment by ID
                int assignmentId = Integer.parseInt(pathInfo.substring(1)); // Extract course ID from path
                coursesXml = Assignment.getAssignmentById(assignmentId);
            } else {
                // Invalid path
                coursesXml = "<Error><Message>Invalid request</Message></Error>";
            }
            response.getWriter().write(coursesXml);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            response.getWriter().write("<Error><Message>" + e.getMessage() + "</Message></Error>");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");

        String assignmentName = request.getParameter("assignmentName");
        String assignmentDescription = request.getParameter("assignmentDescription");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String dueDate = request.getParameter("dueDate");
        String openDate = request.getParameter("openDate");

        try {
            // Sanitize and convert the date strings to SQL Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date sqlDueDate = new Date(dateFormat.parse(dueDate).getTime());
            Date sqlOpenDate = new Date(dateFormat.parse(openDate).getTime());

            String result = Assignment.createAssignment(assignmentName, assignmentDescription, courseId, studentId, sqlDueDate, sqlOpenDate);
            response.getWriter().write(result);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            response.getWriter().write("<Error><Message>" + e.getMessage() + "</Message></Error>");
        } catch (java.text.ParseException e) {
            // Handle the case where the date format is incorrect
            response.getWriter().write("<Error><Message>Invalid date format. Please use YYYY-MM-DD.</Message></Error>");
        }
    }


    @Override
    public void destroy() {}
}
