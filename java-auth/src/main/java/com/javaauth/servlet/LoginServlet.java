package com.javaauth.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.javaauth.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Received Email: " + email);
        System.out.println("Received Password: " + password);

        UserDAO userDAO = new UserDAO();
        
        if (userDAO.validateUser(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect("dashboard.jsp");
        } else {
            System.out.println("Invalid credentials.");
            response.sendRedirect("login.jsp?error=1");
        }
    }

}
