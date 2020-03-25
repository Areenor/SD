package Default_classes;

import Configuration_models.NPCConfig;
import Game_data.GameState;
import Services.InitiationService;
import Services.Terminal;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class NPC extends Character {
    private String _description;
    private String _type;
    private String _dialogue;
    private boolean _isHostile;
    private boolean _isFightable;

    public NPC(NPCConfig config) {
        if (config == null) throw new IllegalArgumentException("The configuration is empty");
        if (config.Name.isEmpty()) throw  new IllegalArgumentException("The character name is empty");
        if (config.Description.isEmpty()) throw  new IllegalArgumentException("The character description is empty");

        _name = config.Name;
        _description = config.Description;
        _type = config.Type;
        _dialogue = config.Dialogue;
        _strength = config.Strength;
        _dexterity = config.Dexterity;
        _constitution = config.Constitution;
        _isFightable = config.IsFightable;
        _isHostile = config.IsHostile;
        _hitPoints = _constitution + BASE_HEALTH;
        _attack = _strength + BASE_ATTACK;
        _stamina = _dexterity + BASE_STAMINA;
        _inventory = InitiationService.InitiateCharacterInventory(config.Inventory);
    }

    public String GetDescription(){
        return _description;
    }
    public String GetType() { return _type; }
    public String GetDialogue() {
        return _dialogue;
    }
    public boolean IsHostile() { return _isHostile; }
    public boolean IsFightable() { return _isFightable; }

    public void SetDescription(String description){
        _description = description;
    }
    public void SetType(String type) { _type = type; }
    public void SetDialogue(String dialogue) { _dialogue = dialogue; }

    public void Talk() {
        if (_dialogue == null || _dialogue.isEmpty())
            Terminal.PrintLine(_name + " has nothing to say to you.");
        Terminal.PrintLine(_dialogue + "\n");
    }

    public void Attack(String targetCharacterName) {
        //To be implemented
    }

    public void DropInventory(Location dropLocation) {
        for (String itemName : _inventory.keySet()) {
            Item item = GetItem(itemName);
            RemoveFromInventory(itemName);
            dropLocation.AddItem(item);
        }
    }
}