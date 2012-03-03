<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="style2.css" rel="stylesheet" type="text/css">
	<title>Login</title>
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
			<li><a href="login.jsp"><img src="images/nav03b.gif" BORDER="0"></a></li>
			<li><a href="register.jsp"><img src="images/nav04a.gif" BORDER="0"></a></li>
		</ul>
	</div>
	<div id="content">
		<h1>Login</h1>

		<% String error = (String)session.getAttribute("error"); %>
		<% if (session.getAttribute("error") == "error" ) { %>
			<div id="errorText">
			No match found for username and/or password. Please try again below, or if 
			you do not have an account you can <A HREF="register.jsp">register here.</A><BR><BR>
			</div>
		<% } %>

		<% session.removeAttribute("error"); %>

		<FORM ACTION="/ebook-app/loginauth" METHOD="POST">
			Username: <BR><INPUT TYPE="TEXT" NAME="username"><BR><BR>
			Password: <BR><INPUT TYPE="PASSWORD" NAME="password"><BR><BR>
			<INPUT TYPE="SUBMIT" VALUE="Submit">
		</FORM>
		
	</div><BR><BR>
	<div id="footer">
    	<h1>
		BookBar 2011
        </h1>
	</div>
</div>

</body>
</html>