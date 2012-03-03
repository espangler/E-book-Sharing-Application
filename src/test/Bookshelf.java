package test;

/**
 * Bookshelf class to manage bookshelf operations
 */
public class Bookshelf {

	// create object used to connect to the database, declare/initialize related fields	
	DBConnect connectObject = new DBConnect();
	
	// variables to perform database lookup
	private String database = "test";
	private String userTable = "users";
	private String usernameColumn = "username";
	private String userIDColumn = "user_id";
	private String bookIDColumn = "book_id";
	private String bookUserTable = "book_user";
	private String boolColumn = "active";

	// method to associate a user with a book
	public void addBookToShelf(int bookID, String username) {

		// create a connect object
		DBConnect connectObject = new DBConnect();
		
		// retrieve the user's id given the username
		int userID = connectObject.retrieveIntWithString(username, database, userTable, 
				usernameColumn, userIDColumn);

		// if association between user and book doesn't already exist create one
		if ((connectObject.countRowsWithTwoValues(bookID, userID, database, bookUserTable,
				bookIDColumn, userIDColumn)) <= 0) {

	    	// create a new entry in the book_user table with the bookID
	    	connectObject.addIntToDB(bookID, database, bookUserTable, bookIDColumn);

	    	// update the same row by adding the userID
	    	connectObject.intUpdateTable(userID, bookIDColumn, bookID, database, bookUserTable, 
					  userIDColumn);
		}

    	// set the entry's active status to true
    	connectObject.updateBoolStatus(userID, bookID, userIDColumn, bookIDColumn, database, 
    		 	bookUserTable, boolColumn, true);
    	
	} // end addBookToShelf
	
	// method to dissassociate a user with a book
	public void removeBookFromShelf(int bookID, String username) {
		
	}
} // end class Bookshelf
