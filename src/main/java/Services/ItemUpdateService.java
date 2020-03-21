package Services;

import Default_classes.Item;
import Default_classes.Location;
import Default_classes.NPC;
import Default_classes.Player;
import Game_data.GameState;

//If location name is not specified, the current location is used.
//If character name and location name are not specified the main character is used.
public class ItemUpdateService {
    public static void SetDescriptionInLocation(String locationName, String itemName, String newDescription) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("location name cannot be empty.");
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");
        if (newDescription.isEmpty()) throw new IllegalArgumentException("New description cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        Item item = location._items.get(itemName);
        item._description = newDescription;
        GameState.Locations.replace(locationName, location);
    }

    public static void SetDescriptionInLocation(String itemName, String newDescription) {
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");
        if (newDescription.isEmpty()) throw new IllegalArgumentException("New description cannot be empty.");

        Location currentLocation = GameState.MainCharacter.GetCurrentLocation();
        Item item = currentLocation._items.get(itemName);
        item._description = newDescription;
        currentLocation._items.replace(itemName, item);
    }

    public static void SetIsRetrievableInLocation(String locationName, String itemName, boolean retrievable) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("location name cannot be empty.");
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        Item item = location._items.get(itemName);
        item._isRetrievable = retrievable;
        GameState.Locations.replace(locationName, location);
    }

    public static void SetIsRetrievableInLocation(String itemName, boolean retrievable) {
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");

        Location currentLocation = GameState.MainCharacter.GetCurrentLocation();
        Item item = currentLocation._items.get(itemName);
        item._isRetrievable = retrievable;
        currentLocation._items.replace(itemName, item);
    }

    public static void SetItemDescriptionInInventory(String locationName, String characterName, String itemName, String newDescription) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("location name cannot be empty.");
        if (characterName.isEmpty()) throw new IllegalArgumentException("character name cannot be empty.");
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");
        if (newDescription.isEmpty()) throw new IllegalArgumentException("New description cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        Item item = GetCharacterItem(location, characterName, itemName);
        item._description = newDescription;
        SetCharacterItem(location, characterName, itemName, item);
        GameState.Locations.replace(locationName, location);
    }

    public static void SetItemDescriptionInInventory(String characterName, String itemName, String newDescription) {
        Player mainCharacter = GameState.MainCharacter;
        String currLocationName = mainCharacter.GetCurrentLocation().GetName();
        SetItemDescriptionInInventory(currLocationName, characterName, itemName, newDescription);
        mainCharacter.SetCurrentLocation(currLocationName);
    }

    public static void SetItemDescriptionInInventory(String itemName, String newDescription) {
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");
        if (newDescription.isEmpty()) throw new IllegalArgumentException("New description cannot be empty.");
        Item item = GetMainCharacterItem(itemName);
        item._description = newDescription;
        SetMainCharacterItem(itemName, item);
    }

    public static void SetIsRetrievableInInventory(String locationName, String characterName, String itemName, boolean retrievable) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("location name cannot be empty.");
        if (characterName.isEmpty()) throw new IllegalArgumentException("character name cannot be empty.");
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        Item item = GetCharacterItem(location, characterName, itemName);
        item._isRetrievable = retrievable;
        SetCharacterItem(location, characterName, itemName, item);
        GameState.Locations.replace(locationName, location);
    }

    public static void SetIsRetrievableInInventory(String characterName, String itemName, boolean retrievable) {
        Player mainCharacter = GameState.MainCharacter;
        String currLocationName = mainCharacter.GetCurrentLocation().GetName();
        SetIsRetrievableInInventory(currLocationName, characterName, itemName, retrievable);
        mainCharacter.SetCurrentLocation(currLocationName);
    }

    public static void SetIsRetrievableInInventory(String itemName, boolean retrievable) {
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");
        Item item = GetMainCharacterItem(itemName);
        item._isRetrievable = retrievable;
        SetMainCharacterItem(itemName, item);
    }

    private static Item GetCharacterItem(Location location, String characterName, String itemName) {
        NPC npc = location._NPCs.get(characterName);
        Item item = npc.GetInventory().get(itemName);
        return item;
    }

    private static void SetCharacterItem(Location location, String characterName, String itemName, Item item) {
        NPC character = location._NPCs.get(characterName);
        character.GetInventory().replace(itemName, item);
        location._NPCs.replace(characterName, character);
        GameState.Locations.replace(characterName, location);
    }

    private static Item GetMainCharacterItem(String itemName) {
        return GameState.MainCharacter.GetInventory().get(itemName);
    }

    private static void SetMainCharacterItem(String itemName, Item item) {
        GameState.MainCharacter.GetInventory().replace(itemName, item);
    }
}
