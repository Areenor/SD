package Default_classes;

import Configuration_models.NPCConfig;
import Game_data.GameState;
import Services.InitiationService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class NPC extends Character {
    public String _description;
    public String _type;
    public String _dialogue;
    public boolean _isHostile;
    public boolean _isFightable;

    private TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

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
        _hitPoints = _constitution + GameState.baseHealth;
        _attack = _strength + GameState.baseAttack;
        _stamina = _dexterity + GameState.baseStamina;
        _inventory = InitiationService.InitiateCharacterInventory(config.Inventory);
    }

    public String GetDescription(){
        return _description;
    }
    public String GetDialogue() {
        return _dialogue;
    }
}