package textAdventure;
import java.sql.*;

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
		//Set the database url
		setURL("jdbc:derby:GameDatabase;create=true");
		
		//Try catch block for database transactions
		try {
			//Make a connection
			Connection conn = DriverManager.getConnection(databaseURL);
			
			//Create statments for each table
			Statement createItemSchema = conn.createStatement();
			Statement createRoomSchema = conn.createStatement();
			Statement createRoomActionsSchema = conn.createStatement();
			Statement createRoomItemsSchema = conn.createStatement();
			Statement createRoomInteractablesSchema = conn.createStatement();
			Statement createInteractableSchema = conn.createStatement();
			
			//Assing updates to those statments
			createItemSchema.executeUpdate("CREATE TABLE Items (id INT PRIMARY KEY, name varchar(50), description varchar(250), lore varchar(250), action varchar(100))");
			createRoomSchema.executeUpdate("CREATE TABLE Rooms (id INT PRIMARY KEY, name varchar(50), description varchar(250))");
			createRoomActionsSchema.executeUpdate("CREATE TABLE RoomActions (id INT PRIMARY KEY, roomID INT, action varchar(50)");
			createRoomItemsSchema.executeUpdate("CREATE TABLE RoomItems (id INT PRIMARY KEY, roomID INT, itemID INT)");
			createRoomInteractablesSchema.executeUpdate("CREATE TABLE RoomInteractables (id INT PRIMARY KEY, roomID INT, interactableID INT)");
			createInteractableSchema.executeUpdate("CREATE TABLE Interactables (id INT PRIMARY KEY, name varchar(50), description varchar(250), lore varchar(250))");
			
			//Log successful database creation
			log.info("Database created successfully!");
			
			//Close connection
			conn.close();
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue with the derby database, please try again.");
			e.printStackTrace();
		}
		
	}
	
	
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
