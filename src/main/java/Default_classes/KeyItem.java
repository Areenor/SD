//
// Default consumable
//

package Default_classes;

import Configuration_models.KeyItemConfig;
import Configuration_models.ItemConfig;
import Enumerators.DirectionEnum;
import Game_data.GameState;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class KeyItem extends Item{
    private String _intendedTarget;
    private String _newTargetDescription;
    private DirectionEnum _unlockedDirection;
    private String _unlockedLocationName;

    private TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

    public KeyItem(ItemConfig config, KeyItemConfig addon) {
        super(config);
        _intendedTarget = addon.IntendedTarget;
        _newTargetDescription = addon.NewTargetDescription;
        _unlockedDirection = addon.UnlockedDirection;
        _unlockedLocationName = addon.UnlockedLocationName;
    }

    @Override
    public void Use() {
        terminal.print("This is a key item. Key items cannot be used on yourself, only on other items or characters in your location.\n");
    }

    @Override
    public void Use(Item targetItem) {
        if(_intendedTarget.equals(targetItem.GetName())) {
            targetItem.SetDescription(_newTargetDescription);
            GameState.MainCharacter.GetCurrentLocation().AddAdjacentLocation(_unlockedDirection,_unlockedLocationName);
            terminal.printf("You successfully used %s on %s.\n", _name, _intendedTarget);
            GameState.MainCharacter.RemoveFromInventory(_name);
        }
        else {
            terminal.print("This is not the intended target for this key item. Try again on a different target. \n");
        }
    }

    @Override
    public void Use(NPC targetNpc) {
    }
}
