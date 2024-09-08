<!DOCTYPE html>
<%@page import="dao.UserDao"%>
<%@page import="mvcdemo.model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>User Information</h1>
    <%
        String userIdParam = request.getParameter("user_id");
        if (userIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            User user = UserDao.getUserById(userId); // Fetch user details (replace with actual DAO call)
    %>
    <table>
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Name</th>
            <th>Email</th>
            <th>Is Admin</th>
        </tr>
        <tr>
            <td><%= user.getUserId() %></td>
            <td><%= user.getUsername() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getIsAdmin()==1 ? "Yes" : "No" %></td>
        </tr>
    </table>
    <% } else { %>
    <p style="color: red;">User ID not provided. Please specify a valid user ID.</p>
    <% } %>

    <!-- Add any other content or functionality as needed -->
</body>
</html>
