package Default_classes;

import Configuration_models.NPCConfig;
import Game_data.GameState;

public class NPC extends Character{
    public String _description;
    public String _type;
    public String _dialogue;
    public boolean _isHostile;
    public boolean _isFightable;

    public NPC(NPCConfig config) {
        if (config == null) throw new IllegalArgumentException("The configuration is empty");
        if (config.Name.isEmpty()) throw  new IllegalArgumentException("The character name is empty");
        if (config.Description.isEmpty()) throw  new IllegalArgumentException("The character description is empty");

        _name = config.Name;
        _description = config.Description;
        _type = config.Type;
        _dialogue = config.Dialogue;
        _inventory = config.Inventory;
        _strength = config.Strength;
        _dexterity = config.Dexterity;
        _constitution = config.Constitution;
        _isFightable = false; //config.IsFightable;
        _isHostile = false; //config.IsHostile;
        _hitPoints = _constitution + GameState.baseHealth;
        _attack = _strength + GameState.baseAttack;
        _stamina = _dexterity + GameState.baseStamina;
    }

    public void Talk(String NpcName) {
    }
}