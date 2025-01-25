<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% 
    if(session.getAttribute("email") == null) {
        response.sendRedirect("login.jsp");
    }

    // Get the email from the session and extract the part before '@'
    String email = (String) session.getAttribute("email");
    String username = email != null && email.contains("@") ? email.split("@")[0] : email;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard - Java Auth</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="h-screen bg-gray-100">

    <!-- Sidebar (Optional) -->
    <div class="flex">
        <div class="w-64 h-screen bg-gray-800 text-white py-6 px-2">
            <h3 class="text-2xl font-bold mb-6 px-4">Dashboard</h3>
            <ul>
                <li><a href="#" class="block py-2 px-4 rounded-md hover:bg-gray-700">Overview</a></li>
                <li><a href="#" class="block py-2 px-4 rounded-md hover:bg-gray-700">Settings</a></li>
                <li><a href="#" class="block py-2 px-4 rounded-md hover:bg-gray-700">Notifications</a></li>
                <li><a href="logout.jsp" class="block py-2 px-4 rounded-md hover:bg-gray-700">Logout</a></li>
            </ul>
        </div>

        <!-- Main Content -->
        <div class="flex-1 p-8">
            <!-- Header -->
            <div class="flex size-full items-center justify-center flex-col">
                <h2 class="text-3xl font-bold">Welcome, <%= username %>!</h2>
                <p class="mt-2 text-gray-600">This is your secure dashboard area.</p>
            </div>
        </div>
    </div>

</body>
</html>
