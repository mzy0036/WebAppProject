package com.example.webproject;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");

            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = new User(username, email, password);
            UserDatabase regUser = new UserDatabase(ConnectionDB.getConnection());
            if(regUser.saveUser(user)){
                response.sendRedirect("login.jsp");
            } else{
                String errorMessage="User Available";
                HttpSession regSession = request.getSession();
                regSession.setAttribute("RegError", errorMessage);
                response.sendRedirect("register.jsp");
            }
            out.println("</body");
            out.println("</html>");
        }
    }
}
