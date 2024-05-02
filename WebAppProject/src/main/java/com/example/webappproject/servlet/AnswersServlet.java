package com.example.webappproject.servlet;

import com.example.webappproject.dao.Answer;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;

@WebServlet(name = "Answers", value = "/api/answers/*")
public class AnswersServlet extends HttpServlet {
    // We are not getting individual answers in this. See QuestionsServlet to get answers by questions.
    // There is no need to get a list of all answers.
    // This will serve only to CREATE answer via POST method for a question.
    // -ER
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        String returnXml = "<Error><Message>Invalid request</Message></Error>";
        response.getWriter().write(returnXml);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");

        String questionText = request.getParameter("answerText");
        int assignmentId = Integer.parseInt(request.getParameter("questionId"));
        boolean correctAnswer = Boolean.parseBoolean(request.getParameter("correctAnswer"));

        try {
            String result = Answer.createAnswer(questionText, assignmentId, correctAnswer);
            response.getWriter().write(result);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            response.getWriter().write("<Error><Message>" + e.getMessage() + "</Message></Error>");
        }
    }

    @Override
    public void destroy() {}
}