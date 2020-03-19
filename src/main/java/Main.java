import Default_classes.Location;
import Game_data.GameState;
import Services.Controller;
import Services.InitiationService;
import org.apache.log4j.BasicConfigurator;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.Map;

public class Main {

    public static void main (String[] args) {
        final String SPAWN_ROOM = "room";

        BasicConfigurator.configure();
        InitiationService.InitiateLocations();

        TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
        TextTerminal terminal = textIO.getTextTerminal(); //strictly for output
        Controller controller = new Controller();

        terminal.printf("Welcome Player! ");
        InitiationService.InitiateMainCharacter(SPAWN_ROOM);

        Location currentLocation = GameState.MainCharacter.GetCurrentLocation();
        String description = currentLocation.ReturnLocationDescription();
        terminal.print(description + "\n");

        while (!GameState.IsFinished) {
            String userInput = textIO.newStringInputReader().read("Please input a command:\n");

            String[] arguments = userInput.split(" ");
            String command = arguments[0];

            controller.ExecuteCommand(command, arguments);
        }
    }
}

