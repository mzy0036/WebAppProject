package com.example.webappproject.servlet;

import com.example.webappproject.dao.Answer;
import com.example.webappproject.dao.Answer.AnswerData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.stream.Collectors;

@WebServlet(name = "AnswersServlet", urlPatterns = {"/api/answers"})
public class AnswersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");

        try {
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            JSONObject json = new JSONObject(jsonData);
            ArrayList<Answer.AnswerData> answerList = new ArrayList<>();

            Iterator<String> keys = json.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                answerList.add(new AnswerData(json.get(key).toString(),Integer.parseInt( key)));
            }

            String result = Answer.createAnswers(answerList);
            response.getWriter().write(result);
        } catch (JSONException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON data");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
