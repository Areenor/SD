package Default_classes;

import java.util.HashMap;
import java.util.Map;

public abstract class Character {
    protected String _name;
    protected int _strength;
    protected int _dexterity;
    protected int _constitution;
    protected int _hitPoints;
    protected int _attack;
    protected int _stamina;
    protected Map<String, Item> _inventory = new HashMap<String, Item>();

    public String GetName() {
        return _name;
    }
    public int GetStrenght(){
        return _strength;
    }
    public int GetDexterity(){
        return _dexterity;
    }
    public int GetConstitution() { return _constitution; }
    public int GetHitPoints() { return _hitPoints; }
    public int GetAttack()  { return _attack; }
    public int GetStamina() { return _strength; }
    public Map<String, Item> GetInventory() { return _inventory; }

    public void SetStrenght(int strength){
        _strength = strength;
    }
    public void SetDexterity(int dexterity){ _dexterity = dexterity; }
    public void SetConstitution(int constitution) { _constitution = constitution; }
    public void SetHitPoints(int hitPoints) { _hitPoints = hitPoints; }
    public void SetAttack(int attack)  { _attack = attack; }
    public void SetStamina(int strength) { _strength = strength; }
    public void SetInventory(HashMap<String, Item> inventory) { _inventory = inventory; }

    public void AddToInventory(String itemName, Item item) { _inventory.put(itemName, item); }
    public void RemoveFromInventory(String itemName) { _inventory.remove(itemName); }
}
