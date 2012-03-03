package test;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Register class to process registration
 */
@WebServlet("/registration")
public class Register extends HttpServlet {
	  
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// doGet method checks that values entered are not null and available
	  // if not, forwards to a registration page displaying a relevant error message
	  // if values are good, the user's information is added to the database
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	  			throws ServletException, IOException {
		  response.setContentType("text/html");
	    
		  // the parameters received from the user via the form
		  String email = request.getParameter("email");
		  String username = request.getParameter("username");
		  String password = request.getParameter("password");

		  // declare/initialize database related fields
		  String database = "test";
		  String table = "users";
		  String usernameColumn = "username";
		  String emailColumn = "email";
		  String idColumn = "user_id";
		  
		  // create user object, error string, and session
		  User user = new User();
	  	  String error = "error";
		  HttpSession session = request.getSession();
		  
		  // error if any of the fields were left blank
		  if (!(longEnough(email, username, password))) {
		  	  session.setAttribute("error1", error);
		  }
		  // error if the username is already taken
		  if (!(valueAvailable(username, database, table, usernameColumn, 
				  idColumn))) {
		  	  session.setAttribute("error2", error);
		  }
		  // error if the email is already taken
		  if (!(valueAvailable(email, database, table, emailColumn, 
				  idColumn))) {
		  	  session.setAttribute("error3", error);
		  }
		  // if error(s) redirect to registration page
		  if ((session.getAttribute("error1") != null) || 
				  (session.getAttribute("error2") != null) ||
				  (session.getAttribute("error3") != null)) {
			  response.sendRedirect("/ebook-app/register.jsp");
		  }
		  // if entries are acceptable, register user and redirect to success page
		  else {
			  user.registerUser(username, password, email);
			  response.sendRedirect("/ebook-app/registersuccess.html");
		  }
	  } // end doGet
	  
	  // doPost calls doGet
	  public void doPost(HttpServletRequest request,
			  			HttpServletResponse response)
	  		throws ServletException, IOException {
		  doGet(request, response);
	  } // end doPost
	
	  // method to ensure that no value is blank
	  protected boolean longEnough(String email, String username, 
			  String password) 
	  {
		  // check email, username, and password for length > 0
		  boolean emailValid = (email.length() > 0);
		  boolean userValid = (username.length() > 0);
		  boolean passValid = (password.length() > 0);
		  
		  // if all are > 0, fields are an ok length
		  if (emailValid && userValid && passValid)
			  return true;
		  else	
			  return false;
	  } // end longEnough
	  
	  // method to check if a particular table and column in the db already
	  // contains the stringToMatch
	  protected boolean valueAvailable(String stringToMatch, String database, 
			  String table, String tableColumn, String idColumn) {
		  // create connect object
		  DBConnect connectObject = new DBConnect();
		  
		  // if the parameters return a positive match for the stringToMatch
		  if ((connectObject.retrieveIntWithString(stringToMatch, database, 
				  table, tableColumn, idColumn)) > 0)
			  return false;
		  else
			  return true;
	  } // end valueTaken
	  
} // end class Register


