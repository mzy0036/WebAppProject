package com.example.webappproject.servlet;

import com.example.webappproject.dao.Question;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;

@WebServlet(name = "Questions", value = "/api/questions/*")
public class QuestionsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");

        String pathInfo = request.getPathInfo(); // Get the path info to determine the specific request
        try {
            String questionXml;
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all questions
                boolean includeAnswers = Boolean.parseBoolean(request.getParameter("includeAnswers"));
                boolean indicateCorrectAnswers = Boolean.parseBoolean(request.getParameter("indicateCorrectAnswers"));

                questionXml = Question.getAllQuestions(includeAnswers, indicateCorrectAnswers);
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2 && pathParts[1].matches("\\d+")) { // Regex to match a path that is exactly one or more digits
                    // Get individual course by ID
                    int questionId = Integer.parseInt(pathParts[1]); // Extract question ID from path
                    boolean includeAnswers = Boolean.parseBoolean(request.getParameter("includeAnswers"));
                    boolean indicateCorrectAnswers = Boolean.parseBoolean(request.getParameter("indicateCorrectAnswers"));

                    questionXml = Question.getQuestionById(questionId, includeAnswers, indicateCorrectAnswers);
                } else {
                    // Invalid path
                    questionXml = "<Error><Message>Invalid request</Message></Error>";
                }
            }
            response.getWriter().write(questionXml);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            response.getWriter().write("<Error><Message>" + e.getMessage() + "</Message></Error>");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");

        String questionText = request.getParameter("questionText");
        int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));

        try {
            String result = Question.createQuestion(questionText, assignmentId);
            response.getWriter().write(result);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            response.getWriter().write("<Error><Message>" + e.getMessage() + "</Message></Error>");
        }
    }

    @Override
    public void destroy() {}
}