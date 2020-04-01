package Game_data;

import Default_classes.Character;
import Default_classes.Location;

import java.util.HashMap;
import java.util.Map;

public class GameState {
    public static boolean IsFinished = false;
    public static Location CurrentLocation;
    public static Character MainCharacter = new Character();
    public static Map<String, Location> Locations = new HashMap<>();
    public static final int baseHealth = 5;
    public static final int baseAttack = 1 ;
    public static final int baseStamina = 1;


    public static Location GetLocation(String locationName) {
        if (Locations.containsKey(locationName))
            return Locations.get(locationName);
        return null;
    }
}
