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
    <h1>User Registration Form</h1>
    <div class="container">
    
	    <form action="register.jsp" method="post">
	        <!-- Input fields for user details -->
	        <label for="username">username:</label>
	        <input type="text" id="username" name="username" required><br>
	
	        <label for="password">password:</label>
	        <input type="password" id="password" name="password" required><br>
	        
	        <label for="name">name:</label>
	        <input type="text" id="name" name="name" required><br>
	        
	        <label for="address">address:</label>
	        <input type="text" id="address" name="address"><br>
	        
	        <label for="email">email:</label>
	        <input type="email" id="email" name="email" required><br>
	        
	        <label for="phone">phone:</label>
	        <input type="text" id="phone" name="phone"><br>
	        
	        <label for="admin">isAdmin:</label>
	        <input type="radio" id="admin" name="admin" value="true">Yes<br>
	        <input type="radio" id="admin" name="admin" value="false" checked>No<br>
	
	        
	
	        <input type="submit" value="Sign up">
	        <input type="reset" value="Reset">
	    </form>
	    <a href="login.jsp">Sign in</a>
    </div>

    <!-- Database logic using JSTL -->
    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://insurancedb.cjyslqasspqj.ca-central-1.rds.amazonaws.com:3306/j2ee_project"
        user="admin" password="jaykumar" />

    <!-- Check if form was submitted -->
    <c:if test="${param.username!=null && param.password!=null && param.name!=null && param.email!=null && param.admin!= null }">
        <sql:update dataSource="${db}">
            INSERT INTO user (username, password, name, address, phone_number, email, is_admin) VALUES (?, ?, ?, ?, ?, ?, ?)
            <sql:param value="${param.username}" />
            <sql:param value="${param.password}" />
            <sql:param value="${param.name}" />
            <sql:param value="${param.address}" />
            <sql:param value="${param.phone}" />
            <sql:param value="${param.email}" />
            <sql:param value="${param.admin == \"true\"? 1 : 0 }" />
        </sql:update>
        <p>User registered successfully!</p>
    </c:if>

    <!-- Display error message if fields are empty -->
    <c:if test="${param.username==null || param.password==null || param.name==null || param.email==null || param.admin== null}">
        <p>Please fill in all required fields.</p>
    </c:if>

</body>
</html>
