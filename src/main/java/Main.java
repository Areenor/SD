import Default_classes.Location;
import Game_data.GameState;
import Services.InitiationService;
import org.apache.log4j.BasicConfigurator;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.Map;

public class Main {
    public static void main (String[] args) {

        BasicConfigurator.configure();
        InitiationService.InitiateLocations();
        Map<String, Location> a = GameState.Locations;
        final String SpawnRoom = "room";
        GameState.CurrentLocation = GameState.GetLocation(SpawnRoom);

        TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
        TextTerminal terminal = textIO.getTextTerminal(); //strictly for output


        terminal.printf("Welcome Player! ");
        InitiationService.InitiateMainCharacter();
        terminal.print(GameState.CurrentLocation._description + "\n");

        while (!GameState.IsFinished) {
            String userInput = textIO.newStringInputReader().read("Please input a command:\n");

            String[] arguments = userInput.split(" ");
            String command = arguments[0];

            GameState.MainCharacter.ExecuteCommand(command, arguments);
        }
    }
}

