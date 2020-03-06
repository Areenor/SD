import Default_classes.Location;
import Game_data.GameState;
import Services.InitiationService;
import org.apache.log4j.BasicConfigurator;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import java.util.ArrayList;
import java.util.List;
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


        terminal.printf("Dear player, welcome to our game!");
        mainCharacterInitiation();
        terminal.print(GameState.CurrentLocation._description + "\n");

        while (!GameState.IsFinished) {
            String userInput = textIO.newStringInputReader().read("Please input a command:\n");

            String[] arguments = userInput.split(" ");
            String command = arguments[0];

            GameState.MainCharacter.ExecuteCommand(command, arguments);
        }
    }

    private static void mainCharacterInitiation() {
        TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
        TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

        int pointCount = 2;
        String strInput = "";
        String dexInput = "";
        String conInput = "";

        String nameInput = textIO.newStringInputReader().read("Please firstly enter the name of your character \n");
        GameState.MainCharacter._name = nameInput;
        terminal.printf("There are thee game statistics of you character: Strength, Dexterity and Constitution." +
                "All of them will directly influence the performance of your character in combat. You have 2 points to split between them.\n");

        while(pointCount != 0){
            strInput = textIO.newStringInputReader().read("Strength:");
            dexInput = textIO.newStringInputReader().read("Dexterity:");
            conInput = textIO.newStringInputReader().read("Constitution:");
            pointCount = pointCount - Integer.parseInt(conInput) - Integer.parseInt(dexInput) - Integer.parseInt(strInput);
            if(pointCount != 0) { terminal.printf("Remember, you have two points! Try again:\n"); }
        }

        GameState.MainCharacter._strength = Integer.parseInt(strInput);
        GameState.MainCharacter._dexterity = Integer.parseInt(dexInput);
        GameState.MainCharacter._constitution = Integer.parseInt(conInput);
        GameState.MainCharacter._inventory = new ArrayList<String>();

        terminal.printf("Greetings, " + GameState.MainCharacter._name + "\n");
    }
}

