package Default_classes;

import Game_data.GameState;

import java.util.HashMap;

public class Player extends Character {
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
}