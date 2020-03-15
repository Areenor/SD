package Services;

import Default_classes.Item;
import Default_classes.Location;
import Default_classes.NPC;
import Game_data.GameState;

//If locationName is empty use current location.
public class LocationUpdateService {
    private static void SetDescription(String locationName, String newDescription) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (newDescription.isEmpty()) throw new IllegalArgumentException("New description cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        location._description = newDescription;
        GameState.Locations.replace(locationName, location);
    }

    public static void SetDescription(String newDescription) {
        String currLocationName = GameState.CurrentLocation._name;
        SetDescription(currLocationName, newDescription);
        GameState.CurrentLocation = GameState.GetLocation(currLocationName);
    }

    public static void AddItem(String locationName, Item newItem) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (newItem == null) throw new IllegalArgumentException("Item to be added cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        String itemName = newItem._name;
        location._items.put(itemName, newItem);
        GameState.Locations.replace(locationName, location);
    }

    public static void AddItem(Item newItem) {
        String currLocationName = GameState.CurrentLocation._name;
        AddItem(currLocationName, newItem);
        GameState.CurrentLocation = GameState.GetLocation(currLocationName);
    }

    public static Item RemoveItem(String locationName, String itemName) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (itemName.isEmpty()) throw new IllegalArgumentException("Item name cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        if (!location._items.containsKey(itemName)) throw new IllegalArgumentException(String.format("%1$s does not contain an item named %2$s.", location._name, itemName));
        Item targetItem = location._items.get(itemName);
        location._items.remove(itemName);
        GameState.Locations.replace(locationName, location);
        return targetItem;
    }

    public static Item RemoveItem(String itemName) {
        String currLocationName = GameState.CurrentLocation._name;
        Item deletedItem = RemoveItem(currLocationName, itemName);
        GameState.CurrentLocation = GameState.GetLocation(currLocationName);
        return deletedItem;
    }

    public static void AddNpc(String locationName, NPC newNpc) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (newNpc == null) throw new IllegalArgumentException("NPC to be added cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        String npcName = newNpc._name;
        location._NPCs.put(npcName, newNpc);
        GameState.Locations.replace(locationName, location);
    }

    public static void AddNpc( NPC newNpc) {
        String currLocationName = GameState.CurrentLocation._name;
        AddNpc(currLocationName, newNpc);
        GameState.CurrentLocation = GameState.GetLocation(currLocationName);
    }

    public static NPC RemoveNpc(String locationName, String npcName) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (npcName.isEmpty()) throw new IllegalArgumentException("NPC name cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        if (!location._NPCs.containsKey(npcName)) throw new IllegalArgumentException(String.format("%1$s does not contain an NPC named %2$s.", location._name, npcName));
        NPC targetItem = location._NPCs.get(npcName);
        location._NPCs.remove(npcName);
        GameState.Locations.replace(locationName, location);
        return targetItem;
    }

    public static NPC RemoveNpc(String npcName) {
        String currLocationName = GameState.CurrentLocation._name;
        NPC deletedNpc = RemoveNpc(currLocationName, npcName);
        GameState.CurrentLocation = GameState.GetLocation(currLocationName);
        return deletedNpc;
    }


}
