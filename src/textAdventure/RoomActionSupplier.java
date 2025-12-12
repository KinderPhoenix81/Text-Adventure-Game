package textAdventure;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RoomActionSupplier {
	/*
	 * Suppliers for each room's actions
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
	
	//North garden actions
	public static Supplier<List<String>> northGardensActions = () -> 
		new ArrayList<>(List.of(
			"Go South",
			"Examine Engraved Rock",
			"Examine Pedestal",
			"Grab Wheat Totem"
			));
	
	//West garden actions
	public static Supplier<List<String>> westGardensActions = () -> 
		new ArrayList<>(List.of(
			"Go East",
			"Examine Engraved Rock",
			"Examine Pedestal",
			"Grab Lumber Totem"
			));
	
	//East garden actions
	public static Supplier<List<String>> eastGardensActions = () -> 
		new ArrayList<>(List.of(
			"Go West",
			"Examine Engraved Rock",
			"Examine Pedestal",
			"Grab Sword Totem"
			));
	
	//South garden actions
	public static Supplier<List<String>> southGardensActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Examine Engraved Rock",
			"Examine Pedestal",
			"Grab Fish Totem"
			));
	
	//Inner crypt entrance actions
	public static Supplier<List<String>> innerCryptEntranceActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Grab Torch",
			"Exit Crypt"
			));
	
	//Dark room (A) actions
	public static Supplier<List<String>> darkCellAActions = () -> 
		new ArrayList<>(List.of(
			"Go East",
			"Go South"
			));

	//Dark room (B) actions
	public static Supplier<List<String>> darkCellBActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Go East",
			"Go South",
			"Go West"
			));
	
	//Dark room (C) actions
	public static Supplier<List<String>> darkCellCActions = () -> 
		new ArrayList<>(List.of(
			"Go South",
			"Go West"
			));
	
	//Dark room (D) actions
	public static Supplier<List<String>> darkCellDActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Go East"
			));
	
	//Dark room (E) actions
	public static Supplier<List<String>> darkCellEActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Go East",
			"Go South",
			"Go West"
			));
	
	//Dark room (F) actions
	public static Supplier<List<String>> darkCellFActions = () -> 
		new ArrayList<>(List.of(
			"Go North",
			"Go West"
			));
	
	//Crypt hallway actions
	public static Supplier<List<String>> cryptHallwayActions = () -> 
	new ArrayList<>(List.of(
			"Go North",
			"Go South"
			));
	
	//Crypt split hallway actions
	public static Supplier<List<String>> cryptSplitHallwayActions = () -> 
	new ArrayList<>(List.of(
			"Open Gilded Door",
			"Open Silver Door"
			));
	
	//Spirit room actions
	public static Supplier<List<String>> spiritRoomActions = () -> 
	new ArrayList<>(List.of(
			"Open Vase",
			"Exit Room"
			));
	
	//Artifact room actions
	public static Supplier<List<String>> artifactRoomActions = () -> 
	new ArrayList<>(List.of(
			"Grab Artifact",
			"Exit Room"
			));
}
