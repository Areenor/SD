package Services;

import Default_classes.Character;
import Default_classes.Item;
import Enumerators.StatEnum;
import Game_data.GameState;

public class CharacterUpdateService {
    public static void SetCharacterStatistics(StatEnum statistic, int statValue) {
        Character mainCharacter = GameState.MainCharacter;
        SetCharacterStatistics(mainCharacter, statistic, statValue);
        GameState.MainCharacter = mainCharacter;
    }

    public static void SetCharacterHp(int hpValue) {
        Character mainCharacter = GameState.MainCharacter;
        SetCharacterHp(mainCharacter, hpValue);
        GameState.MainCharacter = mainCharacter;
    }

    public static void AddItemToInventory(Item newItem) {
        Character mainCharacter = GameState.MainCharacter;
        AddItemToInventory(mainCharacter, newItem);
        GameState.MainCharacter = mainCharacter;
    }

    public static Item RemoveItemFromInventory(String itemName) {
        Character mainCharacter = GameState.MainCharacter;
        Item removedItem = RemoveItemFromInventory(mainCharacter, itemName);
        GameState.MainCharacter = mainCharacter;

        return removedItem;
    }

    public static void SetCharacterStatistics(Character character, StatEnum statistic, int statValue) {
        if (statValue < 0) { throw new IllegalArgumentException("The value of a statistic cannot be less than zero."); }

        switch (statistic) {
            case Strength: character._strength = statValue; break;
            case Dexterity: character._dexterity = statValue; break;
            case Constitution: character._constitution = statValue; break;
            case Attack: character._attack = statValue; break;
            case Stamina: character._stamina = statValue; break;
        }
    }

    public static void SetCharacterHp(Character character, int hpValue) {
        if (hpValue < 0) { throw new IllegalArgumentException("The value of hit points cannot be less than zero."); }
        character._hitPoints = hpValue;
    }

    public static void AddItemToInventory(Character character, Item newItem) {
        String itemName = newItem._name;
        character._inventory.put(itemName, newItem);
    }

    public static Item RemoveItemFromInventory(Character character, String itemName) {
        if (!character._inventory.containsKey(itemName)) throw new IllegalArgumentException(String.format("%1$s does not own an item named %2$s.", character._name, itemName));
        Item targetItem = character._inventory.get(itemName);
        character._inventory.remove(itemName);

        return targetItem;
    }
}
