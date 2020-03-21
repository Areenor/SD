package Default_classes;

import Enumerators.DirectionEnum;
import Game_data.GameState;
//import Services.LocationUpdateService;
import jdk.internal.jline.Terminal;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Character {
    private TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private TextTerminal terminal = textIO.getTextTerminal(); //strictly for output
    private Location _currentLocation;

    public Player(String name, int strength, int dexterity, int constitution, Location startLocation) {
        _name = name;
        _strength = strength;
        _dexterity = dexterity;
        _constitution = constitution;
        _inventory = new HashMap<String, Item>();
        _currentLocation = startLocation;
    }

    public Location GetCurrentLocation() { return _currentLocation; }
    public void SetCurrentLocation(String locationName) { _currentLocation = GameState.GetLocation(locationName); }
    public void SetCurrentLocation(Location location) { _currentLocation = location; }

    public void Use(String itemToUseName) {
        if(!_inventory.containsKey(itemToUseName)) {
            terminal.println("You do not posses a " + itemToUseName + "\n");
            return;
        }
        Item itemToUse = _inventory.get(itemToUseName);
        itemToUse.Use();
    }

    public void UseOnItem(String itemToUseName, String targetItemName) {
        if(!_inventory.containsKey(itemToUseName)) {
            terminal.println("You do not posses a " + itemToUseName + "\n");
            return;
        }
        if(!_currentLocation.ContainsItem(targetItemName)) {
            terminal.println("There is no " + targetItemName + " present in this location.\n");
            return;
        }
        Item itemToUse = _inventory.get(itemToUseName);
        Item targetItem = _currentLocation.GetItem(targetItemName);
        itemToUse.Use(targetItem);
    }

    public void UseOnNpc(String itemToUseName, String targetNpcName) {
        if(!_inventory.containsKey(itemToUseName)) {
            terminal.println("You do not posses a " + itemToUseName + "\n");
            return;
        }
        if(!_currentLocation.ContainsNpc(targetNpcName)) {
            terminal.println("The character " + targetNpcName + " is not present in this location.\n");
            return;
        }
        Item itemToUse = _inventory.get(itemToUseName);
        NPC targetNpc = _currentLocation.GetNpc(targetNpcName);
        itemToUse.Use(targetNpc);
    }

    public void TalkTo(String targetNpcName) {
        if (!_currentLocation.ContainsNpc(targetNpcName)) {
            terminal.println("The character " + targetNpcName + " is not present in this location.\n");
            return;
        }

        terminal.println("You approach " + targetNpcName + "\n");
        NPC targetNpc = _currentLocation.GetNpc(targetNpcName);
        targetNpc.Talk();
    }

    public void Take(String targetItemName) {
        if (!_currentLocation.ContainsItem(targetItemName)) {
            terminal.println("There is no " + targetItemName + " present in this location.\n");
            return;
        }

        Item targetItem = _currentLocation.GetItem(targetItemName);
        if(!targetItem.IsRetrievable()) {
            terminal.println("The " + targetItemName + " can't be picked up.\n");
            return;
        }
        _currentLocation.RemoveItem(targetItemName);
        AddToInventory(targetItem);
        terminal.println("You took the " + targetItemName + "\n");
    }

    public void Move(DirectionEnum direction) {
        String adjacentLocationName = _currentLocation.GetAdjacentLocation(direction);
        if (adjacentLocationName == null || adjacentLocationName.isEmpty()) {
            terminal.println("There is nothing in this direction.\n");
            return;
        }

        //Check if next location is locked
        Location nextLocation = GameState.GetLocation(adjacentLocationName);
        _currentLocation = nextLocation;

        terminal.println("\n" + _currentLocation.GetDescription() + " .\n");
    }

    public void Attack(String targetCharacterName) {
        //To be implemented
    }

    public void PrintInventory() {
        String inventoryItems = String.join(", ", _inventory.keySet());
        terminal.println("You posses the following items:\n" + inventoryItems);
    }
}