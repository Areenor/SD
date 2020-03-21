package Services;

import Default_classes.Item;
import Default_classes.Player;
import Enumerators.StatEnum;
import Game_data.GameState;

public class PlayerUpdateService extends CharacterUpdateService {
    public static void SetStatistic(StatEnum statistic, int statValue) {
        Player mainCharacter = GameState.MainCharacter;
        SetStatistic(mainCharacter, statistic, statValue);
        GameState.MainCharacter = mainCharacter;
    }

    public static void SetHp(int hpValue) {
        Player mainCharacter = GameState.MainCharacter;
        SetHp(mainCharacter, hpValue);
        GameState.MainCharacter = mainCharacter;
    }

    public static void AddItemToInventory(Item newItem) {
        Player mainCharacter = GameState.MainCharacter;
        AddItemToInventory(mainCharacter, newItem);
        GameState.MainCharacter = mainCharacter;
    }

    public static Item RemoveItemFromInventory(String itemName) {
        Player mainCharacter = GameState.MainCharacter;
        Item removedItem = RemoveItemFromInventory(mainCharacter, itemName);
        GameState.MainCharacter = mainCharacter;

        return removedItem;
    }
}
