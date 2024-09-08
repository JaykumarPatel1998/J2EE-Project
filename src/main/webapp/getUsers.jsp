<!DOCTYPE html>
<%@page import="dao.UserDao"%>
<%@page import="mvcdemo.model.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <style>
        /* Reset some default styles */
        body, h1, ul, li {
            margin: 0;
            padding: 0;
        }

        /* Basic layout for the admin panel */
        .admin-panel {
            display: flex;
            flex-direction : column;
            min-height: 100vh;
            font-family: Arial, sans-serif;
        }

        /* Header styling */
        header, .header {
            background-color: #333;
            color: #fff;
            padding: 20px;
            width: 100%;
            text-decoration: none;
        }

        /* Sidebar (navigation) styling */
        nav {
            background-color: #ddd;
            padding: 20px;
        }

        /* Main content area styling */
        main {
            flex-grow: 1;
            padding: 20px;
        }

		.clicker {
		cursor: pointer;
		}
		
		.clicker:hover {
			background : hotpink;
		}
    </style>
</head>
<body>
    <%-- Check if the 'authorized' attribute is present --%>
    <c:choose>
        <c:when test="${not empty requestScope['authorized']}">
        <header>
            <h1>
            <a class="header" href="adminPanel.html">ADMIN</a>
            </h1>
        </header>
            <table border="1">
                <tr>
                    <th>User ID</th>
                    <th>Username</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Is Admin</th>
                    <th>get Details<th>
                </tr>
                <%
                    List<User> users = UserDao.getAllUsers();
                    for (User user : users) {
                %>
                
                	<tr>
	                    <td><%= user.getUserId() %></td>
	                    <td><%= user.getUsername() %></td>
	                    <td><%= user.getName() %></td>
	                    <td><%= user.getEmail() %></td>
	                    <td><%= user.getIsAdmin() == 1 ? "Yes" : "No" %></td>
	                    <td class="clicker"><a href="userDetails.jsp?userId=<%= user.getUserId() %>">get user</a></td>
                	</tr>
                <% } %>
            </table>
        </c:when>
        <c:otherwise>
            <p style="color: red;">Unauthorized access. Please log in.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
