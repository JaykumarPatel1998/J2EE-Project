<!DOCTYPE html>
<%@page import="mvcdemo.model.User"%>
<%@page import="java.util.Map"%>
<%@page import="mvcdemo.model.Authenticator"%>
<%@page import="dao.RegistrationDao"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Registrations</title>
</head>
<body>
    <h1>All Registrations</h1>
    <header>
            <h1>
            <a class="header" href="userPanel.jsp">USER</a>
            </h1>
        </header>
    <table border="1">
        <tr>
            <th>Registration ID</th>
            <th>User ID</th>
            <th>Product ID</th>
            <th>Serial Number</th>
            <th>Purchase Date</th>
            <th>Set Claim</th>
        </tr>
        <%-- Assuming you have a RegistrationDao class to fetch registrations by user ID --%>
        <%
	        Authenticator authenticator = new Authenticator();
	        Cookie[] cookies = request.getCookies();
			Map<String, String> cookiesMap = authenticator.parseCookie(cookies);
			RequestDispatcher rd = null;
			List<mvcdemo.model.Registration> registrations = null;
			
			if(authenticator.checkSessionExists(cookiesMap)) {
				User user = authenticator.checkUserExistsAndGetUser(cookiesMap.get("ssid"));
				rd = request.getRequestDispatcher("/success.jsp");
				request.setAttribute("user", user);
				registrations = RegistrationDao.getRegistrationsByUserId(user.getUserId()); // Fetch registrations
			}
		
            
            for (mvcdemo.model.Registration registration : registrations) {
        %>
        <tr>
            <td><%= registration.getRegistrationId() %></td>
            <td><%= registration.getUserId() %></td>
            <td><%= registration.getProductId() %></td>
            <td><%= registration.getSerialNumber() %></td>
            <td><%= registration.getPurchaseDate() %></td>
            <td><a class="header" href="createClaim.jsp">settle claim</a></td>
        </tr>
        <% } %>
    </table>
    <!-- Add any other content or functionality as needed -->
</body>
</html>
