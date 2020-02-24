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
    public Map<String, String> _adjacentLocations;


    public Location(LocationConfig config) {
        if (config == null) throw new IllegalArgumentException("The configuration is empty");
        if (config.Name.isEmpty()) throw  new IllegalArgumentException("The location name is empty");
        if (config.Description.isEmpty()) throw  new IllegalArgumentException("The location description is empty");

        _name = config.Name;
        _description = config.Description;
        _adjacentLocations = config.AdjacentLocations;

        for (String character: config.Characters) {_characters.put(character, InitiationService.InitiateCharacter(character)); }
        for (String object: config.Objects) {_objects.put(object, InitiationService.InitiateObject(object)); }
    }

    public void North() {
        System.out.println(GameState.CurrentLocation);
        if (_adjacentLocations.containsValue("north")) {
            //not implemented
            return;
        }
        //print "nothing happened"
    }

    public void East() {
        if (_adjacentLocations.containsValue("east")) {
            //not implemented
            return;
        }
        //print "nothing happened"
    }

    public void South() {
        if (_adjacentLocations.containsValue("south")) {
            //not implemented
            return;
        }
        //print "nothing happened"
    }

    public void West() {
        if (_adjacentLocations.containsValue("west")) {
            //not implemented
            return;
        }
        //print "nothing happened"
    }
}
