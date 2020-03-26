package Default_classes;

import Game_data.GameState;
import Services.Combat;
import Services.Terminal;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public abstract class Character {

    protected final int BASE_HEALTH = 5;
    protected final int BASE_ATTACK = 1;
    protected final int BASE_STAMINA = 1;

    protected String _name;
    protected int _strength;
    protected int _dexterity;
    protected int _constitution;
    protected int _hitPoints;
    protected int _currentHitPoints;
    protected int _attack;
    protected int _stamina;
    protected int _currentStamina;
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
    public int GetCurrentHitPoints() { return _currentHitPoints; }
    public int GetAttack()  { return _attack; }
    public int GetStamina() { return _strength; }
    public int GetCurrentStamina() { return _currentStamina; }
    public Item GetItem(String itemName) { return _inventory.get(itemName); }
    public Boolean HasItem(String itemName) { return _inventory.containsKey(itemName); }

    public void SetStrength(int strength){
        _strength = strength;
    }
    public void SetDexterity(int dexterity){ _dexterity = dexterity; }
    public void SetConstitution(int constitution) { _constitution = constitution; }
    public void SetHitPoints(int hitPoints) { _hitPoints = hitPoints; }
    public void SetCurrentHitPoints(int currentHitPoints) { _currentHitPoints = currentHitPoints; }
    public void SetAttack(int attack)  { _attack = attack; }
    public void SetStamina(int stamina) { _stamina = stamina; }
    public void SetCurrentStamina(int currentStamina) { _currentStamina = currentStamina;};

    public boolean IsDead() { return _currentHitPoints <= 0; }

    public void ResetHealth() { _currentHitPoints = _hitPoints; }
    public void ResetStamina() { _currentStamina = _stamina; }

    public static Comparator<Character> DexterityComparator = (Character character1, Character character2) -> {
        int characterDexterity1 = character1.GetDexterity();
        int characterDexterity2 = character2.GetDexterity();

        //descending order
        return characterDexterity2 - characterDexterity1;

    };

    public void AddToInventory(Item item) { _inventory.put(item.GetName(), item); }
    public void RemoveFromInventory(String itemName) { _inventory.remove(itemName); }

    public void Attack(String targetCharacterName) {
        if(targetCharacterName.equals(GameState.MainCharacter.GetName())){
            DealDamage(GameState.MainCharacter);
        }
        else{
            DealDamage(GameState.MainCharacter.GetCurrentLocation().GetNpc(targetCharacterName));
        }
    }
    private void DealDamage(Character target){
        target.SetCurrentHitPoints(target.GetCurrentHitPoints() + target.GetConstitution() - _attack);
        if(target.IsDead()){
            Terminal.PrintLine(target.GetName() + " is dead!");
        }
        Terminal.PrintLine(_name + " strikes " + target.GetName() + " with " + _attack + " points of damage!");
    }
}
