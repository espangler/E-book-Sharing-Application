package test;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * SubmitBook class to process book entry submissions
 */
@WebServlet("/submitabook")
public class SubmitBook extends HttpServlet {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// create book object
	  Book book = new Book();
	  
	  // doGet method
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	  		throws ServletException, IOException {
	    response.setContentType("text/html");
	    
	    String title = request.getParameter("title");
	    String authorFirst = request.getParameter("authorFirst");
	    String authorLast = request.getParameter("authorLast");
	    String genre = request.getParameter("genre");
	    
	    // create a session in which to place attributes
	    // to either thank the user or notify of an error
		HttpSession session = request.getSession();
		  
	    // error if any of the fields were left blank
	    if (!longEnough(title, authorFirst, authorLast, genre)) {
		    session.setAttribute("error", "error");
	    	response.sendRedirect("/ebook-app/restricted/submitbook.jsp");
	    }
	    else {
	    	// add book to db
	    	book.addBookToDB(title, authorFirst, authorLast, genre);
			
	    	// set attribute to thank user
		    session.setAttribute("addBook", "yes");
		    
			// redirect user to success page
	    	response.sendRedirect("/ebook-app/restricted/books.jsp");
	    }
	  } // end doGet
	  
	  // doPost calls doGet
	  public void doPost(HttpServletRequest request, HttpServletResponse response)
	  		throws ServletException, IOException {
		  doGet(request, response);
	  } // end doPost
	
	  // method to ensure values entered are not null
	  protected boolean longEnough(String title, String authorFirst, 
			  String authorLast, String genre) {
		  boolean titleValid = (title.length() > 0);
		  boolean authorFirstValid = (authorFirst.length() > 0);
		  boolean authorLastValid = (authorLast.length() > 0);
		  boolean genreValid = (authorLast.length() > 0);
		  
		  if (titleValid && authorFirstValid && authorLastValid && genreValid)
			  return true;
		  else	
			  return false;
	  } // end longEnough

} // end class SubmitBook
