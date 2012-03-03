<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="../style2.css" rel="stylesheet" type="text/css">
	<title>Submit a Book</title>
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
			<li><a href="loggedinhome.jsp"><img src="../images/nav01a.gif" BORDER="0"></a></li>
			<li><a href="books.jsp"><img src="../images/nav02a.gif" BORDER="0"></a></li>
			<li><a href="bookshelf.jsp"><img src="../images/nav05a.gif" BORDER="0"></a></li>
			<li><a href="submitbook.jsp"><img src="../images/nav06b.gif" BORDER="0"></a></li>
			<li><a href="logout.jsp"><img src="../images/nav07a.gif" BORDER="0"></a></li>
		</ul>
	</div>
	<div id="content">
		<h1>Submit a Book</h1>
		<div id="text">
		<% String error = (String)session.getAttribute("error"); %>
		
		<% if (error == "error" ) { %>
			<div id="errorText">
			All fields must be filled in to submit a book.
			</div>
		<% } %>
		
		<% session.removeAttribute("error"); %>		
		
		Add a book to the collection. <BR><BR>
		</div>
		<FORM ACTION="/ebook-app/submitabook" METHOD="POST">
			Book Title: <BR><INPUT TYPE="TEXT" NAME="title"><BR><BR>
			Author's First Name: <BR><INPUT TYPE="TEXT" NAME="authorFirst"><BR><BR>
			Author's Last Name: <BR><INPUT TYPE="TEXT" NAME="authorLast"><BR><BR>
			Genre: <BR><select name="genre">
				<option value = "Fiction">Fiction</option>
				<option value = "Non-Fiction">Non-Fiction</option>
				<option value = "Science Fiction">Science Fiction</option>
				</select><BR><BR>
			<INPUT TYPE="SUBMIT" VALUE="Submit">
		</FORM>
	</div>
	<div id="footer">
    	<h1>
		BookBar 2011
        </h1>
	</div>
</div>

</body>
</html>