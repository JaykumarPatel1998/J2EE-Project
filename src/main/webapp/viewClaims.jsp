<!DOCTYPE html>
<%@page import="mvcdemo.model.User"%>
<%@page import="java.util.Map"%>
<%@page import="mvcdemo.model.Authenticator"%>
<%@page import="mvcdemo.model.Claim"%>
<%@page import="dao.ClaimDao"%>
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
            flex-direction: column;
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
 
        /* Main content area styling */
        main {
            flex-grow: 1;
            padding: 20px;
        }
 
        .clicker {
            cursor: pointer;
        }
 
        .clicker:hover {
            background: hotpink;
        }
        
        tr {
        	min-height : 200px
        }

		td a {
			border : 1px solid black;
			text-decoration: none;
			color: black;
			min-width : wrap-content;
			padding: 0.5rem;
			background: teal;
			border-radius: 3px;
		}
		
		td a:hover {
			border : 1px solid black;
			text-decoration: none;
			color: black;
			min-width : wrap-content;
			padding: 0.5rem;
			background: hotpink;
			border-radius: 3px;
		}

        
    </style>
</head> 
<body>
			<%
	        Authenticator authenticator = new Authenticator();
	        Cookie[] cookies = request.getCookies();
			Map<String, String> cookiesMap = authenticator.parseCookie(cookies);
			User user = null;
			if(authenticator.checkSessionExists(cookiesMap)) {
				 user = authenticator.checkUserExistsAndGetUser(cookiesMap.get("ssid"));
			}
        %>
            <header>
                <h1>
                <%if(user.getIsAdmin() == 1) {%>
	                        <a class="header" href="adminPanel.html">ADMIN</a>
                        <%} else {%>
	                        <a class="header" href="userPanel.jsp">USER</a>
                        <%}%>
                    
                </h1>
            </header>
            <br><br>
            <table border="1" style="border-collapse: collapse; width: 100%;">
                <tr>
                    <th style="padding: 10px; background-color: #333; color: #fff;">Claim ID</th>
                    <th style="padding: 10px; background-color: #333; color: #fff;">Registration ID</th>
                    <th style="padding: 10px; background-color: #333; color: #fff;">Date of Claim</th>
                    <th style="padding: 10px; background-color: #333; color: #fff;">Description</th>
                    <th style="padding: 10px; background-color: #333; color: #fff;">Status</th>
                    <%if(user.getIsAdmin() == 1) {%>
                    <th style="padding: 10px; background-color: #333; color: #fff;">Decision</th>
                    <%} %>
                </tr>
                <%
                    List<Claim> claims = ClaimDao.getAllClaims();
                    for (Claim claim : claims) {
                %>
                    <tr>
                        <td style="padding: 10px;"><%= claim.getClaim_id() %></td>
                        <td style="padding: 10px;"><%= claim.getRegistration_id() %></td>
                        <td style="padding: 10px;"><%= claim.getDate_of_claim() %></td>
                        <td style="padding: 10px;"><%= claim.getDescription() %></td>
                        <td style="padding: 10px;"><%= claim.getStatus() %></td>
                        
                        <%if(user.getIsAdmin() == 1) {%>
                        <td style="padding: 10px;">
                        <a href="updateClaim.jsp?claim_id=<%= claim.getClaim_id() %>&approved=true">Approve</a>
                        <a href="updateClaim.jsp?claim_id=<%= claim.getClaim_id() %>&approved=false">Reject</a>
                         </td>
                        <%}%>
                       
                    </tr>
                <% } %>
            </table>
</body>
</html>