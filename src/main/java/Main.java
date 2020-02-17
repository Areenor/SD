import GameData.GameState;
import Services.InitiationService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println("Welcome to Software Design");
        Scanner scanner = new Scanner(System.in);
        InitiationService.InitiateLocation("room");

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
        switch (command) {
            case ("examine") :
                System.out.println("command is examine");
                break;
            case ("take") :
                System.out.println("command is take");
                break;
            case ("use") :
                System.out.println("command is use");
                break;
            case ("quite") :
            case ("exit"):
                GameState.IsFinished = true;
                break;
            default:
                System.out.println("unknown command");
        }
    }
}
