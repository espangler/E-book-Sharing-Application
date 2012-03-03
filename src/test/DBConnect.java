package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBConnect class to allow interactions with the database
 */
public class DBConnect {
	
	  // method to update a bool column and row given two ints
	  public void updateBoolStatus(int int1, int int2, String int1Col, String int2Col, 
			  String database, String table, String boolCol, boolean value) {
		  
		  // prepare update string
		  String updateString = ("update " + table + " set " + boolCol + "=" + value +
				  " where " + int1Col + "=" + int1 + " and " + int2Col + "=" + int2);
		  
		  // execute update
		  executeUpdate(updateString, database);
	  }
	
	  // method to return a bool value given two ints
	  public boolean retrieveBoolWithTwoValues(int int1, int int2, String database, 
			  String table, String int1Col, String int2Col, String columnToReturn) {
		  boolean value = false;
		  
		  // prepare query string
		  String queryString = ("select * from " + table + " where " + int1Col + "=" +
				  int1 + " and " + int2Col + "=" + int2);
		  
		  // execute query
		  value = executeQueryReturnBool(queryString, database, columnToReturn);
		  
		  return value;
	  }
	  
	  // method to retrieve a string from a table given an int key
	  public String retrieveString(int key, String database, String table, 
			  String columnToMatch, String columnToReturn) {
		  String value = null;
		  
		  // prepare string to pass to the execute method
		  String queryString = ("select * from " + table + " where " + 
				  columnToMatch + "=" + key);
		  
		  // execute query
		  value = executeQueryReturnString(queryString, database, columnToReturn);
		  
		  return value;
	  } // end retrieveString
	  
	  // method to retrieve an int given an int
	  public int retrieveInt(int key, String database, String table, 
			  String columnToMatch, String columnToReturn) {
		  int value = -1;
		  
		  // prepare string to pass to the execute method
		  String queryString = ("select * from " + table + " where " + 
				  columnToMatch + "=" + key);
		  
		  // execute query
		  value = executeQueryReturnInt(queryString, database, columnToReturn);
		  
		  return value;
	  } // end retrieveInt
	
	  // method to retrieve an id value from a table given a string to match
	  public int retrieveIntWithString(String stringToMatch, String database, 
			  String table, String columnToMatch, String columnToReturn) {
		  int id = -1;
		  // prepare string to pass to the execute method
		  String queryString = ("select * from " + table + 
	            		" where " + columnToMatch + "=" + "'" + stringToMatch + "'");
		  
		  // execute query
		  id = executeQueryReturnInt(queryString, database, columnToReturn);
          
          // return id, if no match made value will be -1
		  return id;
	  } // end retrieveIDOneParam
	  
	  // method to retrieve an id value from a table given two strings
	  public int retrieveIDTwoParams(String string1, String string2, String database, 
			  String table, String tableColumn1, String tableColumn2, String idColumn)
	  {		  
		  // prepare string to pass the execute method
		  String queryString = ("select * from " + table + 
	            		" where " + tableColumn1 + "=" + "'" + string1 + "'" + 
	            		" and " + tableColumn2 + "=" + "'" + string2 + "'");
		  
		  // execute query 
		  int id = executeQueryReturnInt(queryString, database, idColumn);
		  
          // return id, if no match made value will be -1
		  return id;
	  } // end retrieveIDTwoParams
	  
	  // method to add a string to a particular table column
	  public void addStringToDB(String stringToAdd, String database, String table, 
			  String tableColumn)
	  {
		  // prepare string to pass to the update method
		  String updateString = ("insert into " + table + " (" + tableColumn + 
          			") values (" + "'" + stringToAdd + "'" + ")");
		  
		  // execute the update
		  executeUpdate(updateString, database);
	  }
	  
	  // method to add two strings to a table row
	  public void addTwoStringsToDB(String string1, String string2, String database, 
			  String table, String tableColumn1, String tableColumn2)
	  {
		  // prepare string to pass to the update method
		  String updateString = ("insert into " + table + " (" + tableColumn1 + ", " + 
				  tableColumn2 + ") values (" + "'" + string1 + "'" + ", "  + "'" + 
				  string2 + "'" + ")");
		  
		  // execute the update
		  executeUpdate(updateString, database);
	  } // end addTwoStringsToDB
	  
