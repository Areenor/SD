//
// Default object type
//

package Default_classes;

import Configuration_models.ItemConfig;



public abstract class Item {
    protected final String _name;
    protected String _description;
    protected boolean _isRetrievable;

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

    public void Use() { }

    public void Use(Item targetItem) { }

    public void Use(NPC targetNpc) { }
}
