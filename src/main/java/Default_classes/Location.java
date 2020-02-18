package Default_classes;

import Configuration_models.LocationConfig;
import Game_data.GameState;
import jdk.tools.jaotc.Main;

import java.util.Map;

public class Location {
    public final String _name;
    public String _description;
    public String[] _objects;
    public String[] _characters;
    public Map<String, String> _adjacentLocations;


    public Location(LocationConfig config) {
        if (config == null) throw new IllegalArgumentException("The configuration is empty");
        if (config.Name.isEmpty()) throw  new IllegalArgumentException("The location name is empty");
        if (config.Description.isEmpty()) throw  new IllegalArgumentException("The location description is empty");

        _name = config.Name;
        _description = config.Description;
        _objects = config.Objects;
        _characters = config.Characters;
        _adjacentLocations = config.AdjacentLocations;
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
