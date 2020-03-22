//
// Default consumable
//

package Default_classes;

import Configuration_models.ConsumConfig;
import Configuration_models.ItemConfig;
import Enumerators.StatEnum;
import Game_data.GameState;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class Junk extends Item{

    private TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

    public Junk(ItemConfig config) {
        super(config);
    }

    @Override
    public void Use() {
        terminal.print("This item has no use, it is junk.\n");
    }

    @Override
    public void Use(Item targetItem) {
        terminal.print("This item has no use, it is junk.\n");
    }

    @Override
    public void Use(NPC targetNpc) {
        terminal.print("This item has no use, it is junk.\n");
    }
}
