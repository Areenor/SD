import Default_classes.Location;
import Game_data.GameState;
import Services.InitiationService;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        System.out.println("Welcome to Software Design");
        Scanner scanner = new Scanner(System.in);
        InitiationService.InitiateLocations();
        Map<String, Location> a = GameState.Locations;
        GameState.CurrentLocation = GameState.GetLocation("room");

        while (!GameState.IsFinished) {
            String userInput = scanner.nextLine();
            System.out.print("You entered ");
            System.out.println(userInput);

            String[] arguments = userInput.split(" ");
            String command = arguments[0];
            ExecuteCommand(command, arguments);
        }
    }

    private static void ExecuteCommand(String command, String[] args) {
        if ("examine".equals(command)) {
            System.out.println("command is examine");
        } else if ("take".equals(command)) {
            GameState.CurrentLocation._description = "hurdur";
            System.out.println("command is take");
        } else if ("use".equals(command)) {
            System.out.println("command is use");
        } else if ("north".equals(command)) {
                System.out.println("command is North");
                GameState.CurrentLocation.North();
        }else if ("south".equals(command)) {
            System.out.println("command is South");
            GameState.CurrentLocation.South();
        } else if ("quite".equals(command) || "exit".equals(command)) {
            GameState.IsFinished = true;
        } else {
            System.out.println("unknown command");
        }
    }
}
