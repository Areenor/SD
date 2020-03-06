//
// configuration file for creating a location instance
//

package Configuration_models;
import java.util.*;

public class LocationConfig {
    public String Name;
    public String Description;
    public List<String> Items;
    public String[] Characters;
    public Map<String, String> AdjacentLocations = new HashMap<String, String>() {{
        put("north","");
        put("east","");
        put("south","");
        put("west","");
        put("up", "");
        put("down", "");
    }};
}
