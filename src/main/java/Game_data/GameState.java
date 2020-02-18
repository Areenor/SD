package Game_data;

import Default_classes.Location;

import java.util.Map;

public class GameState {
    public static boolean IsFinished = false;
    public static String CurrentLocation;
    public static Map<String, Location> Locations;
    public static Map<String, Object> Objects;
    public static Map<String, Character> Characters;

    public static Location GetLocation(String locationName) {
        if (Locations.containsKey(locationName))
            return Locations.get(locationName);
        return null;
    }

    public static Object GetObject(String objectName) {
        if (Objects.containsKey(objectName))
            return Objects.get(objectName);
        return null;
    }

    public static Character GetCharacter(String characterName) {
        if (Characters.containsKey(characterName))
            return Characters.get(characterName);
        return null;
    }
}
