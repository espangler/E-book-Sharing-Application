package test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * RemoveBookFromShelf servlet to process form data related to removing a 
 * book from a user's shelf.
 */
@WebServlet("/removefromshelf")
public class RemoveBookFromShelf extends HttpServlet {

	// doGet method
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
  		throws ServletException, IOException {		 
		response.setContentType("text/html");
		
		// retrieve parameter from form
		String removeValue = request.getParameter("remove");
		
		// convert to int
		int bookID = Integer.parseInt(removeValue);
		
		// retrieve the user's username
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		// create bookshelf object
		Bookshelf bookshelf = new Bookshelf();

		// remove book from user's shelf using bookID and username
		bookshelf.removeBookFromShelf(bookID, username);
		
		// redirect to bookshelf
		response.sendRedirect("/ebook-app/restricted/bookshelf.jsp");
	} // end doGet
		  
	// doPost calls doGet
	public void doPost(HttpServletRequest request,
		  			HttpServletResponse response)
				throws ServletException, IOException {
		doGet(request, response);
	} // end doPost
	
} // end class RemoveBookFromShelf

