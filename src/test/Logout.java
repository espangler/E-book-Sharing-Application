package test;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Logout servlet to log user out.
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {

	// doGet method
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loggedIn");
		session.removeAttribute("username");
		session.invalidate();
		response.sendRedirect("/ebook-app/homepage.jsp");
	}

	// doPost calls doGet
	public void doPost(HttpServletRequest request,
			  			HttpServletResponse response)
	  		throws ServletException, IOException {
		doGet(request, response);
	} // end doPost

}
