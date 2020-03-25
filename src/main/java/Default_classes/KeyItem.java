//
// Default consumable
//

package Default_classes;

import Configuration_models.KeyItemConfig;
import Configuration_models.ItemConfig;
import Enumerators.DirectionEnum;
import Game_data.GameState;
import Services.Terminal;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class KeyItem extends Item{
    private String _intendedTarget;
    private String _newTargetDescription;
    private DirectionEnum _unlockedDirection;
    private String _unlockedLocationName;

    public KeyItem(ItemConfig config, KeyItemConfig addon) {
        super(config);
        _intendedTarget = addon.IntendedTarget;
        _newTargetDescription = addon.NewTargetDescription;
        _unlockedDirection = addon.UnlockedDirection;
        _unlockedLocationName = addon.UnlockedLocationName;
    }

    @Override
    public void Use() {
        Terminal.PrintLine("This is a key item. Key items cannot be used on yourself, only on other items or characters in your location.");
    }

    @Override
    public void Use(Item targetItem) {
        if(_intendedTarget.equals(targetItem.GetName())) {
            targetItem.SetDescription(_newTargetDescription);
            GameState.MainCharacter.GetCurrentLocation().AddAdjacentLocation(_unlockedDirection,_unlockedLocationName);
            Terminal.PrintLine("You successfully used " + _name + " on  " + _intendedTarget + ".");
            GameState.MainCharacter.RemoveFromInventory(_name);
        }
        else {
            Terminal.PrintLine("This is not the intended target for this key item. Try again on a different target. ");
        }
    }

    @Override
    public void Use(NPC targetNpc) {
    }
}
