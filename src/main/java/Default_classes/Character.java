package Default_classes;

import java.util.HashMap;
import java.util.Map;

public class Character {
    public String _name;
    public int _strength;
    public int _dexterity;
    public int _constitution;
    public int _hitPoints;
    public int _attack;
    public int _stamina;
    public Map<String, Item> _inventory = new HashMap<String, Item>();
}