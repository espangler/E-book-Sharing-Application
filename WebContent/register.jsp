<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="style2.css" rel="stylesheet" type="text/css">
	<title>Sign Up</title>
</head>
<body>

<div id="container">
	<div id="header">
		<h1><span>
			Site name
           	</span>
		</h1>
	</div>
	<div id="navigation">
		<ul>
			<li><a href="homepage.jsp"><img src="images/nav01a.gif" BORDER="0"></a></li>
			<li><a href="books.jsp"><img src="images/nav02a.gif" BORDER="0"></a></li>
			<li><a href="login.jsp"><img src="images/nav03a.gif" BORDER="0"></a></li>
			<li><a href="register.jsp"><img src="images/nav04b.gif" BORDER="0"></a></li>
		</ul>
	</div>
	<div id="content">
		<h1>Sign Up to be a Member</h1>

		<% String error1 = (String)session.getAttribute("error1"); %>
		<% String error2 = (String)session.getAttribute("error2"); %>	
		<% String error3 = (String)session.getAttribute("error3"); %>
		
		<% if (error1 == "error" ) { %>
			<div id="errorText">
			All fields must be filled in to register.
			</div>
		<% } %>
		<% if (error2 == "error" ) { %>
			<div id="errorText">
			The username you selected is already in use.
			</div>
		<% } %>
		<% if (error3 == "error" ) { %>
			<div id="errorText">
			The email address you selected is already in use.
			</div>
		<% } %>
		
		<% session.removeAttribute("error1"); %>
		<% session.removeAttribute("error2"); %>
		<% session.removeAttribute("error3"); %>
		
		<FORM ACTION="/ebook-app/registration" METHOD="POST">
			<div id="text">Enter your email address, and the username and password that you would like:
			</div><BR>
			Email: <BR><INPUT TYPE="TEXT" NAME="email"><BR><BR>
			Username: <BR><INPUT TYPE="TEXT" NAME="username"><BR><BR>
			Password: <BR><INPUT TYPE="PASSWORD" NAME="password"><BR><BR>
			<INPUT TYPE="SUBMIT" VALUE="Submit">
		</FORM>
	</div>
	<div id="footer">
    	<h1>
		BookBar 2011
        </h1>
	</div><BR><BR>
</div>

</body>
</html>