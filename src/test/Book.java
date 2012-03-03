package test;

/**
 * Book class to
 */
public class Book {
	
	// create object used to connect to the database, declare/initialize related fields	
	DBConnect connectObject = new DBConnect();

	// set parameters for database actions
	private String database = "test";
	private String authTable = "authors";
	private String bookTable = "books";
	private String genreTable = "genres";
	private String bookAuthTable = "book_author";
	private String bookGenreTable = "book_genre";
	private String authorFColumn = "first_name";
	private String authorLColumn = "last_name";
	private String titleColumn = "title";
	private String genreColumn = "genre";
	private String authIDColumn = "author_id";
	private String bookIDColumn = "book_id";
	private String genreIDColumn = "genre_id";
	
	Book() {
		super();
	}
	
	// method to add a book to the database
	public void addBookToDB(String title, String authorFirst, String authorLast, String genre) {
    	// add book to database
		addTitle(title);
		
		// add author to database if not there already
		addAuthor(authorFirst, authorLast);
		
		// record the book as belonging to the author
		recordAuthor(title, authorFirst, authorLast);
		
		// record the book as belonging to the genre
		recordGenre(title, genre);
	} // end addBookToDB
	
	// method to add a book title to the database
	protected void addTitle(String title) {	
		// adds the title to the database
		connectObject.addStringToDB(title, database, bookTable, titleColumn);
	} // end addTitle

	// method to add an author to the database if not already there
  	protected void addAuthor(String authorFirst, String authorLast) {
  		
  		// look for the author in the database
  		int authID = (connectObject.retrieveIDTwoParams(authorFirst, authorLast, database, authTable,
				  authorFColumn, authorLColumn, authIDColumn));

  		// if there is no entry for the author already in the database
	  	if (authID < 0) {
	  		  // make a new row in the authors table with the first and last name
			  connectObject.addTwoStringsToDB(authorFirst, authorLast, database, authTable, 
					  authorFColumn, authorLColumn);
	  	}
  	} // end addAuthor

    // method associates a book with an author
    protected void recordAuthor(String title, String authorFirst, String authorLast) {
		  
    	// retrieve the book id given the title
    	int bookID = connectObject.retrieveIntWithString(title, database, bookTable, titleColumn, 
				  bookIDColumn);
		  
    	// retrieve the author id given the first and last name
    	int authorID = connectObject.retrieveIDTwoParams(authorFirst, authorLast,
				  database, authTable, authorFColumn, authorLColumn, authIDColumn);
		
    	// create a new entry in the book_author table with the bookID
    	connectObject.addIntToDB(bookID, database, bookAuthTable, bookIDColumn);
		  
    	// update the same row by adding the authorID
    	connectObject.intUpdateTable(authorID, bookIDColumn, bookID, database, bookAuthTable, 
				  authIDColumn);
    } // end recordAuthor
	  
    // method associates a book with a genre
    protected void recordGenre(String title, String genre) {
	  
    	// retrieve the book id given the title
    	int bookID = connectObject.retrieveIntWithString(title, database, bookTable, titleColumn, 
				  bookIDColumn);
		  
    	// retrieve the genre id given the genre
    	int genreID = connectObject.retrieveIntWithString(genre, database, genreTable, genreColumn, 
				  genreIDColumn);
		  
    	// create a new entry in the book_genre table with the bookID
    	connectObject.addIntToDB(bookID, database, bookGenreTable, bookIDColumn);
		  
    	// update the same row by adding the genreID
    	connectObject.intUpdateTable(genreID, bookIDColumn, bookID, database, bookGenreTable, 
				  genreIDColumn);
    } // end recordGenre

} // end class book
