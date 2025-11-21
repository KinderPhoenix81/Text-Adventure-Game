package textAdventure;
import java.sql.*;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Holds schema and inserts for database
public class Database {

	/*
	 * Fields for the database instance
	 */
	private static String databaseURL;
	final static Logger log = LogManager.getLogger("LoggingExample");
	
	/*
	 * Method to run database creation
	 */
	public static void createGameDatabaseSchema() {
		
		//Try catch block for database transactions
		try {
			//Make a connection
			EmbeddedDataSource ds = new EmbeddedDataSource();
			ds.setDatabaseName("Game_Database");
			Connection conn = ds.getConnection();
			
			//Create statments for each table
			Statement createItemSchema = conn.createStatement();
			Statement createRoomSchema = conn.createStatement();
			Statement createInteractableSchema = conn.createStatement();
			
			//Assing updates to those statments
			createItemSchema.executeUpdate("CREATE TABLE Items (id INT PRIMARY KEY, name varchar(50), description varchar(250), lore varchar(250), action varchar(100))");
			createRoomSchema.executeUpdate("CREATE TABLE Rooms (id INT PRIMARY KEY, name varchar(50), description varchar(250))");
			createInteractableSchema.executeUpdate("CREATE TABLE Interactables (id INT PRIMARY KEY, name varchar(50), description varchar(250), lore varchar(250), type varchar(50))");
			
			//Log successful database creation
			log.info("Database created successfully!");
			
			//Create entries for each table
			createItemEntries(conn);
			createInteractableEntries(conn);
			createRoomEntries(conn);
			
			//Load each tables data, and create the necessary objects
			
			//Close connection
			conn.close();
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue with the derby database, please try again.");
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Methods to create entries in each table
	 */
	//Item entries
	public static void createItemEntries(Connection conn) {

		//Try catch block for database transactions
		try {
			//Use the connection to make some statements
			Statement itemEntryStatement = conn.createStatement();
			itemEntryStatement.executeUpdate("SQL INSERT STATEMENTS FOR ITEMS");
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue with the derby database, please try again.");
			e.printStackTrace();
		}
		
	}
	
	//Interactable entries
	public static void createInteractableEntries(Connection conn) {

		//Try catch block for database transactions
		try {
			//Use the connection to make some statements
			Statement interactableEntryStatement = conn.createStatement();
			interactableEntryStatement.executeUpdate("SQL INSERT STATEMENTS FOR INTERACTABLES");
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue with the derby database, please try again.");
			e.printStackTrace();
		}
		
	}
	
	//Room entries
	public static void createRoomEntries(Connection conn) {

		//Try catch block for database transactions
		try {
			//Use the connection to make some statements
			Statement roomEntryStatement = conn.createStatement();
			roomEntryStatement.executeUpdate("SQL INSERT STATEMENTS FOR ROOMS");
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue with the derby database, please try again.");
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Loader methods for the database into creating objects in Java
	 */
	
	
	/*
	 * Getters and setters for the database url
	 */
	public static String getURL() {
		return databaseURL;
	}
	
	public static void setURL(String url) {
		databaseURL = url;
	}
}
