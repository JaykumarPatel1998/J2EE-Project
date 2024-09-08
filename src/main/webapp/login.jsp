<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
     <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>User login</h1>
    <div class="container">
	    <form action="LoginController" method="post">
	        <label for="username">username:</label>
	        <input type="text" id="username" name="username" required><br>
	
	        <label for="password">password:</label>
	        <input type="password" id="password" name="password" required><br>
	
	        <input type="submit" value="Sign in">
	        <input type="reset" value="Reset">
	    </form>
	</div>
	<a href="register.jsp">Register here</a>
</body>
</html>
