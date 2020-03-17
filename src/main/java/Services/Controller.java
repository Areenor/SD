package Services;

import Default_classes.Item;
import Default_classes.NPC;
import Game_data.GameState;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import Services.LocationUpdateService;
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

    private boolean IsNPCinCurrentLocation(String NPCName) {
        return GameState.CurrentLocation._NPCs.containsKey(NPCName);
    }

    private boolean IsIteminCurrentLocation(String ItemName) {
        return GameState.CurrentLocation._items.containsKey(ItemName);
    }

    private void Examine(String[] args) {
        String description = "";

        if (args.length == 1) { //examine without arguments causes the current location to be examined with all its residing items and NPCs
            description = GameState.CurrentLocation.ReturnLocationDescription();
            description = description + GameState.CurrentLocation.ReturnResidingItemsDescriptions();
            description = description + GameState.CurrentLocation.ReturnResidingNPCDescriptions();
        }
        else if (IsNPCinCurrentLocation(args[1])) {
            NPC examinedNPC = GameState.CurrentLocation.ReturnResidingNPC(args[1]);
            description = examinedNPC.ReturnNPCDescription();
        }
        else if (IsIteminCurrentLocation(args[1])) {
            Item examinedItem = GameState.CurrentLocation.ReturnResidingItem(args[1]);
            description = examinedItem.ReturnItemDescription();
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

        if (IsNPCinCurrentLocation(args[2])) {
            NPC spokenToNPC = GameState.CurrentLocation.ReturnResidingNPC(args[2]);
            String conversation;
            conversation = spokenToNPC.ReturnNPCName() + ": " + spokenToNPC.ReturnNPCDialogue() + "\n";
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
            Item grabbedItem = GameState.CurrentLocation.ReturnResidingItem(args[1]);

            if(grabbedItem.isItemRetrievable()) {
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

        DirectionEnum direction = DirectionEnum.valueOf(args[1]);
        Map<DirectionEnum, String> adjacentLocations = GameState.CurrentLocation.ReturnAdjacentLocations();

        String nextLocationName = adjacentLocations.get(direction);
        if (nextLocationName != null && !nextLocationName.isEmpty()) {
            GameState.CurrentLocation = GameState.GetLocation(nextLocationName);

            terminal.printf("You entered %s.\n", GameState.CurrentLocation.ReturnLocationName());

            String description = GameState.CurrentLocation.ReturnLocationDescription();
            description = description + GameState.CurrentLocation.ReturnResidingItemsDescriptions();
            description = description + GameState.CurrentLocation.ReturnResidingNPCDescriptions();

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
        if() {
            terminal.print("Item not in inventory, try again.\n");
            return;
        }

    }
}