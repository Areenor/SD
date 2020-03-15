package Services;

import Default_classes.Item;
import Default_classes.Location;
import Default_classes.NPC;
import Enumerators.StatEnum;
import Game_data.GameState;

public class NpcUpdateService extends CharacterUpdateService {
    public static void SetCharacterStatistics(String locationName, String npcName, StatEnum statistic, int statValue) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (npcName.isEmpty()) throw new IllegalArgumentException("npc name cannot be empty.");

        NPC npc = GetNpc(locationName, npcName);
        SetCharacterStatistics(npc, statistic, statValue);
        SetNpc(locationName, npcName, npc);
    }

    public static void SetCharacterHp(String locationName, String npcName, int hpValue) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (npcName.isEmpty()) throw new IllegalArgumentException("npc name cannot be empty.");

        NPC npc = GetNpc(locationName, npcName);
        SetCharacterHp(npc, hpValue);
        SetNpc(locationName, npcName, npc);
    }

    public static void AddItemToInventory(String locationName, String npcName, Item newItem) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (npcName.isEmpty()) throw new IllegalArgumentException("npc name cannot be empty.");

        NPC npc = GetNpc(locationName, npcName);
        AddItemToInventory(npc, newItem);
        SetNpc(locationName, npcName, npc);
    }

    public static Item RemoveItemFromInventory(String locationName, String npcName, String itemName) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (npcName.isEmpty()) throw new IllegalArgumentException("npc name cannot be empty.");

        NPC npc = GetNpc(locationName, npcName);
        Item removedItem = RemoveItemFromInventory(npc, itemName);
        SetNpc(locationName, npcName, npc);

        return removedItem;
    }

    private static void SetDescription(String locationName, String npcName, String newDescription) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (npcName.isEmpty()) throw new IllegalArgumentException("npc name cannot be empty.");

        NPC npc = GetNpc(locationName, npcName);
        npc._description = newDescription;
        SetNpc(locationName, npcName, npc);
    }

    private static void SetType(String locationName, String npcName, String newType) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (npcName.isEmpty()) throw new IllegalArgumentException("npc name cannot be empty.");

        NPC npc = GetNpc(locationName, npcName);
        npc._type = newType;
        SetNpc(locationName, npcName, npc);
    }

    public static void SetIsHostile(String locationName, String npcName, boolean hostile) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (npcName.isEmpty()) throw new IllegalArgumentException("npc name cannot be empty.");

        NPC npc = GetNpc(locationName, npcName);
        npc._isHostile = hostile;
        SetNpc(locationName, npcName, npc);
    }

    public static void SetIsFightable(String locationName, String npcName, boolean fightable) {
        if (locationName.isEmpty()) throw new IllegalArgumentException("Location name cannot be empty.");
        if (npcName.isEmpty()) throw new IllegalArgumentException("npc name cannot be empty.");

        NPC npc = GetNpc(locationName, npcName);
        npc._isFightable = fightable;
        SetNpc(locationName, npcName, npc);
    }

    private static NPC GetNpc(String locationName, String npcName) {
        Location location = GameState.Locations.get(locationName);
        return location._NPCs.get(npcName);
    }

    private static void SetNpc(String locationName, String npcName, NPC npc) {
        Location location = GameState.Locations.get(locationName);
        location._NPCs.replace(npcName, npc);
        GameState.Locations.replace(locationName, location);
    }
}
