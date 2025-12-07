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
			"Move North",
			"Move East",
			"Move West",
			"Move South",
			"Inspect Crypt Door"
			));
	
	//North garden actions
	public static Supplier<List<String>> northGardensActions = () -> 
		new ArrayList<>(List.of(
			"Move South",
			"Inspect Engraved Rock",
			"Inspect Pedestal",
			"Pick up Wheat Totem"
			));
	
	//West garden actions
	public static Supplier<List<String>> westGardensActions = () -> 
		new ArrayList<>(List.of(
			"Move East",
			"Inspect Engraved Rock",
			"Inspect Pedestal",
			"Pick up Lumber Totem"
			));
	
	//East garden actions
	public static Supplier<List<String>> eastGardensActions = () -> 
		new ArrayList<>(List.of(
			"Move West",
			"Inspect Engraved Rock",
			"Inspect Pedestal",
			"Pick up Sword Totem"
			));
	
	//South garden actions
	public static Supplier<List<String>> southGardensActions = () -> 
		new ArrayList<>(List.of(
			"Move North",
			"Inspect Engraved Rock",
			"Inspect Pedestal",
			"Pick up Fish Totem"
			));
	
	//Inner crypt entrance actions
	public static Supplier<List<String>> innerCryptEntranceActions = () -> 
		new ArrayList<>(List.of(
			"Move North",
			"Grab Torch",
			"Exit Crypt"
			));
	
	//Dark room (A) actions
	public static Supplier<List<String>> darkCellAActions = () -> 
		new ArrayList<>(List.of(
			"Move East",
			"Move South",
			"Pick up Health Potion"
			));

	//Dark room (B) actions
	public static Supplier<List<String>> darkCellBActions = () -> 
		new ArrayList<>(List.of(
			"Move North",
			"Move East",
			"Move South",
			"Move West"
			));
	
	//Dark room (C) actions
	public static Supplier<List<String>> darkCellCActions = () -> 
		new ArrayList<>(List.of(
			"Move South",
			"Move West"
			));
	
	//Dark room (D) actions
	public static Supplier<List<String>> darkCellDActions = () -> 
		new ArrayList<>(List.of(
			"Move North",
			"Move East"
			));
	
	//Dark room (E) actions
	public static Supplier<List<String>> darkCellEActions = () -> 
		new ArrayList<>(List.of(
			"Move North",
			"Move East",
			"Move South",
			"Move West"
			));
	
	//Dark room (F) actions
	public static Supplier<List<String>> darkCellFActions = () -> 
		new ArrayList<>(List.of(
			"Move North",
			"Move West",
			"Pick up Health Potion"
			));
	
	//Crypt hallway actions
	public static Supplier<List<String>> cryptHallwayActions = () -> 
	new ArrayList<>(List.of(
			"Move North",
			"Move South"
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
			"Take Artifact",
			"Exit Room"
			));
}
