package Default_classes;

import Configuration_models.LocationConfig;
import Game_data.GameState;
import Services.InitiationService;

import java.util.HashMap;
import java.util.Map;

public class Location {
    public final String _name;
    public String _description;
    public Map<String, Object> _objects = new HashMap<String, Object>();
    public Map<String, Character> _characters = new HashMap<String, Character>();
    public Map<String, String> _adjacentLocations = new HashMap<String, String>();


    public Location(LocationConfig config) {
        if (config == null) throw new IllegalArgumentException("The configuration is empty");
        if (config.Name.isEmpty()) throw new IllegalArgumentException("The location name is empty");
        if (config.Description.isEmpty()) throw new IllegalArgumentException("The location description is empty");

        _name = config.Name;
        _description = config.Description;
        _adjacentLocations = config.AdjacentLocations;

        for (String character : config.Characters) {
            _characters.put(character, InitiationService.InitiateCharacter(character));
        }
        for (String object : config.Objects) {
            _objects.put(object, InitiationService.InitiateObject(object));
        }
    }

    public void move(String direction) {
        String nextLocationName = _adjacentLocations.get(direction);
        if (nextLocationName != null && !nextLocationName.isEmpty()) {
            GameState.CurrentLocation = GameState.GetLocation(nextLocationName);
            System.out.println("You entered " + nextLocationName);
            System.out.println(GameState.CurrentLocation._description);
            return;
        }
        System.out.println("nothing happened, try a different direction");
    }

    public void North() {
        move("north");
    }

    public void East() {
        move("east");
    }

    public void South() {
        move("south");
    }

    public void West() {
        move("west");
    }
    public void Up() {
        move("up");
    }
    public void Down() {
        move("down");
    }
}
