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
    private final String _name;
    private String _description;
    private boolean _isRetrievable;

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

    public String GetName() {
        return _name;
    }
    public String GetDescription() {
       return _description;
    }
    public boolean IsRetrievable() {
        return _isRetrievable;
    }

    public void SetDescription(String description) {
        _description = description;
    }
    public void SetIsRetrievable(boolean retrievable) { _isRetrievable = retrievable; }

    public void Use() {
        //To be implemented
    }

    public void Use(Item targetItem) {
        //To be implemented
    }

    public void Use(NPC targetNpc) {
        //To be implemented
    }
}
