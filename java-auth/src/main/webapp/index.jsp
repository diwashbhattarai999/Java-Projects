<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Java Auth</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="h-screen flex justify-center items-center bg-gray-50">
    <div class="text-center p-10 bg-white shadow-md rounded-lg">
        <h1 class="text-3xl font-bold text-gray-800">Welcome to Java Auth</h1>
        <p class="text-gray-600 mt-2">Secure authentication system</p>
        <div class="mt-6 space-x-4">
            <a href="login.jsp" class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600">Login</a>
            <a href="register.jsp" class="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600">Register</a>
        </div>
    </div>
</body>
</html>