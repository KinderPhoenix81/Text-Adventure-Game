package textAdventure;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for managing interaction with the database
 */
//Holds schema and inserts for database
public class Database {

	/**
	 * Fields for the database instance
	 * 
	 * log: logger for logging
	 */
	final static Logger log = LogManager.getLogger("LoggingExample");
	
	/**
	 * Method to run database creation
	 * 
	 * @param items Array of items in the game
	 * @param interactables Array of interactable points of interest in the game
	 * @param rooms Array of rooms in the game
	 * @throws SQLException if a SQL statement fails to execute
	 */
	public static void createGameDatabase(ArrayList<Item> items, ArrayList<BaseInteractable> interactables, ArrayList<Room> rooms) {
		
		/*
		 * Try catch block for database transactions
		 * 
		 * 6.1: Use of Try-Catch Block
		 */
		
		//Make a connection and database
		EmbeddedDataSource ds = new EmbeddedDataSource();
		ds.setDatabaseName("Game_Database");
		ds.setCreateDatabase("create");
		
		//Try with resources block for database
		try (Connection conn = ds.getConnection()) {
			//Drop existing tables
			unloadTables(conn);
			
			//Create statements for each table
			Statement createItemSchema = conn.createStatement();
			Statement createRoomSchema = conn.createStatement();
			Statement createInteractableSchema = conn.createStatement();
			
			//Assign updates to those statements
			createItemSchema.executeUpdate("CREATE TABLE Items (id INT PRIMARY KEY, name varchar(50), description varchar(250), lore varchar(250), action varchar(100))");
			createRoomSchema.executeUpdate("CREATE TABLE Rooms (id INT PRIMARY KEY, name varchar(50), description varchar(350))");
			createInteractableSchema.executeUpdate("CREATE TABLE Interactables (id INT PRIMARY KEY, name varchar(50), description varchar(250), lore varchar(250), type varchar(50))");
			
			//Log progress
			log.info("Database created successfully!");
			
			//Create entries for each table
			createItemEntries(conn);
			createInteractableEntries(conn);
			createRoomEntries(conn);
			
			//Log progress
			log.info("Objects properly instantiated!");
			
			//Load each tables data, and create the necessary objects
			loadRooms(conn, rooms);
			loadItems(conn, items);
			loadInteractables(conn, interactables);
			
			//Log progress
			log.info("Game objects properly loaded!");
			
			//Map items, interactables, and actions to specific rooms
			mapItems(rooms, items);
			mapInteractables(rooms, interactables);
			mapRoomActions(rooms);
			mapRoomExits(rooms);
			mapRoomConditions(rooms);
			mapPedestalItems(interactables);
			
			//Log progress
			log.info("Game objects properly mapped!");
			
			//Sorting lists based off id (default sorting using comparable interface)
			rooms.sort(null);
			interactables.sort(null);
			
			//Using a comparator to sort by name (custom sorting by a field / property defined by us)
			items.sort(Item.BY_ID);
			
			//Log progress
			log.info("Game objects sorted!");
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue initializing the database, please try again.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Methods to create entries in item table
	 * 
	 * @param conn Database connection
	 * @throws SQLException if a SQL statement fails to execute
	 */
	//Item entries
	public static void createItemEntries(Connection conn) {

		//Try catch block for database transactions
		try {
			//Use the connection to make some statements
			Statement itemEntryStatement = conn.createStatement();
			itemEntryStatement.executeUpdate("Insert Into Items (id, name, description, lore, action) "
					+ "Values (1, 'Wheat Totem', 'A carved wooden totem depicting a few stalks of wheat.', 'Garden Totem Puzzle Item', 'Inspect'), "
					+ "(2, 'Lumber Totem', 'A carved wooden totem depicting a tree.', 'Garden Totem Puzzle Item', 'Inspect'), "
					+ "(3, 'Sword Totem', 'A carved wooden totem depicting a sword.', 'Garden Totem Puzzle Item', 'Inspect'), "
					+ "(4, 'Fish Totem', 'A carved wooden totem depicting a fish.', 'Garden Totem Puzzle Item', 'Inspect'), "
					+ "(5, 'Torch', 'A standard torch, complete with a sturdy wooden handle and a ball of cloth on the end.', 'Source of Light', 'Use'), "
					+ "(6, 'Haunted Vase', 'A vase, ornately painted and embedded with gemstones. It feels empty and full at the same time. This is undoubtedly the source of the haunted feeling in this room. Holding it compels you to open it for a reason you’re unsure of.', 'Ending Item', 'Open'), "
					+ "(7, 'Golden Artifact', 'A small golden statue. A large ruby is set in the center. It feels special to hold.', 'Ending Item', 'Inspect') ");
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue creating database items, please try again.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Methods to create entries in interactable table
	 * 
	 * @param conn Database connection string
	 * @throws SQLException if a SQL statement fails to execute
	 */
	//Interactable entries
	public static void createInteractableEntries(Connection conn) {

		//Try catch block for database transactions
		try {
			//Use the connection to make some statements
			Statement interactableEntryStatement = conn.createStatement();
			interactableEntryStatement.executeUpdate("Insert Into Interactables (id, name, description, lore, type) "
					+ "Values (1, 'Crypt Door', 'CryptDoorOuter', 'Crypt Door- Outer', 'Door'), "
					+ "(2, 'North Pedestal', 'NorthPedestal', 'A stone pedestal. There is a slot on the top to hold a totem.', 'Pedestal'), "
					+ "(3, 'North Tablet', 'NorthTablet', 'Gives the Garden Totem Puzzle', 'Engraved-Rock'), "
					+ "(4, 'West Pedestal', 'WestPedestal', 'A stone pedestal. There is a slot on the top to hold a totem.', 'Pedestal'), "
					+ "(5, 'West Tablet', 'WestTablet', 'Gives the Garden Totem Puzzle', 'Engraved-Rock'), "
					+ "(6, 'East Pedestal', 'EastPedestal', 'A stone pedestal. There is a slot on the top to hold a totem.', 'Pedestal'), "
					+ "(7, 'East Tablet', 'EastTablet', 'Gives the Garden Totem Puzzle', 'Engraved-Rock'), "
					+ "(8, 'South Pedestal', 'SouthPedestal', 'A stone pedestal. There is a slot on the top to hold a totem.', 'Pedestal'), "
					+ "(9, 'South Tablet', 'SouthTablet', 'Gives the Garden Totem Puzzle', 'Engraved-Rock'), "
					+ "(10, 'Crypt Door', 'CryptDoorInner', 'Crypt Door- Inner', 'Door'), "
					+ "(11, 'Artifact Door', 'ArtifactDoor', 'Artifact Door', 'Door'), "
					+ "(12, 'Spirit Door', 'SpiritDoor', 'Spirit Door', 'Door')");
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue creating database interactables, please try again.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Methods to create entries in room table
	 * 
	 * @param conn Database connection
	 * @throws SQLException if a SQL statement fails to execute
	 */
	//Room entries
	public static void createRoomEntries(Connection conn) {

		//Try catch block for database transactions
		try {
			//Use the connection to make some statements
			Statement roomEntryStatement = conn.createStatement();
			roomEntryStatement.executeUpdate("Insert Into Rooms (id, name, description) "
					+ "Values (1, 'Outer Garden Center', 'CenterGarden'), "
					+ "(2, 'North Garden', 'NorthGarden'), "
					+ "(3, 'West Garden','WestGarden'), "
					+ "(4, 'East Garden', 'EastGarden'), "
					+ "(5, 'South Garden', 'SouthGarden' ), "
					+ "(6, 'Crypt Entrance', 'CryptEntrance' ), "
					+ "(7, 'Dark Room', 'DarkRoom' ), "
					+ "(8, 'Dark Room', 'DarkRoom' ), "
					+ "(9, 'Dark Room', 'DarkRoom' ), "
					+ "(10, 'Dark Room', 'DarkRoom' ), "
					+ "(11, 'Dark Room', 'DarkRoom' ), "
					+ "(12, 'Dark Room', 'DarkRoom' ), "
					+ "(13, 'Crypt Hallway', 'CryptHallway' ), "
					+ "(14, 'Split Hallway', 'SplitHallway'), "
					+ "(15, 'Spirit Room', 'SpiritRoom'), "
					+ "(16, 'Artifact Room', 'TreasureRoom')");
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue creating database rooms, please try again.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Loader method for the database to creating room objects in Java
	 * 
	 * @param conn Database connection
	 * @param roomList Array list of rooms
	 * @throws SQLException if a SQL statement fails to execute
	 */
	//Load rooms
	public static void loadRooms(Connection conn, ArrayList<Room> roomList) {
		
		//Try-catch
		try {
			//Use connection
			Statement loadRoom = conn.createStatement();
			ResultSet rooms = loadRoom.executeQuery("SELECT id, name, description FROM Rooms");
			
			//While there are rooms in the result set
			while(rooms.next()) {
				//Assign room fields to variables and create a room object
				int id = rooms.getInt("id");
				String name = rooms.getString("name");
				String desc = rooms.getString("description");
				
				Room room = new Room(name, desc, id);
				
				//Add the room to the list
				roomList.add(room);
			}
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue loading database rooms, please try again.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Loader method for the database to create item objects in Java
	 * 
	 * @param conn Database connection
	 * @param itemList Array list of items
	 * @throws SQLException if a SQL statement fails to execute
	 */
	//Load items
	public static void loadItems(Connection conn, ArrayList<Item> itemList) {
		
		//Try-catch
		try {
			//Use connection
			Statement loadItem = conn.createStatement();
			ResultSet items = loadItem.executeQuery("SELECT id, name, description, lore, action FROM Items");
			
			//While there are rooms in the result set
			while(items.next()) {
				//Assign room fields to variables and create a room object
				int id = items.getInt("id");
				String name = items.getString("name");
				String desc = items.getString("description");
				String lore = items.getString("lore");
				String action = items.getString("action");
				
				Item item = new Item(name, id, desc, lore, action);
				
				//Add the room to the list
				itemList.add(item);
			}
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue loading database items, please try again.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Loader method for the database to create item objects in Java
	 * 
	 * @param conn Database connection
	 * @param interactableList Array list of interactable points of interest
	 * @throws SQLException if a SQL statement fails to execute
	 * @throws InvalidInteractableTypeException if an interactable type failed to load into the database
	 */
	//Load interactables
	public static void loadInteractables(Connection conn, ArrayList<BaseInteractable> interactableList) {
		
		//Try-catch
		try {
			//Use connection
			Statement loadInter = conn.createStatement();
			ResultSet interactables = loadInter.executeQuery("SELECT id, name, description, lore, type FROM Interactables");
			
			//While there are rooms in the result set
			while(interactables.next()) {
				//Assign room fields to variables and create a room object
				int id = interactables.getInt("id");
				String name = interactables.getString("name");
				String desc = interactables.getString("description");
				String lore = interactables.getString("lore");
				String type = interactables.getString("type");
				
				/*
				 * Use a function to create a new interactable based on the type
				 * 
				 * 4.2: Functional Interface (Function)
				 */
				Function<String, BaseInteractable> createInteractable = interactableType -> {
				return 	type.toLowerCase().equals("door") ? new Door(id, name, desc, lore) :
					   	type.toLowerCase().equals("pedestal") ? new Pedestal(id, name, desc, lore) :
					   	type.toLowerCase().equals("engraved-rock") ? new EngravedRock(id, name, desc, lore) :
					   	null;
			};
			
			//Add the new interactable to the list by using the function above
			BaseInteractable newInteractable = createInteractable.apply(type);
			
			//Check if the interactable was applied properly
			if(newInteractable == null) {
				/*
				 * Throw an error
				 * 
				 * 6.3: Use Custom Exception
				 */
				throw new InvalidInteractableTypeException(type);
			} else {
				//Add to list
				interactableList.add(newInteractable);
			}

		}
			
		} catch (SQLException | InvalidInteractableTypeException e) {
			//Print errors
			log.error("There seems to have been an issue loading database interactables, please try again.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Drop tables method
	 * 
	 * @param conn Database connection
	 * @throws SQLException if a SQL statement fails to execute
	 */
	private static void unloadTables(Connection conn) {
		//Try-catch block
		try {
			//Drop the tables from the database
			Statement dropTableStatement = conn.createStatement();
			dropTableStatement.executeUpdate("DROP TABLE APP.ITEMS");
			dropTableStatement.executeUpdate("DROP TABLE APP.INTERACTABLES");
			dropTableStatement.executeUpdate("DROP TABLE APP.ROOMS");
			
		} catch (SQLException e) {
			//Log out a message 
			log.warn("There was no table to drop");
		}
		
		log.info("Existing tables have been dropped.");
	}
	
	/**
	 * Mapping items to rooms they are found in
	 * 
	 * @param rooms Array list of rooms
	 * @param items Array list of items
	 */
	//Map items
	public static void mapItems(ArrayList<Room> rooms, ArrayList<Item> items) {
		//Loop through the list of rooms, when it gets to a specific room, add an item
		for(Room room : rooms) {
			//Item list to store items
			ArrayList<Item> currentItems = new ArrayList<Item>();
			
			//Getting the room ID
			int roomID = room.getID();
			
			//Start going through the rooms to load items
			switch(roomID) {
			//Wheat totem in north gardens
			case 2: 
				currentItems.add(items.get(0));
				break;
			//Lumber totem in west gardens
			case 3:
				currentItems.add(items.get(1));
				break;
			//Sword totem in east gardens
			case 4:
				currentItems.add(items.get(2));
				break;
			//Fish totem in south gardens
			case 5:
				currentItems.add(items.get(3));
				break;
			//Torch in inner crypt entrance
			case 6:
				currentItems.add(items.get(4));
				break;
			//Haunted vase in spirit room
			case 15:
				currentItems.add(items.get(5));
				break;
			//Golden artifact in artifact room
			case 16:
				currentItems.add(items.get(6));
			}
			
			//Add the item list to the room
			room.setItemList(currentItems);
		}
	}
	
	/**
	 * Mapping interactables to rooms they are found in
	 * 
	 * @param rooms Array list of rooms
	 * @param interactables Array list of interactables
	 */
	//Map interactables
	public static void mapInteractables(ArrayList<Room> rooms, ArrayList<BaseInteractable> interactables) {
		//Loop through list of rooms, when it gets to a specific room, add interactables
		for(Room room : rooms) {
			//Interactable list to hold interactables for a room
			ArrayList<BaseInteractable> roomInteractables = new ArrayList<BaseInteractable>();
			
			//Getting room id
			int roomID = room.getID();
			
			//Go through the rooms to load interactables
			switch(roomID) {
			//Crypt door to outer crypt entrance
			case 1:
				roomInteractables.add(interactables.get(0));
				break;
			//Pedestal and rock to north gardens
			case 2:
				roomInteractables.add(interactables.get(1));
				roomInteractables.add(interactables.get(2));
				break;
			//Pedestal and rock to west gardens
			case 3:
				roomInteractables.add(interactables.get(3));
				roomInteractables.add(interactables.get(4));
				break;
			//Pedestal and rock to east gardens
			case 4:
				roomInteractables.add(interactables.get(5));
				roomInteractables.add(interactables.get(6));
				break;
			//Pedestal and rock to south gardens
			case 5:
				roomInteractables.add(interactables.get(7));
				roomInteractables.add(interactables.get(8));
				break;
			//Crypt door to inner crypt entrance
			case 6:
				roomInteractables.add(interactables.get(9));
				break;
			//Artifact & Spirit doors to crypt split hallway
			case 14:
				roomInteractables.add(interactables.get(10));
				roomInteractables.add(interactables.get(11));
				break;
			}
			
			//Add the interactable(s) to the room
			room.setInteractableList(roomInteractables);
		}
	}
	
	/**
	 * Mapping method for setting room exits
	 * 
	 * @param rooms Array list of rooms
	 */
	public static void mapRoomExits(ArrayList<Room> rooms) {
		Room outerGarden = rooms.get(0);
		Room northGarden = rooms.get(1);
		Room westGarden = rooms.get(2);
		Room eastGarden = rooms.get(3);
		Room southGarden = rooms.get(4);
		Room cryptEntrance = rooms.get(5);
		Room darkRoom1 = rooms.get(6);
		Room darkRoom2 = rooms.get(7);
		Room darkRoom3 = rooms.get(8);
		Room darkRoom4 = rooms.get(9);
		Room darkRoom5 = rooms.get(10);
		Room darkRoom6 = rooms.get(11);
		Room cryptHallway = rooms.get(12);
		Room splitHallway = rooms.get(13);
		Room spiritRoom = rooms.get(14);
		Room artifactRoom = rooms.get(15);

		
		//outer garden exits
		outerGarden.setExit(Direction.NORTH, northGarden);
		outerGarden.setExit(Direction.WEST, westGarden);
		outerGarden.setExit(Direction.EAST, eastGarden);
		outerGarden.setExit(Direction.SOUTH, southGarden);
		outerGarden.setExit(Direction.DOWN, cryptEntrance);
		
		//north garden exits
		northGarden.setExit(Direction.SOUTH, outerGarden);
		
		//west garden exits
		westGarden.setExit(Direction.EAST, outerGarden);
		
		//east garden exits
		eastGarden.setExit(Direction.WEST, outerGarden);
		
		//south garden exits
		southGarden.setExit(Direction.NORTH, outerGarden);	
		
		//crypt entrance
		cryptEntrance.setExit(Direction.NORTH, darkRoom1);
		
		//darkrooms
		darkRoom1.setExit(Direction.SOUTH, cryptEntrance);
		darkRoom1.setExit(Direction.EAST, artifactRoom);
	}
	
	/**
	 * Mapping method for setting room special items
	 * 
	 * @param rooms Array list of rooms
	 */
	public static void mapRoomConditions(ArrayList<Room> rooms) {
		Room outerGarden = rooms.get(0);
		Room northGarden = rooms.get(1);
		Room westGarden = rooms.get(2);
		Room eastGarden = rooms.get(3);
		Room southGarden = rooms.get(4);
		Room cryptEntrance = rooms.get(5);
		
		//outer garden exits
		outerGarden.setSpecialConditionVerb("Completed");
		outerGarden.setSpecialConditionNoun("Totem Quest");
		outerGarden.setSpecialAction("Enter Crypt Door");
		
		//north garden exits
		northGarden.setSpecialConditionVerb("Has");
		northGarden.setSpecialConditionNoun("Fish Totem");
		northGarden.setSpecialAction("Use Fish Totem");
		
		//west garden exits
		westGarden.setSpecialConditionVerb("Has");
		westGarden.setSpecialConditionNoun("Wheat Totem");
		westGarden.setSpecialAction("Use Wheat Totem");
		
		//east garden exits
		eastGarden.setSpecialConditionVerb("Has");
		eastGarden.setSpecialConditionNoun("Lumber Totem");
		eastGarden.setSpecialAction("Use Lumber Totem");
		
		//south garden exits
		southGarden.setSpecialConditionVerb("Has");
		southGarden.setSpecialConditionNoun("Sword Totem");
		southGarden.setSpecialAction("Use Sword Totem");
	}
	
	/**
	 * Map the actions that can be performed in each room
	 * 
	 * @param rooms Array list of rooms
	 */
	//Map room actions
	public static void mapRoomActions(ArrayList<Room> rooms) {
		//Map room actions to each room
		rooms.get(0).setRoomActions((ArrayList<String>) RoomActionSupplier.cryptEntranceActions.get());
		rooms.get(1).setRoomActions((ArrayList<String>) RoomActionSupplier.northGardensActions.get());
		rooms.get(2).setRoomActions((ArrayList<String>) RoomActionSupplier.westGardensActions.get());
		rooms.get(3).setRoomActions((ArrayList<String>) RoomActionSupplier.eastGardensActions.get());
		rooms.get(4).setRoomActions((ArrayList<String>) RoomActionSupplier.southGardensActions.get());
		rooms.get(5).setRoomActions((ArrayList<String>) RoomActionSupplier.innerCryptEntranceActions.get());
		rooms.get(6).setRoomActions((ArrayList<String>) RoomActionSupplier.darkCellAActions.get());
		rooms.get(7).setRoomActions((ArrayList<String>) RoomActionSupplier.darkCellBActions.get());
		rooms.get(8).setRoomActions((ArrayList<String>) RoomActionSupplier.darkCellCActions.get());
		rooms.get(9).setRoomActions((ArrayList<String>) RoomActionSupplier.darkCellDActions.get());
		rooms.get(10).setRoomActions((ArrayList<String>) RoomActionSupplier.darkCellEActions.get());
		rooms.get(11).setRoomActions((ArrayList<String>) RoomActionSupplier.darkCellFActions.get());
		rooms.get(12).setRoomActions((ArrayList<String>) RoomActionSupplier.cryptHallwayActions.get());
		rooms.get(13).setRoomActions((ArrayList<String>) RoomActionSupplier.cryptSplitHallwayActions.get());
		rooms.get(14).setRoomActions((ArrayList<String>) RoomActionSupplier.spiritRoomActions.get());
		rooms.get(15).setRoomActions((ArrayList<String>) RoomActionSupplier.artifactRoomActions.get());
	}
	
	/**
	 * Set each pedestal's correct totem for checking puzzle completion later
	 * 
	 * @param interactables Array list of interactables
	 */
	//Map pedestal items
	public static void mapPedestalItems(ArrayList<BaseInteractable> interactables) {
		/*
		 * Make a stream for the pedestals
		 * 
		 * 4.5: Use of Stream Pipeline
		 */
		interactables.stream()
		
		//Filter pedestals from interactables
		.filter(i -> i instanceof Pedestal)
		
		//Cast each interactable as a pedestal
		.map(i -> (Pedestal) i)
		
		/*
		 * Loop through pedestals for correct id
		 * 
		 * 3.5: Use of ForEach Statement
		 * 4.1: Use of variable in Lambda Expression
		 */
		.forEach(p -> {
			//North pedestal for fish
			if (p.getID() == 2) {
				p.setPedestalCorrectItemID(4);
			//West pedestal for wheat
			} else if (p.getID() == 4) {
				p.setPedestalCorrectItemID(1);
			//East pedestal for lumber
			} else if (p.getID() == 6) {
				p.setPedestalCorrectItemID(2);
			//South pedestal for sword
			} else if (p.getID() == 8) {
				p.setPedestalCorrectItemID(3);
			}
		}
		);
	}
}
