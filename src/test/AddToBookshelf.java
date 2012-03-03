package test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * AddToBookshelf servlet to retrieve and process form information related to adding
 * a book to a user's shelf.
 */
@WebServlet("/addtoshelf")
public class AddToBookshelf extends HttpServlet {

	// doGet method
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
  		throws ServletException, IOException {		 
		response.setContentType("text/html");
		
		// retrieve parameter from form
		String addValue = request.getParameter("add");
		
		// convert to int
		int bookID = Integer.parseInt(addValue);
		
		// retrieve the user's username
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		// create bookshelf object
		Bookshelf bookshelf = new Bookshelf();

		// add book to user's shelf using bookID and username
		bookshelf.addBookToShelf(bookID, username);
		
		// redirect to bookshelf
		response.sendRedirect("/ebook-app/restricted/bookshelf.jsp");
	} // end doGet
		  
	// doPost calls doGet
	public void doPost(HttpServletRequest request,
		  			HttpServletResponse response)
				throws ServletException, IOException {
		doGet(request, response);
	} // end doPost
	
} // end class AddToBookshelf

