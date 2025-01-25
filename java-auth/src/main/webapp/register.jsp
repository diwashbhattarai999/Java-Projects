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
    <title>Register - Java Auth</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        function toggleLoader(button) {
            const loader = button.querySelector('.loader');
            loader.classList.remove('hidden');
            button.disabled = true;
        }
    </script>
</head>
<body class="bg-gradient-to-r from-purple-500 to-blue-500 h-screen flex justify-center items-center">
    <div class="flex justify-center items-center bg-white shadow-md rounded-lg">
        <!-- Form -->
        <div class="w-1/2 h-full border-r border-neutral-100 p-6">
            <h3 class="text-3xl font-bold mb-8 text-gray-800">Sign Up</h3>

            <form action="registerUser" method="POST" onsubmit="toggleLoader(this.querySelector('button'));">
                <div class="mb-3">
                    <label class="block text-gray-700">First Name</label>
                    <input type="text" name="firstName" class="w-full p-2 border rounded-md" required>
                </div>
                <div class="mb-3">
                    <label class="block text-gray-700">Last Name</label>
                    <input type="text" name="lastName" class="w-full p-2 border rounded-md" required>
                </div>
                <div class="mb-3">
                    <label class="block text-gray-700">Email</label>
                    <input type="email" name="email" class="w-full p-2 border rounded-md" required>
                </div>
                <div class="mb-3">
                    <label class="block text-gray-700">Phone Number</label>
                    <input type="text" name="phoneNumber" class="w-full p-2 border rounded-md" required>
                </div>
                <div class="mb-3">
                    <label class="block text-gray-700">Password</label>
                    <input type="password" name="password" class="w-full p-2 border rounded-md" required>
                </div>
                
                <div class="mb-3">
                    <label class="block text-gray-700">Confirm Password</label>
                    <input type="password" name="confirmPassword" class="w-full p-2 border rounded-md" required>
                </div>

                <%-- Display error message from Servlet --%>
                <%
                    String error = request.getParameter("error");
                    String message = request.getParameter("message");
                    if (error != null && error.equals("1")) {
                %>
                    <div class="text-red-500 text-left bg-red-100 px-4 py-2 border border-red-300 rounded-md mb-3">
                        <p><%= message %></p>
                    </div>
                <% } %>
                
                <button type="submit" class="w-full bg-emerald-600 text-white p-2 rounded-md hover:bg-emerald-700 relative flex item-center justify-center gap-2">
                    <div class="loader hidden size-5 animate-spin rounded-full border-2 border-white/20 border-t-white"></div>
                    Register
                </button>

                <p class="text-center mt-3">Already have an account? <a href="login.jsp" class="text-emerald-600">Login</a></p>
            </form>
        </div>

        <!-- Right Image -->
        <div class="w-1/2 h-full hidden sm:block">
            <img src="assets/register.jpg" alt="Register Image" class="w-[40rem] h-full object-contain rounded-lg">
        </div>
    </div>
</body>
</html>
