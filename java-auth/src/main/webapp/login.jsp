<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% 
    // Check if the user is already logged in by verifying if the email attribute exists in the session
    if(session.getAttribute("email") != null) {
        response.sendRedirect("dashboard.jsp"); // Redirect to the dashboard if logged in
        return; // Ensure that the rest of the code doesn't execute after redirection
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login - Java Auth</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-r from-purple-500 to-blue-500 h-screen flex justify-center items-center">

	<div class="flex justify-center items-center bg-white shadow-md rounded-lg">
        <!-- Left Image -->
          <div class="w-1/2 h-full hidden sm:block border-r border-neutral-100">
            <img src="assets/login.jpg" alt="Login Image" class="w-[40rem] h-full object-contain rounded-lg">
        </div>

        <!-- Form -->
        <div class="w-1/2 h-full p-6">
            <h3 class="text-3xl font-bold mb-8 text-gray-800">Sign In</h3>

            <form action="loginUser" method="POST">
                <div class="mb-3">
                    <label class="block text-gray-700">Email</label>
                    <input type="email" name="email" class="w-full p-2 border rounded-md" required>
                </div>
                <div class="mb-3">
                    <label class="block text-gray-700">Password</label>
                    <input type="password" name="password" class="w-full p-2 border rounded-md" required>
                </div>

                <%-- Check if there's an error parameter in the URL --%>
                <%
                    String error = request.getParameter("error");
                    if (error != null && error.equals("1")) {
                %>
                    <div class="text-red-500 text-left bg-red-100 px-4 py-2 border border-red-300 rounded-md mb-3">
                        <p>Invalid credentials, please try again.</p>
                    </div>
                <% } %>
                
                <button type="submit" class="w-full bg-emerald-600 text-white p-2 rounded-md hover:bg-emerald-700 relative flex item-center justify-center gap-2">
                    <div class="loader hidden size-5 animate-spin rounded-full border-2 border-white/20 border-t-white"></div>
                	Login
                </button>

                <p class="text-center mt-3">Don't have an account? <a href="register.jsp" class="text-emerald-600">Register</a></p>
            </form>
        </div>
    </div>

</body>
</html>
