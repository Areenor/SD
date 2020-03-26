package Services;


import Default_classes.Character;
import Game_data.GameState;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import java.util.ArrayList;


public abstract class Combat {

    private static TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private static TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

    public static ArrayList<Character> combat_order = new ArrayList<Character>();

    public static void init(){
        terminal.println("Combat starts! Vampire and Warewolf won`t let you live!");
        terminal.println("Your HP is: " + GameState.MainCharacter.GetCurrentHitPoints());
        terminal.println("Your Stamina is: " + GameState.MainCharacter.GetCurrentStamina());
        GameState.Combat = true;
        combat_order.add(GameState.MainCharacter);
        combat_order.addAll(GameState.MainCharacter.GetCurrentLocation().getEnemies());
        combat_order.sort(Character.DexterityComparator);
    }

    public static void run(ArrayList<Character> order, int start){
        if(order.size() == 1){
            combat_end();
        }
        else{
            combat_flow(order, start);
        }
    }

    private static void combat_flow(ArrayList<Character> order, int start){
        for(int current = 0;current < order.size(); current++) {
            if(current < start){
                continue;
            }
            if(order.get(current) == GameState.MainCharacter){
                playerAction();
            } else {
                if(order.get(current).IsDead()){ //creates a new arrayList without killed npc. "start" variable is needed to keep the correct order after this event.
                    order.remove(current);
                    run(order, current);
                    break;
                }
                else {
                    enemyAction(order.get(current));
                }
            }
        }
    }

    private static void playerAction(){
        GameState.MainCharacter.ResetStamina();
        while(GameState.MainCharacter.GetCurrentStamina() > 0){
            Controller.ExecuteCombatCommand(GameState.MainCharacter);
            terminal.println("Your Stamina is: " + GameState.MainCharacter.GetCurrentStamina());
        }
    }

    private static void enemyAction(Character attacker){
        attacker.Attack(GameState.MainCharacter.GetName());
        terminal.println("Your HP is: " + GameState.MainCharacter.GetCurrentHitPoints());
    }

    private static void combat_end(){
        combat_order.clear();
        terminal.println("Combat ends!");
        GameState.Combat = false;
    }

}
