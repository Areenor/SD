package Services;

import Configuration_models.*;
import Default_classes.*;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ItemFactory {
    private Path storyDirPath = Paths.get(System.getProperty("user.dir"), "story");
    private Path itemJsonDirPath = Paths.get(storyDirPath.toString(), "junk");
    private Path consumJsonDirPath = Paths.get(storyDirPath.toString(), "consumables");
    private Path keyitemsJsonDirPath = Paths.get(storyDirPath.toString(), "keyitems");
    private Path equipmentJsonDirPath = Paths.get(storyDirPath.toString(), "equipment");

    public Item GetItem(String itemName) throws FileNotFoundException {
        Path consumConfigFilePath = Paths.get(consumJsonDirPath.toString(), itemName + ".json");
        Path keyItemConfigFilePath = Paths.get(keyitemsJsonDirPath.toString(), itemName + ".json");
        Path equipmentConfigFilePath = Paths.get(equipmentJsonDirPath.toString(), itemName + ".json");
        Path itemConfigFilePath = Paths.get(itemJsonDirPath.toString(), itemName + ".json");

        if(IsExistingFile(consumConfigFilePath)) { //If the item name belongs to a consumable item file
            ConsumConfig consumConfig = InitiationService.ReturnNewConsumConfig(consumConfigFilePath);
            return new Consumable(consumConfig);
        }
        else if(IsExistingFile(keyItemConfigFilePath)) { //If the item name belongs to a key item file
            KeyItemConfig keyItemConfig = InitiationService.ReturnNewKeyItemConfig(keyItemConfigFilePath);
            return new KeyItem(keyItemConfig);
        }
        else if(IsExistingFile(equipmentConfigFilePath)) { //If the item name belongs to a equipment item file
            EquipConfig equipConfig = InitiationService.ReturnNewEquipConfig(equipmentConfigFilePath);
            return new Equipment(equipConfig);
        }
        else if(IsExistingFile(itemConfigFilePath)) { //If the item name belongs to a junk item file
            ItemConfig itemConfig = InitiationService.ReturnNewJunkItemConfig(itemConfigFilePath);
            return new Junk(itemConfig);
        }
        else { //There is no file found with the requested item name, so exception is thrown.
            throw new FileNotFoundException("item configuration file does not exist.");
        }
    }

    private boolean IsExistingFile(Path filePath) {
        return Files.exists(filePath);
    }
}
