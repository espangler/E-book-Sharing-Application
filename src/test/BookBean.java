package test;

/**
 * BookBean class to
 */
public class BookBean {
	
	// create new database connect object
	private DBConnect connectObject= new DBConnect();
	
	// fields for database lookup
	private String database="test";
	private String bookTable="books";
	private String authorTable="authors";
	private String genreTable="genres";
	private String bookAuthorTable="book_author";
	private String bookGenreTable="book_genre";
	private String bookUserTable="book_user";
	private String userTable = "users";
	private String bookIDColumn="book_id";
	private String authIDColumn="author_id";
	private String genreIDColumn="genre_id";
	private String userIDColumn="user_id";
	private String usernameColumn="username";
	private String titleColumn = "title";
	private String authorFColumn = "first_name";
	private String authorLColumn = "last_name";
	private String genreColumn = "genre";
	private String activeStatusColumn = "active";
	private int bookID=-1;
	private int bookCount=-1;
	private int bookUserCount=-1;
	private int bookshelfCount=-1;
	
	// fields for bean
	private String title=null;
	private String authorL=null;
	private String authorF=null;
	private String genre=null;
	private int userID=-1;
	private boolean activeBookUserMatch = false;
	
	// find the user's ID given the username
	public void setUserID(String username) {
		userID = connectObject.retrieveIntWithString(username, database, userTable, 
				usernameColumn, userIDColumn);
	}
	
	// find number of book-user associations
	public void setBookshelfCount() {
		bookshelfCount = connectObject.countRows(database, bookUserTable);
	}
	
	// find the current number of books
	public void setBookCount() {
		bookCount = connectObject.countRows(database, bookTable);
	}
	
	// check how many books a user is associated with
	public void setBookUserCount(int userID) {
		bookUserCount = connectObject.countRowsWithValue(userID, database, bookUserTable, 
				userIDColumn);
	}
	
	// find the title based on the bookID
	public void setTitle(int bookID) {
		title = connectObject.retrieveString(bookID, database, bookTable, bookIDColumn, titleColumn);
	}
	
	// find the author first name based on the bookID
	public void setAuthorFirst(int bookID) {
		int authID = connectObject.retrieveInt(bookID, database, bookAuthorTable, 
				  bookIDColumn, authIDColumn);
		authorF = connectObject.retrieveString(authID, database, authorTable, authIDColumn, authorFColumn);
	}
	
	// find the author last name based on the bookID
	public void setAuthorLast(int bookID) {
		int authID = connectObject.retrieveInt(bookID, database, bookAuthorTable, 
				  bookIDColumn, authIDColumn);
		authorL = connectObject.retrieveString(authID, database, authorTable, authIDColumn, authorLColumn);
	}
	
	// find the genre based on the bookID
	public void setGenre(int bookID) {
		int genreID = connectObject.retrieveInt(bookID, database, bookGenreTable, 
				  bookIDColumn, genreIDColumn);
		genre = connectObject.retrieveString(genreID, database, genreTable, genreIDColumn, genreColumn);
	}
	
	// determine if the book and user are associated
	public void setActiveBookUserMatch(int bookID, int userID) {
		activeBookUserMatch = connectObject.retrieveBoolWithTwoValues(bookID, userID, 
				database, bookUserTable, bookIDColumn, userIDColumn, activeStatusColumn);
	}
	
	public int getUserID() {
		return userID;
	}
	
	public int getBookCount() {
		return bookCount;
	}
	
	public boolean getActiveBookUserMatch() {
		return activeBookUserMatch;
	}
	
	public int getBookUserCount() {
		return bookUserCount;
	}
	
	public int getBookshelfCount() {
		return bookshelfCount;
	}
	
	public int getBookID() {
		return bookID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthorLast() {
		return authorL;
	}
	
	public String getAuthorFirst() {
		return authorF;
	}
	
	public String getGenre() {
		return genre;
	}
}
