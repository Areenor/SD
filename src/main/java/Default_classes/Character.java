package Default_classes;

import Configuration_models.CharacterConfig;

public class Character {
    public final String _name;
    public String _description;
    public String _typeName;
    public String[] _inventoryItems;
    public boolean _isHostile = false;
    public boolean _isFightable = false;
    public int _hitPoints;
    public int _attack;
    public int _stamina;

    public Character(CharacterConfig config) {
        if (config == null) throw new IllegalArgumentException("The configuration is empty");
        if (config.Name.isEmpty()) throw  new IllegalArgumentException("The character name is empty");
        if (config.Description.isEmpty()) throw  new IllegalArgumentException("The character description is empty");

        _name = config.Name;
        _description = config.Description;
        _typeName = config.TypeName;
        _inventoryItems = config.InventoryItems;
        _isFightable = config.IsFightable;
        _isHostile = config.IsHostile;
        _hitPoints = config.HitPoints;
        _attack = config.Attack;
        _stamina = config.Stamina;
    }

    public void Talk() {
        //not implemented
    }
}
