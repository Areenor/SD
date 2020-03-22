//
// Default consumable
//

package Default_classes;

import Configuration_models.ConsumConfig;
import Configuration_models.EquipConfig;
import Configuration_models.ItemConfig;
import Enumerators.EquipmentTypeEnum;
import Game_data.GameState;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class Equipment extends Item{
    private int _blockBonus;
    private int _attackBonus;
    private EquipmentTypeEnum _type;

    private TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

    public Equipment(ItemConfig config, EquipConfig addon) {
        super(config);
        _blockBonus = addon.BlockBonus;
        _attackBonus = addon.AttackBonus;
        _type = addon.Type;
    }

    public int GetAttackBonus() {
        return _attackBonus;
    }
    public int GetBlockBonus() {
        return _blockBonus;
    }

    @Override
    public void Use() {
        switch (_type){
            case Weapon:
                GameState.MainCharacter.SetWeapon(this);
                break;
            case Armor:
                GameState.MainCharacter.SetArmor(this);
                break;
            default:
                throw new IllegalArgumentException("The configuration is empty, misses EquipmentType");
        }
        terminal.printf("You equiped the %s of type %s.\n", _name, _type);
    }

    @Override
    public void Use(Item targetItem) {
        terminal.print("This is a piece of equipment. Equipment can only be used on yourself.\n");
    }

    @Override
    public void Use(NPC targetNpc) {
        terminal.print("This is a piece of equipment. Equipment can only be used on yourself.\n");
    }
}
