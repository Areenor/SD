package Game_data;

import Default_classes.Location;

import java.util.Map;

public class GameState {
    public static boolean IsFinished = false;
    public static String CurrentLocation;
    public static Map<String, Location> Locations;

    public static Location GetLocation(String locationName) {
        if (Locations.containsKey(locationName))
            return Locations.get(locationName);
        return null;
    }
}
