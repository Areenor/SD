import Game_data.GameState;
import Services.InitiationService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println("Welcome to Software Design");
        Scanner scanner = new Scanner(System.in);
        InitiationService.InitiateLocations();

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
            System.out.println("command is take");
        } else if ("use".equals(command)) {
            System.out.println("command is use");
        } else if ("quite".equals(command) || "exit".equals(command)) {
            GameState.IsFinished = true;
        } else {
            System.out.println("unknown command");
        }
    }
}
