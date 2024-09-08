<!DOCTYPE html>
<%@page import="dao.ProductDao"%>
<%@page import="mvcdemo.model.Product"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Product for Claims</title>
</head>
<body>
<header>
            <h1>
            <a class="header" href="userPanel.jsp">USER</a>
            </h1>
        </header>
    <h1>Register Product for Claims</h1>
    <form action="RegistrationController" method="post">
        <label for="serial_number">Serial Number:</label>
        <input type="text" id="serial_number" name="serial_number" required><br>

        <label for="product">Select Product:</label>
        <select id="product" name="product">
            <option value="" disabled selected>Select a product</option>
            <%-- Assuming you have a ProductDao class to fetch product options --%>
            <%
                List<Product> products = ProductDao.getAllProducts(); // Fetch product options
                for (Product product : products) {
            %>
            <option value="<%= product.getProduct_id() %>"><%= product.getProduct_name() + ", Brand : " + product.getBrand() + ", Model : " + product.getModel() %></option>
            <% } %>
        </select><br>

        <input type="submit" value="Register">
    </form>
    <!-- Add any other content or functionality as needed -->
</body>
</html>
