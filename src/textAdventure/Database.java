package textAdventure;
import java.sql.*;
import java.util.ArrayList;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Holds schema and inserts for database
public class Database {

	/*
	 * Fields for the database instance
	 */
	final static Logger log = LogManager.getLogger("LoggingExample");
	
	/*
	 * Method to run database creation
	 */
	public static void createGameDatabase(ArrayList<Item> items, ArrayList<BaseInteractable> interactables, ArrayList<Room> rooms) {
		
		//Try catch block for database transactions
		try {
			//Make a connection and database
			EmbeddedDataSource ds = new EmbeddedDataSource();
			ds.setDatabaseName("Game_Database");
			ds.setCreateDatabase("create");
			Connection conn = ds.getConnection();
			
			//Drop existing tables
			unloadTables(conn);
			
			//Create statments for each table
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
			
			//Map items and interactables to specific rooms
			mapItems(rooms, items);
			mapInteractables(rooms, interactables);
			
			//Log progress
			log.info("Game objects properly mapped!");
			
			//Sorting lists based off id (default sorting using comparable interface)
			rooms.sort(null);
			interactables.sort(null);
			
			//Using a comparator to sort by name (custom sorting by a field / property defined by us)
			items.sort(Item.BY_ID);
			
			//Log progress
			log.info("Game objects sorted!");
			
			//Close connection
			conn.close();
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue initializing the database, please try again.");
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
			itemEntryStatement.executeUpdate("Insert Into Items (id, name, description, lore, action) "
					+ "Values (1, 'Wheat Totem', 'A carved wooden totem depicting a few stalks of wheat.', 'Garden Totem Puzzle Item', 'Inspect'), "
					+ "(2, 'Lumber Totem', 'A carved wooden totem depicting a tree.', 'Garden Totem Puzzle Item', 'Inspect'), "
					+ "(3, 'Sword Totem', 'A carved wooden totem depicting a sword.', 'Garden Totem Puzzle Item', 'Inspect'), "
					+ "(4, 'Fish Totem', 'A carved wooden totem depicting a fish.', 'Garden Totem Puzzle Item', 'Inspect'), "
					+ "(5, 'Torch', 'A standard torch, complete with a sturdy wooden handle and a ball of cloth on the end.', 'Source of Light', 'Use'), "
					+ "(6, 'Health Potion', 'A glass bottle containing red liquid. Smelling it already makes you feel better. It must be restorative.', 'Restorative Item', 'Drink'), "
					+ "(7, 'Health Potion', 'A glass bottle containing red liquid. Smelling it already makes you feel better. It must be restorative.', 'Restorative Item', 'Drink'),"
					+ "(8, 'Haunted Vase', 'A vase, ornately painted and embedded with gemstones. It feels empty and full at the same time. This is undoubtedly the source of the haunted feeling in this room. Holding it compels you to open it for a reason you’re unsure of.', 'Ending Item', 'Open'), "
					+ "(9, 'Golden Artifact', 'A small golden statue. A large ruby is set in the center. It feels special to hold.', 'Ending Item', 'Inspect') ");
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue creating database items, please try again.");
			e.printStackTrace();
		}
		
	}
	
	//Interactable entries
	public static void createInteractableEntries(Connection conn) {

		//Try catch block for database transactions
		try {
			//Use the connection to make some statements
			Statement interactableEntryStatement = conn.createStatement();
			interactableEntryStatement.executeUpdate("Insert Into Interactables (id, name, description, lore, type) "
					+ "Values (1, 'Crypt Door', 'A moss-covered stone door rises from the forest floor, half-swallowed by roots and earth, its iron handle cold with the weight of forgotten centuries.', 'Crypt Door- Outer', 'Door'), "
					+ "(2, 'North Pedestal', 'A stone pedestal. There is a wooden totem sitting on top.', 'A stone pedestal. There is a slot on the top to hold a totem.', 'Pedestal'), "
					+ "(3, 'North Tablet', 'The stone tablet holds the Great Decree of the King. It reads: May the tribe in the north provide fish', 'Gives the Garden Totem Puzzle', 'Engraved-Rock'), "
					+ "(4, 'West Pedestal', 'A stone pedestal. There is a wooden totem sitting on top.', 'A stone pedestal. There is a slot on the top to hold a totem.', 'Pedestal'), "
					+ "(5, 'West Tablet', 'The stone tablet holds the Great Decree of the King. It reads: May the tribe in the west provide crops', 'Gives the Garden Totem Puzzle', 'Engraved-Rock'), "
					+ "(6, 'East Pedestal', 'A stone pedestal. There is a wooden totem sitting on top.', 'A stone pedestal. There is a slot on the top to hold a totem.', 'Pedestal'), "
					+ "(7, 'East Tablet', 'The stone tablet holds the Great Decree of the King. It reads: May the tribe in the east provide lumber', 'Gives the Garden Totem Puzzle', 'Engraved-Rock'), "
					+ "(8, 'South Pedestal', 'A stone pedestal. There is a wooden totem sitting on top.', 'A stone pedestal. There is a slot on the top to hold a totem.', 'Pedestal'), "
					+ "(9, 'South Tablet', 'The stone tablet holds the Great Decree of the King. It reads: May the tribe in the south provide protection', 'Gives the Garden Totem Puzzle', 'Engraved-Rock'), "
					+ "(10, 'Crypt Door', 'A heavy stone door dominates the crypt’s entrance, its edges glowing faintly where moss filters through the cracks. Roots cling to the slab like rigid fingers, and the cold iron handle hangs motionless in the dim, stale air.', 'Crypt Door- Inner', 'Door'), "
					+ "(11, 'Artifact Door', 'The warm feeling entices you to choose the right door. A warmth washes over you.', 'Artifact Door', 'Door'), "
					+ "(12, 'Spirit Door', 'The strange feeling lures you to open the door. A chill runs through you', 'Spirit Door', 'Door')");
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue creating database interactables, please try again.");
			e.printStackTrace();
		}
		
	}
	
