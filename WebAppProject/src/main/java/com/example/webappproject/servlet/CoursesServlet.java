package com.example.webappproject.servlet;

import com.example.webappproject.dao.Assignment;
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
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2 && pathParts[1].matches("\\d+")) { // Regex to match a path that is exactly one or more digits
                    // Get individual course by ID
                    int courseId = Integer.parseInt(pathParts[1]); // Extract course ID from path
                    coursesXml = Course.getCoursesById(courseId);
                } else if (pathParts.length == 3 && pathParts[1].matches("\\d+") && "assignments".equals(pathParts[2])) {
                    // Get all assignments for a course
                    int courseId = Integer.parseInt(pathParts[1]); // Extract course ID from path
                    coursesXml = Assignment.getAssignmentsByCourseId(courseId, false); // Adjust this method if it requires more parameters
                } else if (pathParts.length == 3 && "teacher".equals(pathParts[1])) {
                    // Get courses for a specific teacher
                    int teacherId = Integer.parseInt(pathParts[2]);
                    coursesXml = Course.getCourseByTeacherId(teacherId);
                } else if (pathParts.length == 3 && "student".equals(pathParts[1])) {
                    // Get courses for a specific student
                    int studentId = Integer.parseInt(pathParts[2]);
                    coursesXml = Course.getCoursesByStudentId(studentId); // You'll need to implement this if it's required
                } else {
                    // Invalid path
                    coursesXml = "<Error><Message>Invalid request</Message></Error>";
                }
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
