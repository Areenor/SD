package Default_classes;

import Game_data.GameState;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.List;

public class Character {
    public String _name;
    public int _strength;
    public int _dexterity;
    public int _constitution;
    public int _hitPoints;
    public int _attack;
    public int _stamina;
    public List<String> _inventory;


    public static void ExecuteCommand(String command, String[] args) {

        TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
        TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

        switch (command) {
            case "examine":
                if (args.length == 1) {
                    terminal.print(GameState.CurrentLocation._description + "\n");
                } else {
                    if (GameState.CurrentLocation._characters.containsKey(args[1])) {
                        terminal.print(GameState.CurrentLocation._characters.get(args[1])._description + "\n");
                    } else if (GameState.CurrentLocation._items.containsKey(args[1])) {
                        terminal.print(GameState.CurrentLocation._items.get(args[1])._description + "\n");
                    } else {
                        terminal.print("There is no such thing to examine on this location, please try again.\n");
                    }
                }
                break;
            case "talk":
                if (args.length < 3) {
                    terminal.print("Not the correct way of talking, please try again: \n");
                } else {
                    if (GameState.CurrentLocation._characters.containsKey(args[2])) {
                        terminal.print(GameState.CurrentLocation._characters.get(args[2])._name + ": " + GameState.CurrentLocation._characters.get(args[2])._dialogue + "\n");
                    } else {
                        terminal.print("There is no such character on this location, please try again.\n");
                    }
                }
                break;
            case "take":
                if (args.length == 1) {
                    terminal.print("Please specify item.\n");
                } else {
                    if (GameState.CurrentLocation._items.containsKey(args[1])) {
                        GameState.MainCharacter._inventory.add(args[1]);
                        GameState.CurrentLocation._items.remove(args[1]);
                    } else {
                        terminal.print("There is no such item, please try again.\n");
                    }
                }
                break;
            case "move":
                if (args.length == 1) {
                    terminal.print("Please specify direction.\n");
                }
                GameState.CurrentLocation.move(args[1]);
                break;
            case "exit":
            case "quit":
                GameState.IsFinished = true;
                textIO.dispose();
                break;
            default:
                terminal.print("Unknown command, please try again.\n");
        }
    }
}