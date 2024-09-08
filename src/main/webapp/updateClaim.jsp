<%@page import="dao.DB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Claim Status</title>
</head>
<body>
<%
    String approved = request.getParameter("approved");
    int claimId = Integer.parseInt(request.getParameter("claim_id"));
    String statusToUpdate = approved.equals("true") ? "approved" : "rejected";
 
    // Establishing a database connection
    Connection connection = DB.getConnection();
    PreparedStatement ps = null;
 
    try {
       // SQL update query
        String updateQuery = "UPDATE claim SET status = ? WHERE claim_id = ?";
        ps = connection.prepareStatement(updateQuery);
        ps.setString(1, statusToUpdate);
        ps.setInt(2, claimId);
        ps.executeUpdate();
 
        // Display a message indicating the status update
        out.println("<p>Status updated successfully!</p>");
    } catch (Exception e) {
        out.println("<p>Error updating status: " + e.getMessage() + "</p>");
    } finally {
        // Close resources
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
%>
</body>
</html>