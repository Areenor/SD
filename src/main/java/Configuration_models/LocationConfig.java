//
// configuration file for creating a location instance
//

package Configuration_models;
import java.util.*;

import Default_classes.AdjacentLocation;
import Enumerators.DirectionEnum;

public class LocationConfig {
    public String Name;
    public String Description;
    public List<String> Items;
    public List<String> Characters;
    public Map<DirectionEnum, AdjacentLocationConfig> AdjacentLocations = new HashMap<DirectionEnum, AdjacentLocationConfig>() {{
        put(DirectionEnum.north, new AdjacentLocationConfig());
        put(DirectionEnum.east, new AdjacentLocationConfig());
        put(DirectionEnum.south, new AdjacentLocationConfig());
        put(DirectionEnum.west, new AdjacentLocationConfig());
        put(DirectionEnum.up, new AdjacentLocationConfig());
        put(DirectionEnum.down, new AdjacentLocationConfig());
    }};
}

