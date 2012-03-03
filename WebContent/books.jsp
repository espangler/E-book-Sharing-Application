<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="style2.css" rel="stylesheet" type="text/css">
	<title>Books</title>
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
			<li><a href="books.jsp"><img src="images/nav02b.gif" BORDER="0"></a></li>
			<li><a href="login.jsp"><img src="images/nav03a.gif" BORDER="0"></a></li>
			<li><a href="register.jsp"><img src="images/nav04a.gif" BORDER="0"></a></li>
		</ul>
	</div>
	<div id="content">
	<h1>Available Books</h1>
			<div id="text">
			<jsp:useBean id="myBookBean"
					class="test.BookBean" />
			<% myBookBean.setBookCount(); %>
			<% if (myBookBean.getBookCount() > 0)  { %>
				<% for (int i=1; i <= myBookBean.getBookCount(); i++) { %>
				
					 <% myBookBean.setTitle(i); %>
					 <% myBookBean.setAuthorFirst(i); %>
					 <% myBookBean.setAuthorLast(i); %>
					 <% myBookBean.setGenre(i); %>
					<div id="entry">
					<h2>Book number <%= i %> </h2>
					<div id="heading">Title: </div><%= myBookBean.getTitle() %><br>
					<div id="heading">Author Name: </div><%= myBookBean.getAuthorFirst()%>
											<%= myBookBean.getAuthorLast() %><br>
					<div id="heading">Genre: </div><%= myBookBean.getGenre() %><br>
					</div><br>
				<% } %>

			<% } else { %>
				<br>
				<h2>It looks like there are no books at this time.</h2>
			<% } %>
			</div>
		</div>
	</div><BR><BR>
	<div id="footer">
    	<h1>
		BookBar 2011
        </h1>
	</div>

</body>
</html>