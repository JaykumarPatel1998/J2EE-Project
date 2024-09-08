<%@page import="mvcdemo.model.User"%>
<%@page import="dao.UserDao"%>
<%@page import="dao.RegistrationDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        .user-card {
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            font-family: Arial, sans-serif;
        }
        .user-card h2 {
            font-size: 18px;
            margin-bottom: 10px;
        }
        .user-card p {
            margin: 5px 0;
        }
        .user-card strong {
            font-weight: bold;
        }
    </style>
</head>
<body>
	
    <header>
            <h1>
            <a class="header" href="adminPanel.html">ADMIN</a>
            </h1>
        </header>
        <h1>User Details</h1>
          <%-- user registrations by id fetch --%> 
        <%
      
        	String userID = request.getParameter("userId");
			RequestDispatcher rd = null;
			User user = UserDao.getUserById(Integer.parseInt(userID));
        %>
        
        <div class="user-card">
	        <h2>User Information</h2>
	        <p><strong>User ID:</strong> <%= user.getUserId() %></p>
	        <p><strong>Username:</strong> <%= user.getUsername() %></p>
	        <p><strong>Name:</strong> <%= user.getName() %></p>
	        <p><strong>Address:</strong> <%= user.getAddress() %></p>
	        <p><strong>Phone Number:</strong> <%= user.getPhoneNumber() %></p>
	        <p><strong>Email:</strong> <%= user.getEmail() %></p>
	        <p><strong>Is Admin:</strong> <%= user.getEmail() %></p>
    	</div>
       
    <table border="1">
        <tr>
            <th>Registration ID</th>
            <th>User ID</th>
            <th>Product ID</th>
            <th>Serial Number</th>
            <th>Purchase Date</th>
        </tr>
        
        <%
			List<mvcdemo.model.Registration> registrations = null;
			
			registrations = RegistrationDao.getRegistrationsByUserId(Integer.parseInt(userID)); // Fetch registrations
		
            
            for (mvcdemo.model.Registration registration : registrations) {
        %>
        <tr>
            <td><%= registration.getRegistrationId() %></td>
            <td><%= registration.getUserId() %></td>
            <td><%= registration.getProductId() %></td>
            <td><%= registration.getSerialNumber() %></td>
            <td><%= registration.getPurchaseDate() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>