	  // method to update a particular column and row in a table
	  public void stringUpdateTable(String stringToUpdate, String keyColumn, int key, 
			  String database, String table, String tableColumn)
	  {
		 // prepare string to pass to the update method
		 String updateString = ("update " + table + " set " + tableColumn + "=" + 
				 "'" + stringToUpdate + "'" + " where " + keyColumn + "=" + key);
		 
		 // execute the update
		 executeUpdate(updateString, database);
	  } // end stringUpdateTable
	  
	  // method to add an int to a table
	  public void addIntToDB(int intToAdd, String database, String table, 
			  String tableColumn)
	  {
		  // prepare string to pass to the update method
		  String updateString = ("insert into " + table + " (" + tableColumn + 
	            		") values (" + intToAdd +")");
			 
		  // execute the update
		  executeUpdate(updateString, database);
	  } // end addIntToDB
	  
	  // method to update a table with an int
	  public void intUpdateTable(int updateInt, String keyColumn, int key, 
			  String database, String table, String tableColumn)
	  {		  
		  // prepare string to pass to the update method
		  String updateString = ("update " + table + " set " + tableColumn + 
				  "=" + updateInt + " where " + keyColumn + "=" + key);
		  
		  // execute the update
		  executeUpdate(updateString, database);
	  } // end intUpdateTable
	  
	  // method to count the rows in a table
	  public int countRows(String database, String table) 
	  {
		  int rowCount = -1;
		  String countColumn = "count(*)";
		  
		  // prepare string to pass to the update method
		  String queryString = ("select count(*) from " + table);
		  
		  // execute query
		  rowCount = executeQueryReturnInt(queryString, database, countColumn);
		  
	      // return rowCount
		  return rowCount;
	  } // end countRows
	  
	  public int countRowsWithValue(int value, String database, String table, String column) 
	  {
		  int rowCount = -1;
		  String countColumn = "count(*)";
		  
		  // prepare string to pass to the update method
		  String queryString = ("select count(*) from " + table + " where " +
				  column + "=" + value);
		  
		  // execute query
		  rowCount = executeQueryReturnInt(queryString, database, countColumn);
		  
	      // return rowCount
		  return rowCount;
	  } // end countRows
	
	  public int countRowsWithTwoValues(int value1, int value2, String database, 
			  String table, String column1, String column2) 
	  {
		  int rowCount = -1;
		  String countColumn = "count(*)";
		  
		  // prepare string to pass to the update method
		  String queryString = ("select count(*) from " + table + " where " +
				  column1 + "=" + value1 + " and " + column2 + "=" + value2);
		  
		  // execute query
		  rowCount = executeQueryReturnInt(queryString, database, countColumn);
		  
	      // return rowCount
		  return rowCount;
	  } // end countRows
	  
	  // method to execute a query and return an int
	  public int executeQueryReturnInt(String queryString, String database, String tableColumn)
	  {
	      String DBUrl = "jdbc:mysql:///" + database + "";
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      int value = -1;
	      
		  // try to make a JDBC connection to the database and retrieve some data
	      try {
              	//create a new instance of the mysql driver
	    	  	try {
	                Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	            }
	            catch (Exception Ex) {
	                System.out.println("Unable to Load Driver: " + Ex.toString());
	            }
				// get a new connection object 
	            conn = DriverManager.getConnection(DBUrl);

	            // prepare MySQL query
	            pstmt = conn.prepareStatement(queryString);
	            
	            ResultSet rs = pstmt.executeQuery();
	        	
	            while (rs.next()) {
	        		  value = rs.getInt(tableColumn);
	        	}
	        }
	        catch (SQLException sqlEx) {
	        	System.out.println("Caught SQL Exception: " + sqlEx.toString());
	        }
	        
	        // now close the statement and connection if they exist
	        if (pstmt != null) {
	            try {
	                 pstmt.close();
	            }
	            catch (SQLException sqlEx) {
	                System.out.println("Could not close: " + sqlEx.toString());
	            }
	        }
	        if (conn != null) {
	        	try {
	        		conn.close();
	        	}
	        	catch (SQLException sqlEx) {
	        		System.out.println("Could not close: " + sqlEx.toString());
	             }
	        }
	        // now return the result set
	        return value;
	  } // end executeQueryReturnInt
	  
