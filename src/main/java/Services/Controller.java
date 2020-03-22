package Services;

import Default_classes.Item;
import Default_classes.Location;
import Default_classes.NPC;
import Default_classes.Player;
import Game_data.GameState;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import Enumerators.DirectionEnum;

import java.util.Map;

public abstract class Controller {
    private static TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private static TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

    public static void ExecuteCommand(Player player) {
        String userInput = textIO.newStringInputReader().read();
        if (userInput == null || userInput.isEmpty())
            return;

        String[] args = userInput.split(" ");
        String command = args[0];

        switch (command) {
            case "examine":
                Examine(args, player);
                break;
            case "talk":
                Talk(args, player);
                break;
            case "take":
                Take(args, player);
                break;
            case "move":
                Move(args, player);
                break;
            case "use":
                Use(args, player);
                break;
            case "attack":
                Attack(args, player);
                break;
            case "inventory":
                player.PrintInventory();
                break;
            case "exit":
            case "quit":
                GameState.IsFinished = true;
                textIO.dispose();
                break;
            default:
                terminal.print("Unknown command.\n");
        }
    }

    private static void Examine(String[] args, Player player) {
        String description = "";
        Location playerLocation = player.GetCurrentLocation();

        if (args.length == 1) {
            description = playerLocation.GetDescription();
        }
        else if (playerLocation.ContainsNpc(args[1])) {
            NPC examinedNPC = playerLocation.GetNpc(args[1]);
            description = examinedNPC.GetDescription();
        }
        else if (playerLocation.ContainsItem(args[1])) {
            Item examinedItem = playerLocation.GetItem(args[1]);
            description = examinedItem.GetDescription();
        }
        else if (player.HasItem(args[1])) {
            Item examinedItem = player.GetItem(args[1]);
            description = examinedItem.GetDescription();
        }

        if (description.equals("")) {
            terminal.println("No such thing is available to be examined.\n");
            return;
        }
        terminal.println(description);
    }

    private static void Talk(String[] args, Player player) {
        if (args.length < 3 || !args[1].equals("to")) {
            terminal.println("Unknown command.");
            return;
        }
        if (args.length > 3) {
            terminal.println("Too many arguments.\n");
            return;
        }
        player.TalkTo(args[2]);
    }

    private static void Take(String[] args, Player player) {
        if (args.length < 2) {
            terminal.println("Please specify an item.\n");
            return;
        }
        if (args.length > 2) {
            terminal.println("Too many arguments.\n");
            return;
        }
        player.Take(args[1]);
    }

    private static void Move(String[] args, Player player) {
        if (args.length < 2) {
            terminal.println("Please specify a direction.\n");
            return;
        }
        if (args.length > 2) {
            terminal.println("Too many arguments.\n");
            return;
        }

        DirectionEnum direction;
        try {
            direction = DirectionEnum.valueOf(args[1]);
        } catch (IllegalArgumentException ex) {
            terminal.println("Unknown direction.\n");
            return;
        }

        player.Move(direction);
    }

    private static void Use(String[] args, Player player) {
        if (args.length < 2) {
            terminal.println("Please specify an item to use.\n");
            return;
        }
        if (args.length > 3) {
            terminal.println("Too many arguments.\n");
            return;
        }

        String ItemToUseName = args[1];
        if (args.length == 2) {
            player.Use(ItemToUseName);
            return;
        }
        if (player.GetCurrentLocation().ContainsNpc(ItemToUseName)) {
            player.UseOnNpc(ItemToUseName, args[2]);
            return;
        }
        if (player.GetCurrentLocation().ContainsItem(ItemToUseName)) {
            player.UseOnItem(ItemToUseName, args[2]);
            return;
        }
        terminal.println("No such item or character in the current location.\n");
    }

    private static void Attack(String[] args, Player player) {
        if (args.length < 2) {
            terminal.println("Please specify a character.\n");
            return;
        }
        if (args.length > 2) {
            terminal.println("Too many arguments.\n");
            return;
        }
        player.Attack(args[1]);
    }
}