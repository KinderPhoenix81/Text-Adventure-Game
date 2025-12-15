package textAdventure;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Class for supplying a room's actions
 */
public class RoomActionSupplier {
	/*
	 * Suppliers for each room's actions
	 * 
	 * 2.1: Use of Lambda Expressions
	 * 4.2: Use of Functional Interface (Supplier)
	 */
	
	/**
	 * Actions for crypt entrance
	 */
	//Crypt entrance actions
	public static Supplier<List<String>> cryptEntranceActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Go East",
			"Go West",
			"Go South",
			"Examine Crypt Door"
			));
	
		/**
		 * Actions for north garden
		 */
	//North garden actions
	public static Supplier<List<String>> northGardensActions = () -> 
		new ArrayList<>(List.of(
			"Go South",
			"Examine Engraved Rock",
			"Examine Pedestal",
			"Grab Wheat Totem"
			));
	
		/**
		 * Actions for west garden
		 */
	//West garden actions
	public static Supplier<List<String>> westGardensActions = () -> 
		new ArrayList<>(List.of(
			"Go East",
			"Examine Engraved Rock",
			"Examine Pedestal",
			"Grab Lumber Totem"
			));
	
		/**
		 * Actions for east garden
		 */
	//East garden actions
	public static Supplier<List<String>> eastGardensActions = () -> 
		new ArrayList<>(List.of(
			"Go West",
			"Examine Engraved Rock",
			"Examine Pedestal",
			"Grab Sword Totem"
			));
	
		/**
		 * Actions for south garden
		 */
	//South garden actions
	public static Supplier<List<String>> southGardensActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Examine Engraved Rock",
			"Examine Pedestal",
			"Grab Fish Totem"
			));
	
		/**
		 * Actions for inner crypt
		 */
	//Inner crypt entrance actions
	public static Supplier<List<String>> innerCryptEntranceActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Grab Torch",
			"Leave Crypt"
			));
	
		/**
		 * Actions for dark room section A
		 */
	//Dark room (A) actions
	public static Supplier<List<String>> darkCellAActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Go South"
			));

		/**
		 * Actions for dark room section B
		 */
	//Dark room (B) actions
	public static Supplier<List<String>> darkCellBActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Go South"
			));
	
		/**
		 * Actions for crypt hallway
		 */
	//Crypt hallway actions
	public static Supplier<List<String>> cryptHallwayActions = () -> 
	new ArrayList<>(List.of(
			"Enter Spirit Room",
			"Enter Artifact Room"
			));
	
	
	/**
	 * Actions for spirit room
	 */
	//Spirit room actions
	public static Supplier<List<String>> spiritRoomActions = () -> 
	new ArrayList<>(List.of(
			"Open Vase",
			"Leave Room"
			));
	
	/**
	 * Actions for artifact room
	 */
	//Artifact room actions
	public static Supplier<List<String>> artifactRoomActions = () -> 
	new ArrayList<>(List.of(
			"Grab Artifact",
			"Leave Room"
			));
}
