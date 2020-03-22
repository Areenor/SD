package Default_classes;

import java.util.HashMap;
import java.util.Map;

public abstract class Character {
    protected final int BASE_HEALTH = 5;
    protected final int BASE_ATTACK = 1 ;
    protected final int BASE_STAMINA = 1;

    protected String _name;
    protected int _strength;
    protected int _dexterity;
    protected int _constitution;
    protected int _hitPoints;
    protected int _attack;
    protected int _stamina;
    protected Map<String, Item> _inventory = new HashMap<>();

    public String GetName() {
        return _name;
    }
    public int GetStrength(){
        return _strength;
    }
    public int GetDexterity(){
        return _dexterity;
    }
    public int GetConstitution() { return _constitution; }
    public int GetHitPoints() { return _hitPoints; }
    public int GetAttack()  { return _attack; }
    public int GetStamina() { return _strength; }
    public Item GetItem(String itemName) { return _inventory.get(itemName); }

    public void SetStrength(int strength){
        _strength = strength;
    }
    public void SetDexterity(int dexterity){ _dexterity = dexterity; }
    public void SetConstitution(int constitution) { _constitution = constitution; }
    public void SetHitPoints(int hitPoints) { _hitPoints = hitPoints; }
    public void SetAttack(int attack)  { _attack = attack; }
    public void SetStamina(int stamina) { _stamina = stamina; }

    public void AddToInventory(Item item) { _inventory.put(item.GetName(), item); }
    public void RemoveFromInventory(String itemName) { _inventory.remove(itemName); }
}
