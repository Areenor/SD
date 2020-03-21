package Services;

import Default_classes.Character;
import Default_classes.Player;
import Default_classes.Item;
import Enumerators.StatEnum;
import Game_data.GameState;

import java.util.Map;

public class CharacterUpdateService {
    protected static void SetStatistic(Character character, StatEnum statistic, int statValue) {
        if (statValue < 0) { throw new IllegalArgumentException("The value of a statistic cannot be less than zero."); }

        switch (statistic) {
            case Strength: character.SetStrength(statValue); break;
            case Dexterity: character.SetDexterity(statValue); break;
            case Constitution: character.SetConstitution(statValue);  break;
            case Attack: character.SetAttack(statValue);  break;
            case Stamina: character.SetStamina(statValue);  break;
        }
    }

    protected static void SetHp(Character player, int hpValue) {
        if (hpValue < 0) { throw new IllegalArgumentException("The value of hit points cannot be less than zero."); }
        player.SetHitPoints(hpValue);
    }

    protected static void AddItemToInventory(Character player, Item newItem) {
        String itemName = newItem._name;
        player.AddToInventory(itemName, newItem);
    }

    protected static Item RemoveItemFromInventory(Character player, String itemName) {
        Map<String, Item> inventory = player.GetInventory();
        if (!inventory.containsKey(itemName)) throw new IllegalArgumentException(String.format("%1$s does not own an item named %2$s.", player.GetName(), itemName));
        Item targetItem = inventory.get(itemName);
        player.RemoveFromInventory(itemName);

        return targetItem;
    }
}
