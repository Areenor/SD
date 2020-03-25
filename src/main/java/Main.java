import Default_classes.Location;
import Game_data.GameState;
import Services.Controller;
import Services.InitiationService;
import Services.Terminal;
import org.apache.log4j.BasicConfigurator;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class Main {

    public static void main (String[] args) {
        final String SPAWN_ROOM = "room";

        BasicConfigurator.configure();
        InitiationService.InitiateLocations();

        Terminal.PrintLine("Welcome Player! ");
        InitiationService.InitiateMainCharacter(SPAWN_ROOM);

        Location currentLocation = GameState.MainCharacter.GetCurrentLocation();
        String description = currentLocation.GetDescription();
        Terminal.PrintLine(description + "\n");

        while (!GameState.IsFinished) {
            Controller.ExecuteCommand(GameState.MainCharacter);
        }
    }
}

