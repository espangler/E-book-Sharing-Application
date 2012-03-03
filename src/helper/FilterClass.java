package helper;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Filter class to restrict access to member pages to logged in members
 */
public class FilterClass implements Filter {
	 protected FilterConfig filterConfig;
	 
	 // required init method
	 public void init(FilterConfig filterConfig) {
		 this.filterConfig = filterConfig;
	 }

	 // required doFilter method
	 // redirects users trying to access restricted part of site when not logged in
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	 				throws java.io.IOException, javax.servlet.ServletException {
		 HttpServletRequest req = (HttpServletRequest) request;
		 HttpServletResponse res = (HttpServletResponse) response;
		 HttpSession session = req.getSession();
		
		 String loggedIn = (String)session.getAttribute("loggedIn");
		 
		 if (loggedIn == null)
			 res.sendRedirect("../pleaselogin.html");
		 else if (loggedIn == "yes")
			 chain.doFilter(request,response);
	 }

	 // required destroy method
	 public void destroy() {
		 this.filterConfig = null;
	 }
}

