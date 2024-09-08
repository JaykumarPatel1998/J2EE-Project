<!DOCTYPE html>
<%@page import="mvcdemo.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProductDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>
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

    </style>
</head>
<body>
    <!-- Check if the 'user' attribute is present in the request scope -->
    <c:choose>
        <c:when test="${empty requestScope['user']}">
            <p style="color: red;">Error: User not logged in!</p>
        </c:when>
        <c:otherwise>
            <!-- Assuming you have a ProductDAO class to retrieve product records -->
            <%
                List<Product> products = ProductDao.getAllProducts();
            %>
            <header>
            <h1>
            <a class="header" href="adminPanel.html">ADMIN</a>
            </h1>
        </header>
            <table border="1">
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Model</th>
                    <th>Brand</th>
                </tr>
                <% for (Product product : products) { %>
                    <tr>
                        <td><%= product.getProduct_id() %></td>
                        <td><%= product.getProduct_name() %></td>
                        <td><%= product.getModel() %></td>
                        <td><%= product.getBrand() %></td>
                    </tr>
                <% } %>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
