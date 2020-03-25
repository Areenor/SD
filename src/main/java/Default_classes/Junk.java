//
// Default consumable
//

package Default_classes;

import Configuration_models.ConsumConfig;
import Configuration_models.ItemConfig;
import Enumerators.StatEnum;
import Game_data.GameState;
import Services.Terminal;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class Junk extends Item{
    public Junk(ItemConfig config) {
        super(config);
    }

    @Override
    public void Use() { Terminal.PrintLine("This item has no use, it is junk.");}

    @Override
    public void Use(Item targetItem) { Terminal.PrintLine("This item has no use, it is junk."); }

    @Override
    public void Use(NPC targetNpc) {
        Terminal.PrintLine("This item has no use, it is junk.");
    }
}
