//
// Default object type
//

package Default_classes;

import Configuration_models.ObjectConfig;

public class Object {
    public final String _name;
    public String _description;
    public boolean _isItem = false;
    public boolean _isClimbable = false;

    public  Object(ObjectConfig config) {
        if (config == null) throw new IllegalArgumentException("The configuration is empty");
        if (config.Name.isEmpty()) throw  new IllegalArgumentException("The object name is empty");
        if (config.Description.isEmpty()) throw  new IllegalArgumentException("The object description is empty");

        _name = config.Name;
        _description = config.Description;
        _isItem = config.IsItem;
        _isClimbable = config.IsClimbable;
    }

    public void Use() {
        //print "nothing happened"
    }

    public void Interact(String target) {
        //print "nothing happened"
    }

    public void Climb() {
        if (_isClimbable) {
            //not implemented
            return;
        }
        //print "nothing happened"
    }
}
