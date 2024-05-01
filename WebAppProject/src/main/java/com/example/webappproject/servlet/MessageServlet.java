package com.example.webappproject.servlet;

import com.example.webappproject.dao.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "MessageServlet", value = "/MessageServlet")
public class MessageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        int senderID = Integer.parseInt(request.getParameter("senderID"));
        int receiverID = Integer.parseInt(request.getParameter("receiverID"));
        String content = request.getParameter("content");
        try {
            String generalMsg = Message.SendMessage(senderID,receiverID,content);
            response.getWriter().write(generalMsg);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

}