	  // method to execute a query and return a boolean value
	  public boolean executeQueryReturnBool(String queryString, String database, String tableColumn)
	  {
	      String DBUrl = "jdbc:mysql:///" + database + "";
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      boolean value = false;
	      
		  // try to make a JDBC connection to the database and retrieve some data
	      try {
              	//create a new instance of the mysql driver
	    	  	try {
	                Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	            }
	            catch (Exception Ex) {
	                System.out.println("Unable to Load Driver: " + Ex.toString());
	            }
				// get a new connection object 
	            conn = DriverManager.getConnection(DBUrl);

	            // prepare MySQL query
	            pstmt = conn.prepareStatement(queryString);
	            
	            ResultSet rs = pstmt.executeQuery();
	        	
	            while (rs.next()) {
	        		  value = rs.getBoolean(tableColumn);
	        	}
	        }
	        catch (SQLException sqlEx) {
	        	System.out.println("Caught SQL Exception: " + sqlEx.toString());
	        }
	        
	        // now close the statement and connection if they exist
	        if (pstmt != null) {
	            try {
	                 pstmt.close();
	            }
	            catch (SQLException sqlEx) {
	                System.out.println("Could not close: " + sqlEx.toString());
	            }
	        }
	        if (conn != null) {
	        	try {
	        		conn.close();
	        	}
	        	catch (SQLException sqlEx) {
	        		System.out.println("Could not close: " + sqlEx.toString());
	             }
	        }
	        // now return the result set
	        return value;
	  } // end executeQueryReturnBool
	  
	  // method to execute a query and return a string
	  public String executeQueryReturnString(String queryString, String database, String columnToReturn)
	  {
	      String DBUrl = "jdbc:mysql:///" + database + "";
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String value = null;
	      
		  // try to make a JDBC connection to the database and retrieve some data
	      try {
              	//create a new instance of the mysql driver
	    	  	try {
	                Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	            }
	            catch (Exception Ex) {
	                System.out.println("Unable to Load Driver: " + Ex.toString());
	            }
				// get a new connection object 
	            conn = DriverManager.getConnection(DBUrl);

	            // prepare MySQL query
	            pstmt = conn.prepareStatement(queryString);
	            
	            ResultSet rs = pstmt.executeQuery();
	        	
	            while (rs.next()) {
	        		  value = rs.getString(columnToReturn);
	        	}
	        }
	        catch (SQLException sqlEx) {
	        	System.out.println("Caught SQL Exception: " + sqlEx.toString());
	        }
	        
	        // now close the statement and connection if they exist
	        if (pstmt != null) {
	            try {
	                 pstmt.close();
	            }
	            catch (SQLException sqlEx) {
	                System.out.println("Could not close: " + sqlEx.toString());
	            }
	        }
	        if (conn != null) {
	        	try {
	        		conn.close();
	        	}
	        	catch (SQLException sqlEx) {
	        		System.out.println("Could not close: " + sqlEx.toString());
	             }
	        }
	        // now return the result set
	        return value;
	  } // end executeQueryReturnString
	 
	  // method to execute an update
	  private void executeUpdate(String updateString, String database) {
		  String DBUrl = "jdbc:mysql:///" + database + "";
	      Connection conn = null;
	      PreparedStatement pstmt = null;

		  // try to make a JDBC connection to the database and retrieve some data
	      try {
              	//create a new instance of the mysql driver
	    	  	try {
	    	  		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	            }
	            catch (Exception Ex) {
	                System.out.println("Unable to Load Driver: " + Ex.toString());
	            }
	            
				// get a new connection object 
	            conn = DriverManager.getConnection(DBUrl);
	            
	            // prepare the MySQL update string
	            pstmt = conn.prepareStatement(updateString);

	            // and execute the update
				pstmt.executeUpdate();
	      	}
			catch (SQLException sqlEx) {
	        	System.out.println("Caught SQL Exception: " + sqlEx.toString());
	        }
		       
			// now close the statement and connection if they exist
		    if (pstmt != null) {
		    	try {
		                 pstmt.close();
		            }
		            catch (SQLException sqlEx) {
		                System.out.println("Could not close: " + sqlEx.toString());
		            }
		    }
		    if (conn != null) {
		       	try {
		       		conn.close();
		       	}
		       	catch (SQLException sqlEx) {
	        		System.out.println("Could not close: " + sqlEx.toString());
	        	}
		    }
	  } // end executeUpdate
	  
} // end class DBConnect
