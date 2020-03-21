package Services;

import Default_classes.Item;
import Default_classes.Location;
import Default_classes.NPC;
import Game_data.GameState;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import Enumerators.DirectionEnum;

import java.util.Map;

public class Controller {

    private TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

    public void ExecuteCommand(String command, String[] args) {
        switch (command) {
            case "examine":
                Examine(args);
                break;
            case "talk":
                Talk(args);
                break;
            case "take":
                Take(args);
                break;
            case "move":
                Move(args);
                break;
            case "give":
                Give(args);
                break;
            case "attack":
                break;
            case "use":
                break;
            case "hint":
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

    private boolean IsNpcInCurrentLocation(String NpcName) {
        return GameState.MainCharacter.GetCurrentLocation()._NPCs.containsKey(NpcName);
    }

    private boolean IsIteminCurrentLocation(String ItemName) {
        return GameState.MainCharacter.GetCurrentLocation()._items.containsKey(ItemName);
    }

    private void Examine(String[] args) {
        String description = "";
        Location currentLocation = GameState.MainCharacter.GetCurrentLocation();

        if (args.length == 1) { //examine without arguments causes the current location to be examined with all its residing items and NPCs
            description = currentLocation.GetDescription();
        }
        else if (IsNpcInCurrentLocation(args[1])) {
            NPC examinedNPC = currentLocation.GetNpc(args[1]);
            description = examinedNPC.GetDescription();
        }
        else if (IsIteminCurrentLocation(args[1])) {
            Item examinedItem = currentLocation.GetItem(args[1]);
            description = examinedItem.GetDescription();
        }

        if (description.equals("")) {
            terminal.print("No such thing is available to be examined.\n");
            return;
        }
        terminal.print(description + "\n");
    }

    private void Talk(String[] args) {
        if (args.length < 3 || !args[1].equals("to")) {
            terminal.print("Not the correct way of talking, please try again.\n");
            return;
        }

        if (IsNpcInCurrentLocation(args[2])) {
            NPC spokenToNPC = GameState.MainCharacter.GetCurrentLocation().GetNpc(args[2]);
            String conversation;
            conversation = spokenToNPC.GetName() + ": " + spokenToNPC.GetDialogue() + "\n";
            terminal.print(conversation);
        }
        else {
            terminal.print("There is no such character on this location to talk to, please try again.\n");
        }
    }

    private void Take(String[] args) {
        if (args.length == 1) {
            terminal.print("Please specify item.\n");
            return;
        }

        if (IsIteminCurrentLocation(args[1])) {
            Item grabbedItem = GameState.MainCharacter.GetCurrentLocation().GetItem(args[1]);

            if(grabbedItem.IsRetrievable()) {
                LocationUpdateService.RemoveItem(args[1]);
                terminal.print("You took " + args[1] + " and put it in your inventory.\n");
            }
            else {
                terminal.print("You cannot pick this item up, please try again.\n");
            }
        }
        else {
            terminal.print("There is no such item, please try again.\n");
        }
    }

    private void Move(String[] args) {
        if (args.length == 1) {
            terminal.print("Please specify direction.\n");
            return;
        }

        Location currentLocation = GameState.MainCharacter.GetCurrentLocation();
        DirectionEnum direction = DirectionEnum.valueOf(args[1]);
        Map<DirectionEnum, String> adjacentLocations = currentLocation.GetAdjacentLocations();

        String nextLocationName = adjacentLocations.get(direction);
        if (nextLocationName != null && !nextLocationName.isEmpty()) {
            currentLocation = GameState.GetLocation(nextLocationName);

            terminal.printf("You entered %s.\n", currentLocation.GetName());
            String description = currentLocation.GetDescription();
            terminal.print(description + "\n");
            return;
        }
        terminal.print("You found nothing traveling in this direction and returned to your original location.\n");
    }

    private void Give(String[] args) {
        if (args.length < 4) {
            terminal.print("Incorrect input amount of arguments give command, try again.\n");
            return;
        }
        terminal.print("Item not in inventory, try again.\n");
        return;
    }
}