	//Room entries
	public static void createRoomEntries(Connection conn) {

		//Try catch block for database transactions
		try {
			//Use the connection to make some statements
			Statement roomEntryStatement = conn.createStatement();
			roomEntryStatement.executeUpdate("Insert Into Rooms (id, name, description) "
					+ "Values (1, 'Outer Garden Center', 'The center of King Kroz’s Garden. Known to be his favorite place, it naturally must lead to his final resting place. In each cardinal direction lies in the main areas of the garden. Right in the middle is a stone tablet nestled in the grass.'), "
					+ "(2, 'North Garden', 'The north side of the garden is filled with lots of cool-colored plants and trees. Holly berries and Poinsettias grow, and the air is noticeably chilly. A stone pedestal sits in the center.'), "
					+ "(3, 'West Garden', 'The west side of the garden has plants that are just beginning to bud. Flowers wait to bloom, and the leaves are a pleasant light green. A stone pedestal sits in the center.'), "
					+ "(4, 'East Garden', 'The east side of the garden is full of plants displaying vibrant orange, brown, yellow, and red colors. The air is cooler, but not uncomfortable. A stone pedestal sits in the center.'), "
					+ "(5, 'South Garden', 'The south side of the garden has lots of lush green plants and widely blooming flowers. The sun shines brightly and it’s comfortably warm. A stone pedestal sits in the center.' ), "
					+ "(6, 'Crypt Entrance', 'At the bottom of the stairs lies the threshold of the sunlight. Beyond lies nothing but darkness. Being underground, there is no light to be seen.' ), "
					+ "(7, 'Dark Room', 'It is impossibly dark. You can’t make out anything at all, and your eyes will not adjust. The light from the staircase does not reach this room' ), "
					+ "(8, 'Dark Room', 'It is impossibly dark. You can’t make out anything at all, and your eyes will not adjust. The light from the staircase does not reach this room' ), "
					+ "(9, 'Dark Room', 'It is impossibly dark. You can’t make out anything at all, and your eyes will not adjust. The light from the staircase does not reach this room' ), "
					+ "(10, 'Dark Room', 'It is impossibly dark. You can’t make out anything at all, and your eyes will not adjust. The light from the staircase does not reach this room' ), "
					+ "(11, 'Dark Room', 'It is impossibly dark. You can’t make out anything at all, and your eyes will not adjust. The light from the staircase does not reach this room' ), "
					+ "(12, 'Dark Room', 'It is impossibly dark. You can’t make out anything at all, and your eyes will not adjust. The light from the staircase does not reach this room' ), "
					+ "(13, 'Crypt Hallway', 'This hall seems completely normal. There’s nothing special about it. What’s special is what it leads to.' ), "
					+ "(14, 'Split Hallway', 'The hallway no longer goes forward. The only continuing paths are two doors, one on either side. The door on the left gives off a haunting feeling, like something is waiting for you. The door on the right gives off a warm feeling, like sunlight.'), "
					+ "(15, 'Spirit Room', 'In the middle of the room is a single vase on a stand. However, it feels regal and melancholic. You are drawn to the vase in a way you can’t quite explain.'), "
					+ "(16, 'Artifact Room', 'In the middle of the room is a golden artifact on a stand. A single ray of light shines through the ceiling, perfectly highlighting the artifact. The room glows with the light of this treasure you have come so far to find.')");
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue creating database rooms, please try again.");
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Loader methods for the database into creating objects in Java
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
				
				switch(type.toLowerCase()) {
				case "door":
					//Create a new door & add it to the list
					Door door = new Door(id, name, desc, lore);
					interactableList.add(door);
					break;
					
				case "pedestal":
					//Create a new pedestal & add it to the list
					Pedestal pedestal = new Pedestal(id, name, desc, lore);
					interactableList.add(pedestal);
					break;
	
				case "engraved-rock":
					//Create a new rock & add it to the list
					EngravedRock rock = new EngravedRock(id, name, desc, lore);
					interactableList.add(rock);
					break;
				}
				
			}
			
		} catch (SQLException e) {
			//Print errors
			log.error("There seems to have been an issue loading database interactables, please try again.");
			e.printStackTrace();
		}
	}
	
	/*
	 * Drop tables method
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
	
	/*
	 * Mapping methods items and interactables
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
			//Health potion in dark room- cell A
			case 7:
				currentItems.add(items.get(5));
				break;
			//Health potion in dark room- cell F
			case 12:
				currentItems.add(items.get(6));
				break;
			//Haunted vase in spirit room
			case 15:
				currentItems.add(items.get(7));
				break;
			//Golden artifact in artifact room
			case 16:
				currentItems.add(items.get(8));
			}
			
			//Add the item list to the room
			room.setItemList(currentItems);
		}
	}
	
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
			room.setInteractableList(interactables);
		}
	}
}
