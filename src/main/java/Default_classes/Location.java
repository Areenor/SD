package Default_classes;

import Configuration_models.LocationConfig;
import Game_data.GameState;
import Services.InitiationService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.HashMap;
import java.util.Map;

public class Location {
    public final String _name;
    public String _description;
    public Map<String, Object> _objects = new HashMap<String, Object>();
    public Map<String, NPC> _characters = new HashMap<String, NPC>();
    public Map<String, String> _adjacentLocations = new HashMap<String, String>();
    private TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

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

            terminal.printf("You entered %s.\n", GameState.CurrentLocation._name);
            terminal.print(GameState.CurrentLocation._description);
            terminal.print("\n");
            return;
        }
        terminal.print("You found nothing traveling in this direction and returned to your original location.\n");
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

    public void West() { move("west"); }
    public void Up() {  move("up"); }
    public void Down() {
        move("down");
    }
}
