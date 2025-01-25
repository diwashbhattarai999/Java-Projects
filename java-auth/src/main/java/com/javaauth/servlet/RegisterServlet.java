package com.javaauth.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.javaauth.dao.UserDAO;
import com.javaauth.model.User;

/**
 * Servlet implementation class RegisterSevlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // Server-side validation
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            response.sendRedirect("register.jsp?error=1&message=All fields are required.");
            return;
        }
        

        if (!password.equals(confirmPassword)) {
            response.sendRedirect("register.jsp?error=1&message=Passwords do not match.");
            return;
        }

        UserDAO userDAO = new UserDAO();
        
        // Check if the email is already registered
        if (userDAO.isEmailAlreadyRegistered(email)) {
            response.sendRedirect("register.jsp?error=1&message=Email is already registered.");
            return;
        }
        
        User newUser = new User(firstName, lastName, email, phoneNumber, password);

        // Register user
        if (userDAO.registerUser(newUser)) {
            response.sendRedirect("login.jsp?success=1");
        } else {
            response.sendRedirect("register.jsp?error=1&message=Registration failed, please try again.");
        }
    }

}
