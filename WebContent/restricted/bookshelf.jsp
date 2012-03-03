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
			<li><a href="loggedinhome.jsp"><img src="../images/nav01a.gif" BORDER="0"></a></li>
			<li><a href="books.jsp"><img src="../images/nav02a.gif" BORDER="0"></a></li>
			<li><a href="bookshelf.jsp"><img src="../images/nav05b.gif" BORDER="0"></a></li>
			<li><a href="submitbook.jsp"><img src="../images/nav06a.gif" BORDER="0"></a></li>
			<li><a href="../logout"><img src="../images/nav07a.gif" BORDER="0"></a></li>
		</ul>
	</div>
	<div id="content">
	<jsp:useBean id="myBookBean"
			class="test.BookBean" />
	<% String username = (String)session.getAttribute("username"); %>
	<h1><%= username %>'s Bookshelf</h1>
			<div id="text">
			
			<% myBookBean.setBookshelfCount(); %>
			<% myBookBean.setBookCount(); %>
			<% myBookBean.setUserID(username); %>
			<% int userID = myBookBean.getUserID(); %>
			<% myBookBean.setBookUserCount(userID); %>
			<% int booksOnShelf = myBookBean.getBookUserCount(); %>
			<% int counter = 1; %>
			
			You currently have <%= booksOnShelf %> books on your bookshelf.<br><br>
			
			<% if (myBookBean.getBookUserCount() > 0)  { %>
				<% for (int i=1; i <= myBookBean.getBookCount(); i++) { %>
					<% myBookBean.setActiveBookUserMatch(i, userID); %>
					<% if (myBookBean.getActiveBookUserMatch())  {%>
							<% myBookBean.setTitle(i); %>
					 		<% myBookBean.setAuthorFirst(i); %>
					 		<% myBookBean.setAuthorLast(i); %>
					 		<% myBookBean.setGenre(i); %>
					 		<div id="entry">
							<h2>Book number <%= counter %> </h2>
							<div id="heading">Title: </div><%= myBookBean.getTitle() %><br>
							<div id="heading">Author Name: </div><%= myBookBean.getAuthorFirst()%>
											<%= myBookBean.getAuthorLast() %><br>
							<div id="heading">Genre: </div><%= myBookBean.getGenre() %><br><br>
							<% counter++; %>
							<FORM ACTION="../removefromshelf" METHOD="POST">
							<input type="checkbox" name="remove" value=<%= i %>>
							Remove this book from my shelf.<br>
							<input type="submit" value="Remove">
			   				</FORM>
							</div><br>
					<% } %>
				<% } %>
			<% } else { %>
				<br>
				<h2>It looks like there are no books on your shelf!</h2>
			<% } %>
			</div>
	</div><BR><BR>
	<div id="footer">
    	<h1>
		BookBar 2011
        </h1>
	</div>
</div>

</body>
</html>