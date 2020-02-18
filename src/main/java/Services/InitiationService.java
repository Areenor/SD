package Services;

import Default_classes.Location;
//import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InitiationService {
    private static Path storyDirPath = Paths.get(System.getProperty("user.dir"), "story");
    private static Path locationJsonDirPath = Paths.get(storyDirPath.toString(), "locations");
    private static Path characterJsonDirPath = Paths.get(storyDirPath.toString(), "characters");
    private static Path objectJsonDirPath = Paths.get(storyDirPath.toString(), "objects");

    public static void InitiateLocations() {
        List<Location> locations = new ArrayList<Location>();
        File[] locationConfigFiles = locationJsonDirPath.toFile().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".json");
            }
        });

        for (File locationConfigFile : locationConfigFiles) {
            locations.add(InitiateLocation(locationConfigFile));
        }
    }

    public static Location InitiateLocation(File locationConfigFile) {
        //return JSON.parseObject(locationConfigFile.toString(), Location.class);
        return null;
    }

    public static Character InitiateCharacter(String characterName) {
        Path locationPath = Paths.get(characterJsonDirPath.toString(), characterName);
        //return JSON.parseObject(locationPath.toString(), Location.class);
        return null;
    }

    public static Object InitiateObject(String objectName) {
        Path locationPath = Paths.get(objectJsonDirPath.toString(), objectName);
        //return JSON.parseObject(locationPath.toString(), Location.class);
        return null;
    }
}
