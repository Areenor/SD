package Default_classes;

import Enumerators.DirectionEnum;
import Game_data.GameState;
import Services.Terminal;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import java.util.HashMap;


public class Player extends Character {
    private Location _currentLocation;
    private Equipment _weapon = null;
    private Equipment _armor = null;

    public Player(String name, int strength, int dexterity, int constitution, Location startLocation) {
        _name = name;
        _strength = strength;
        _dexterity = dexterity;
        _constitution = constitution;
        _inventory = new HashMap<String, Item>();
        _currentLocation = startLocation;
    }

    public Equipment GetWeapon() { return _weapon;}
    public Equipment GetArmor() { return _armor;}
    public void SetWeapon(Equipment newWeapon) {
        if(_weapon != null) {
            SetStrength(GetStrength() - _weapon.GetAttackBonus());
            AddToInventory(_weapon);
        }
        SetStrength(GetStrength() + newWeapon.GetAttackBonus());
        RemoveFromInventory(newWeapon.GetName());
        _weapon = newWeapon;
    }
    public void SetArmor(Equipment newArmor) {
        if(_armor != null) {
            SetConstitution(GetConstitution() - _armor.GetBlockBonus());
            AddToInventory(_armor);
        }
        SetConstitution(GetConstitution() + newArmor.GetBlockBonus());
        RemoveFromInventory(newArmor.GetName());
        _armor = newArmor;
    }

    public Location GetCurrentLocation() { return _currentLocation; }
    public void SetCurrentLocation(String locationName) { _currentLocation = GameState.GetLocation(locationName); }
    public void SetCurrentLocation(Location location) { _currentLocation = location; }

    public void Use(String itemToUseName) {
        if(!_inventory.containsKey(itemToUseName)) {
            Terminal.PrintLine("You do not posses a " + itemToUseName + "\n");
            return;
        }
        Item itemToUse = _inventory.get(itemToUseName);
        itemToUse.Use();
    }

    public void UseOnItem(String itemToUseName, String targetItemName) {
        if(!_inventory.containsKey(itemToUseName)) {
            Terminal.PrintLine("You do not posses a " + itemToUseName + "\n");
            return;
        }
        if(!_currentLocation.ContainsItem(targetItemName)) {
            Terminal.PrintLine("There is no " + targetItemName + " present in this location.\n");
            return;
        }
        Item itemToUse = _inventory.get(itemToUseName);
        Item targetItem = _currentLocation.GetItem(targetItemName);
        itemToUse.Use(targetItem);
    }

    public void UseOnNpc(String itemToUseName, String targetNpcName) {
        if(!_inventory.containsKey(itemToUseName)) {
            Terminal.PrintLine("You do not posses a " + itemToUseName + "\n");
            return;
        }
        if(!_currentLocation.ContainsNpc(targetNpcName)) {
            Terminal.PrintLine("The character " + targetNpcName + " is not present in this location.\n");
            return;
        }
        Item itemToUse = _inventory.get(itemToUseName);
        NPC targetNpc = _currentLocation.GetNpc(targetNpcName);
        itemToUse.Use(targetNpc);
    }

    public void TalkTo(String targetNpcName) {
        if (!_currentLocation.ContainsNpc(targetNpcName)) {
            Terminal.PrintLine("The character " + targetNpcName + " is not present in this location.\n");
            return;
        }

        Terminal.PrintLine("You approach " + targetNpcName + "\n");
        NPC targetNpc = _currentLocation.GetNpc(targetNpcName);
        targetNpc.Talk();
    }

    public void Take(String targetItemName) {
        if (!_currentLocation.ContainsItem(targetItemName)) {
            Terminal.PrintLine("There is no " + targetItemName + " present in this location.\n");
            return;
        }

        Item targetItem = _currentLocation.GetItem(targetItemName);
        if(!targetItem.IsRetrievable()) {
            Terminal.PrintLine("The " + targetItemName + " can't be picked up.\n");
            return;
        }
        _currentLocation.RemoveItem(targetItemName);
        AddToInventory(targetItem);
        Terminal.PrintLine("You took the " + targetItemName + "\n");
    }

    public void Move(DirectionEnum direction) {
        String adjacentLocationName = _currentLocation.GetAdjacentLocation(direction);
        if (adjacentLocationName == null || adjacentLocationName.isEmpty()) {
            Terminal.PrintLine("There is nothing in this direction.\n");
            return;
        }

        //Check if next location is locked
        Location nextLocation = GameState.GetLocation(adjacentLocationName);
        _currentLocation = nextLocation;

        Terminal.PrintLine("\n" + _currentLocation.GetDescription() + "\n");
    }

    public void Attack(String targetCharacterName) {
        //To be implemented
    }

    public void PrintInventory() {
        String inventoryItems = String.join(", ", _inventory.keySet());
        Terminal.PrintLine("You posses the following items:\n" + inventoryItems);
    }
}