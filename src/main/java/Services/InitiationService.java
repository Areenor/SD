package Services;

import Default_classes.Location;
import com.alibaba.fastjson.JSON;


import java.nio.file.Path;
import java.nio.file.Paths;

public class InitiationService {
    private static Path storyDirPath = Paths.get(System.getProperty("user.dir"), "story");
    private static Path characterJsonDirPath = Paths.get(storyDirPath.toString(), "characters");
    private static Path locationJsonDirPath = Paths.get(storyDirPath.toString(), "locations");
    private static Path objectJsonDirPath = Paths.get(storyDirPath.toString(), "objects");

    public static void InitiateLocation(String locationName) {
        Path locationPath = Paths.get(locationJsonDirPath.toString(), locationName);
        Location room = JSON.parseObject(locationPath.toString(), Location.class);
    }
}
