package Services;

import Configuration_models.*;
import Default_classes.*;
import Game_data.GameState;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class InitiationService {
    private static Path storyDirPath = Paths.get(System.getProperty("user.dir"), "story");
    private static Path locationJsonDirPath = Paths.get(storyDirPath.toString(), "locations");
    private static Path characterJsonDirPath = Paths.get(storyDirPath.toString(), "characters");
    private static Path itemJsonDirPath = Paths.get(storyDirPath.toString(), "items");
    private static Path consumJsonDirPath = Paths.get(storyDirPath.toString(), "consumables");
    private static Path keyitemsJsonDirPath = Paths.get(storyDirPath.toString(), "keyitems");
    private static Path equipmentJsonDirPath = Paths.get(storyDirPath.toString(), "equipment");

    public static void InitiateMainCharacter(String startingLocationName) {
        int pointCount = 2;
        String strengthInput = "";
        String dexterityInput = "";
        String constitutionInput = "";

        String nameInput = Terminal.Read("Please enter the name of your character \n");
        String mainCharacterName = nameInput;
        Terminal.Print("There are thee game statistics of you character: Strength, Dexterity and Constitution." +
                "All of them will directly influence the performance of your character in combat. You have 2 points to split between them.\n");

        while(pointCount != 0){
            strengthInput = Terminal.Read("Strength:");
            dexterityInput = Terminal.Read("Dexterity:");
            constitutionInput = Terminal.Read("Constitution:");
            pointCount = pointCount - Integer.parseInt(constitutionInput) - Integer.parseInt(dexterityInput) - Integer.parseInt(strengthInput);
            if(pointCount < 0) {
                pointCount = 2;
                Terminal.Print("Too many points assigned, please assign only two points\n");
            }
            else if(pointCount > 0) {
                pointCount = 2;
                Terminal.Print("Not enough points assigned, please assign only two points\n");
            }
        }

        Location startingLocation = GameState.GetLocation(startingLocationName);
        GameState.MainCharacter = new Player(mainCharacterName, Integer.parseInt(strengthInput), Integer.parseInt(dexterityInput), Integer.parseInt(constitutionInput), startingLocation);
        Terminal.Print("Greetings, " + GameState.MainCharacter.GetName() + "\n");
    }

    public static HashMap<String, Item> InitiateCharacterInventory(List<String> inventoryItems) {
        HashMap<String, Item> inventory = new HashMap<String, Item>();
        if (inventoryItems != null)
            for (String itemName : inventoryItems )
            {
                Item item = InitiateItem(itemName);
                inventory.put(itemName, item);
            }
        return inventory;
    }

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

    public static Item InitiateItem(String itemName) {
        Path itemConfigFilePath = Paths.get(itemJsonDirPath.toString(), itemName + ".json");
        String objectConfigFileContent = readLineByLine(itemConfigFilePath.toString());
        ItemConfig itemConfig = JSON.parseObject(objectConfigFileContent, ItemConfig.class);


        Path consumConfigFilePath = Paths.get(consumJsonDirPath.toString(), itemName + ".json");
        Path keyitemsConfigFilePath = Paths.get(keyitemsJsonDirPath.toString(), itemName + ".json");
        Path equipmentConfigFilePath = Paths.get(equipmentJsonDirPath.toString(), itemName + ".json");

        if(Files.exists(consumConfigFilePath)) {
            String addobjectConfigFileContent = readLineByLine(consumConfigFilePath.toString());
            ConsumConfig consumConfig = JSON.parseObject(addobjectConfigFileContent, ConsumConfig.class);
            return new Consumable(itemConfig, consumConfig);
        }
        else if(Files.exists(keyitemsConfigFilePath)) {
            String addobjectConfigFileContent = readLineByLine(keyitemsConfigFilePath.toString());
            KeyItemConfig keyItemConfig = JSON.parseObject(addobjectConfigFileContent, KeyItemConfig.class);
            return new KeyItem(itemConfig, keyItemConfig);
        }
        else if(Files.exists(equipmentConfigFilePath)) {
            String addobjectConfigFileContent = readLineByLine(equipmentConfigFilePath.toString());
            EquipConfig equipConfig = JSON.parseObject(addobjectConfigFileContent, EquipConfig.class);
            return new Equipment(itemConfig, equipConfig);
        }
        return new Junk(itemConfig);
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
