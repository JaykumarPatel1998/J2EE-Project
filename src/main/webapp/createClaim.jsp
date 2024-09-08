<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Claim</title>
    <style>
        /* Inline CSS for styling */
        body {
            background-color: #f9f9f9;
            font-family: Arial, sans-serif;
            color: #333;
        }
 
        h1 {
            text-align: center;
            color: #333;
        }
 
        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
        }
 
        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
 
        input[type="text"],
        input[type="date"],
        textarea,
        input[type="submit"],
        input[type="reset"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
            color: #333;
        }
 
        input[type="submit"],
        input[type="reset"] {
            background-color: #333;
            color: #fff;
            cursor: pointer;
        }
 
        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #555;
        }
 
        .error-message {
            color: red;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <h1>Create Claim</h1>
    <div class="container">
        <form action="ClaimController" method="post">
            <!-- Input fields for claim details -->
            <label for="registration_id">Registration ID:</label>
            <input type="text" id="registration_id" name="registration_id" required><br>
 
            <label for="date_of_claim">Date of Claim:</label>
            <input type="date" id="date_of_claim" name="date_of_claim" required><br>
 
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" cols="50" required></textarea><br>
 
            <input type="submit" value="Create Claim">
            <input type="reset" value="Reset">
 
            <c:if test="${not empty errorMessage}">
                <p class="error-message">${errorMessage}</p>
            </c:if>
        </form>
    </div>
</body>
</html>