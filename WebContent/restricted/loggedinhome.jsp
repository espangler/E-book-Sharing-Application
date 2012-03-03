<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="../style2.css" rel="stylesheet" type="text/css">
	<title>Home</title>
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
			<li><a href="loggedinhome.jsp"><img src="../images/nav01b.gif" BORDER="0"></a></li>
			<li><a href="books.jsp"><img src="../images/nav02a.gif" BORDER="0"></a></li>
			<li><a href="bookshelf.jsp"><img src="../images/nav05a.gif" BORDER="0"></a></li>
			<li><a href="submitbook.jsp"><img src="../images/nav06a.gif" BORDER="0"></a></li>
			<li><a href="../logout"><img src="../images/nav07a.gif" BORDER="0"></a></li>
		</ul>
	</div>
	<div id="content">
	<h1>Welcome to BookBar</h1>
		<div id="text">
		You are currently logged in.
		</div>
		<div id="text"><BR>
			<h2>Here are the current newest 3 books:</h2>

			<jsp:useBean id="myBookBean"
					class="test.BookBean" />
			<% myBookBean.setBookCount(); %>
			<% if (myBookBean.getBookCount() >= 3)  { %>
				<% int counter = myBookBean.getBookCount(); %>
				<% for (int i=1; i <= 3; i++) { %>
				
					<% myBookBean.setTitle(counter); %>
				    <% myBookBean.setAuthorFirst(counter); %>
				    <% myBookBean.setAuthorLast(counter); %>
				    <% myBookBean.setGenre(counter); %>
						
					<div id = "entry">
					<h2>New Book <%= i %> </h2>
					<div id="heading">Title: </div><%= myBookBean.getTitle() %><br>
					<div id="heading">Author: </div><%= myBookBean.getAuthorFirst()%>
											<%= myBookBean.getAuthorLast() %><br>
					<div id="heading">Genre: </div><%= myBookBean.getGenre() %><br><br>
									
					<form name="input" action="../addtoshelf" method="get">
					<input type="checkbox" name="add" value="<%= counter %>">
					Add this book to my shelf.<br>
					<input type="submit" value="Add">
			   		</form>
					</div><br>
					<% counter--; %>
				<% } %>
			<% } else { %>
				<br>
				<h2>It looks like there are not yet three books in the catalog.
					Try back later or login to add books.</h2>
			<% } %>
			</div>
		</div>	<BR><BR>
	<div id="footer">
    	<h1>
		BookBar 2011
        </h1>
	</div>
</div>

</body>
</html>