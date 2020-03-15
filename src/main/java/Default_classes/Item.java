//
// Default object type
//

package Default_classes;

import Configuration_models.ItemConfig;
import Game_data.GameState;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;


public class Item {
    public final String _name;
    public String _description;
    public boolean _isRetrievable;

    private TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

    public Item(ItemConfig config) {
        if (config == null) throw new IllegalArgumentException("The configuration is empty");
        if (config.Name.isEmpty()) throw  new IllegalArgumentException("The object name is empty");
        if (config.Description.isEmpty()) throw  new IllegalArgumentException("The object description is empty");

        _name = config.Name;
        _description = config.Description;
        _isRetrievable = config.IsRetriavable;

    }

    public void examine(String item_key){
        terminal.print(GameState.CurrentLocation._items.get(item_key)._description + "\n");
    }

    public void take(String item_key){
        //GameState.MainCharacter._inventory.put(item_key);
        GameState.CurrentLocation._items.remove(item_key);
    }
}
