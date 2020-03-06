//
// Default object type
//

package Default_classes;

import Configuration_models.ItemConfig;

public class Item {
    public final String _name;
    public String _description;
    public boolean _isRetriavable;

    public Item(ItemConfig config) {
        if (config == null) throw new IllegalArgumentException("The configuration is empty");
        if (config.Name.isEmpty()) throw  new IllegalArgumentException("The object name is empty");
        if (config.Description.isEmpty()) throw  new IllegalArgumentException("The object description is empty");

        _name = config.Name;
        _description = config.Description;
        _isRetriavable = config.IsRetriavable;

    }



}
