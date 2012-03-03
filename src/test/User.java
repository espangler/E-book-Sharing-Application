package test;

/**
 * User class to manage user operations.
 */
public class User {

	DBConnect connectObject = new DBConnect();
	
	private String database = "test";
	private String userTable = "users";
	private String usernameColumn = "username";
	private String passwordColumn = "password";
	private String emailColumn = "email";
	private String idColumn = "user_id";
	
	User() {
		super();
	}
	
	// method to validate a username and password provided
	boolean validateLogin(String username, String password) {
		
		  // retrieve the user_id associated with the username
		  int id1 = connectObject.retrieveIntWithString(username, database, userTable, 
				  usernameColumn, idColumn);

		  // retrieve the user_id associated with the password
		  int id2 = connectObject.retrieveIntWithString(password, database, userTable, 
				  passwordColumn, idColumn);

		  // check if these ids a) exist, and b) match each other
		  if ((id1 > 0) && (id2 > 0) && (id1 == id2))
			  return true;
		  else
			  return false;
	}
	
	// addToDB adds the email, username, and password info to the database
	public void registerUser(String username, String password, String email) {
		  
		// creates a new row in the database with the provided username
		connectObject.addStringToDB(username, database, userTable, usernameColumn);
		
		// retrieve user id from the new row
		int userID = connectObject.retrieveIntWithString(username, database, userTable, 
				usernameColumn, idColumn);
		
		// adds the password to the row
		connectObject.stringUpdateTable(password, idColumn, userID, database, userTable, 
				  passwordColumn);
		
		// adds the email address to the row
		connectObject.stringUpdateTable(email, idColumn, userID, database, userTable, 
				  emailColumn);
		
	 } // end addToDB
	
} // end class User
