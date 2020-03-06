//
// configuration file for creating a character instance
//
package Configuration_models;

import java.util.List;

public class NPCConfig {
    public String Name;
    public String Description;
    public String Type;
    public String Dialogue;
    public List<String> Inventory;

    public boolean IsHostile;
    public boolean IsFightable;
    public int Strength;
    public int Dexterity;
    public int Constitution;

}
