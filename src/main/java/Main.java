import Default_classes.Location;
import Game_data.GameState;
import Services.InitiationService;
import org.apache.log4j.BasicConfigurator;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        BasicConfigurator.configure();
        InitiationService.InitiateLocations();
        Map<String, Location> a = GameState.Locations;
        final String SpawnRoom = "room";
        GameState.CurrentLocation = GameState.GetLocation(SpawnRoom);

        TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
        TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

        terminal.printf("Greetings, adventurer! Welcome to our game! You spawned in: %s.\n", SpawnRoom);
        terminal.print(GameState.CurrentLocation._description);
        terminal.print("\n");

        while (!GameState.IsFinished) {
            String userInput = textIO.newStringInputReader().read("Please input a command:\n");

            String[] arguments = userInput.split(" ");
            String command = arguments[0];

            ExecuteCommand(command, arguments);
        }
    }

    private static void ExecuteCommand(String command, String[] args) {

        TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
        TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

        switch(command)   {
            case "examine":
                terminal.print(GameState.CurrentLocation._description);
                terminal.print("\n");
                break;
            case "take":
                break;
            case "use":
                break;
            case "move":
                break;
            case "north":
                GameState.CurrentLocation.North();
                break;
            case "south":
                GameState.CurrentLocation.South();
                break;
            case "west":
                GameState.CurrentLocation.West();
                break;
            case "east":
                GameState.CurrentLocation.East();
                break;
            case "up":
                GameState.CurrentLocation.Up();
                break;
            case "down":
                GameState.CurrentLocation.Down();
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
