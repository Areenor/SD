package Default_classes;

import Configuration_models.AdjacentLocationConfig;

public class AdjacentLocation {
    private String _name;
    private String _keyItemName;
    private String _lockedMessage;
    private String _unlockMessage;
    private boolean _locked;
    private boolean _consumesItem;

    public AdjacentLocation(AdjacentLocationConfig config) {
        _name = config.Name;
        _keyItemName = config.KeyItemName;
        _lockedMessage = config.LockedMessage;
        _unlockMessage = config.UnlockMessage;
        _locked = config.Locked;
        _consumesItem = config.ConsumesItem;
    }

    public String GetName() { return _name; }
    public String GetKeyItemName() { return _keyItemName; }
    public String GetLockedMessage() { return _lockedMessage; }
    public String GetUnlockMessage() { return _unlockMessage; }
    public boolean IsLocked() { return _locked; }
    public boolean ConsumesItem() { return  _consumesItem; }

    public void Lock() { _locked = true; }
    public void Unlock() { _locked = false; }
}
