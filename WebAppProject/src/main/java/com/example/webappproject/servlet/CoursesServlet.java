package com.example.webappproject.servlet;

import com.example.webappproject.dao.Course;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;

@WebServlet(name = "Courses", value = "/api/courses/*")
public class CoursesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");

        String pathInfo = request.getPathInfo(); // Get the path info to determine the specific request
        try {
            String coursesXml;
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all courses
                coursesXml = Course.getAllCourses();
            } else if (pathInfo.matches("/\\d+")) { // Regex to match a path that is exactly one or more digits
                // Get individual course by ID
                int courseId = Integer.parseInt(pathInfo.substring(1)); // Extract course ID from path
                coursesXml = Course.getCoursesById(courseId);
            } else if (pathInfo.startsWith("/teacher/")) {
                // Get courses for a specific teacher
                int teacherId = Integer.parseInt(pathInfo.substring("/teacher/".length()));
                coursesXml = Course.getCourseByTeacherId(teacherId);
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

        String courseNumber = request.getParameter("courseNumber");
        String courseName = request.getParameter("courseName");
        String description = request.getParameter("description");
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));

        try {
            String result = Course.createCourse(courseNumber, courseName, description, teacherId);
            response.getWriter().write(result);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            response.getWriter().write("<Error><Message>" + e.getMessage() + "</Message></Error>");
        }
    }

    @Override
    public void destroy() {
    }
}
