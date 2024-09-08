<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>user Panel</title>
    <style type="text/css">
    
body, h1, ul, li {
    margin: 0;
    padding: 0;
}

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

li {
	list-style-type: none;
	padding: 1rem;
	text-align: center;
	
}

li a {
	border : 1px solid black;
	text-decoration: none;
	color: black;
	min-width : wrap-content;
	padding: 0.5rem;
	background: teal;
	border-radius: 3px;
}

li a:hover {
	border : 1px solid black;
	text-decoration: none;
	color: black;
	min-width : wrap-content;
	padding: 0.5rem;
	background: hotpink;
	border-radius: 3px;
}


/* Main content area styling */
main {
    flex-grow: 1;
    padding: 20px;
}
    
    </style>
</head>
<body>
    <div class="admin-panel">
        <header>
            <h1>
            <a class="header" href="userPanel.jsp">USER</a>
            </h1>
        </header>
        <nav>
            <ul>
                <li><a href="getUserRegistrations.jsp">All Registrations</a></li>
                <li><a href="RegistrationController">Register Product</a></li>
                <li><a href="createClaim.jsp">Create claim</a></li>
                <li><a href="viewClaims.jsp">View Claims</a></li>
            </ul>
        </nav>
        <main>
            <!-- Your main content goes here -->
            <p>Welcome to the user panel!</p>
        </main>
    </div>
</body>
</html>
