package test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Login class to
 */
@WebServlet("/loginauth")
public class Login extends HttpServlet {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	  		throws ServletException, IOException {
		  response.setContentType("text/html");
	    
		  // retrieve the parameters entered by the user
		  String username = request.getParameter("username");
		  String password = request.getParameter("password");
		  
		  // create a user object
		  User user = new User();
	    
		  // if the username/password are not verified send user page with error
		  if (!(user.validateLogin(username, password))) {
			  HttpSession session = request.getSession();
			  String error = "error";
			  session.setAttribute("error", error);
			  response.sendRedirect("/ebook-app/login.jsp");
		  } 
		  else {
			  // Start a new session, record relevant info in session objects
			  // and redirect to logged-in home.
			  HttpSession session = request.getSession();
			  session.setMaxInactiveInterval(60);
			  String loggedIn = "yes";
			  session.setAttribute("loggedIn", loggedIn);
			  session.setAttribute("username", username);
			  response.sendRedirect("/ebook-app/restricted/loggedinhome.jsp");
		  }
	  } // end doGet
	  
	  // doPost calls doGet
	  public void doPost(HttpServletRequest request,
			  			HttpServletResponse response)
	  		throws ServletException, IOException {
		  doGet(request, response);
	  } // end doPost
	  
} // end class Login
