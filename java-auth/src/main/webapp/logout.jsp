<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% 
    // Invalidate the session to log the user out
    session.invalidate();
    response.sendRedirect("login.jsp");  // Redirect to login page after logging out
%>
