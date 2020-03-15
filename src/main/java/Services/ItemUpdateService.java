package Services;

import Default_classes.Item;
import Default_classes.Location;
import Default_classes.NPC;
import Game_data.GameState;

//If location name is not specified, the current location is used.
//If character name and location name are not specified the main character is used.
public class ItemUpdateService {
    public static void SetLocationItemDescription(String locationName, String itemName, String newDescription) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("location name cannot be empty.");
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");
        if (newDescription.isEmpty()) throw new IllegalArgumentException("New description cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        Item item = location._items.get(itemName);
        item._description = newDescription;
        GameState.Locations.replace(locationName, location);
    }

    public static void SetLocationItemDescription(String itemName, String newDescription) {
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");
        if (newDescription.isEmpty()) throw new IllegalArgumentException("New description cannot be empty.");

        Item item = GameState.CurrentLocation._items.get(itemName);
        item._description = newDescription;
        GameState.CurrentLocation._items.replace(itemName, item);
    }

    public static void SetLocationItemIsRetrievable(String locationName, String itemName, boolean retrievable) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("location name cannot be empty.");
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        Item item = location._items.get(itemName);
        item._isRetrievable = retrievable;
        GameState.Locations.replace(locationName, location);
    }

    public static void SetLocationItemIsRetrievable(String itemName,  boolean retrievable) {
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");

        Item item = GameState.CurrentLocation._items.get(itemName);
        item._isRetrievable = retrievable;
        GameState.CurrentLocation._items.replace(itemName, item);
    }

    public static void SetCharacterItemDescription(String locationName, String characterName, String itemName, String newDescription) {
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

    public static void SetCharacterItemDescription(String characterName, String itemName, String newDescription) {
        String currLocationName = GameState.CurrentLocation._name;
        SetCharacterItemDescription(currLocationName, characterName, itemName, newDescription);
        GameState.CurrentLocation = GameState.GetLocation(currLocationName);
    }

    public static void SetCharacterItemDescription(String itemName, String newDescription) {
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");
        if (newDescription.isEmpty()) throw new IllegalArgumentException("New description cannot be empty.");
        Item item = GetMainCharacterItem(itemName);
        item._description = newDescription;
        SetMainCharacterItem(itemName, item);
    }

    public static void SetCharacterItemIsRetrievable(String locationName, String characterName, String itemName, boolean retrievable) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("location name cannot be empty.");
        if (characterName.isEmpty()) throw new IllegalArgumentException("character name cannot be empty.");
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");

        Location location = GameState.GetLocation(locationName);
        Item item = GetCharacterItem(location, characterName, itemName);
        item._isRetrievable = retrievable;
        SetCharacterItem(location, characterName, itemName, item);
        GameState.Locations.replace(locationName, location);
    }

    public static void SetCharacterItemIsRetrievable(String characterName, String itemName, boolean retrievable) {
        String currLocationName = GameState.CurrentLocation._name;
        SetCharacterItemIsRetrievable(currLocationName, characterName, itemName, retrievable);
        GameState.CurrentLocation = GameState.GetLocation(currLocationName);
    }

    public static void SetCharacterItemIsRetrievable(String itemName, boolean retrievable) {
        if (itemName.isEmpty()) throw new IllegalArgumentException("item name cannot be empty.");
        Item item = GetMainCharacterItem(itemName);
        item._isRetrievable = retrievable;
        SetMainCharacterItem(itemName, item);
    }

    private static Item GetCharacterItem(Location location, String characterName, String itemName) {
        NPC npc = location._NPCs.get(characterName);
        Item item = npc._inventory.get(itemName);
        return item;
    }

    private static void SetCharacterItem(Location location, String characterName, String itemName, Item item) {
        NPC character = location._NPCs.get(characterName);
        character._inventory.replace(itemName, item);
        location._NPCs.replace(characterName, character);
        GameState.Locations.replace(characterName, location);
    }

    private static Item GetMainCharacterItem(String itemName) {
        return GameState.MainCharacter._inventory.get(itemName);
    }

    private static void SetMainCharacterItem(String itemName, Item item) {
        GameState.MainCharacter._inventory.replace(itemName, item);
    }
}
