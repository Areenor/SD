package Services;

import Configuration_models.NPCConfig;
import Configuration_models.LocationConfig;
import Configuration_models.ObjectConfig;
import Default_classes.NPC;
import Default_classes.Location;
import Default_classes.Object;
import Game_data.GameState;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

        assert locationConfigFiles != null;
        for (File locationConfigFile : locationConfigFiles) {
            String locationName = locationConfigFile.getName().replace(".json", "");
            GameState.Locations.put(locationName, InitiateLocation(locationConfigFile.getPath()));
        }
    }

    public static Location InitiateLocation(String locationConfigFilePath) {
        String locationConfigFileContent = readLineByLine(locationConfigFilePath);
        LocationConfig locationConfig = JSON.parseObject(locationConfigFileContent, LocationConfig.class);
        return new Location(locationConfig);
    }

    public static NPC InitiateCharacter(String characterName) {
        Path characterConfigFilePath = Paths.get(characterJsonDirPath.toString(), characterName + ".json");
        String characterConfigFileContent = readLineByLine(characterConfigFilePath.toString());
        NPCConfig characterConfig = JSON.parseObject(characterConfigFileContent, NPCConfig.class);
        return new NPC(characterConfig);
    }

    public static Object InitiateObject(String objectName) {
        Path objectConfigFilePath = Paths.get(objectJsonDirPath.toString(), objectName + ".json");
        String objectConfigFileContent = readLineByLine(objectConfigFilePath.toString());
        ObjectConfig objectConfig = JSON.parseObject(objectConfigFileContent, ObjectConfig.class);
        return new Object(objectConfig);
    }

    private static String readLineByLine(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
