package Default_classes;

import Configuration_models.LocationConfig;
import Services.InitiationService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import Enumerators.DirectionEnum;

import java.lang.invoke.SwitchPoint;
import java.util.HashMap;
import java.util.Map;

public class Location {
    public final String _name;
    public String _baseDescription;
    public Map<String, Item> _items = new HashMap<String, Item>();
    public Map<String, NPC> _NPCs = new HashMap<String, NPC>();
    public Map<DirectionEnum, String> _adjacentLocations = new HashMap<DirectionEnum, String>();

    private TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

    public Location(LocationConfig config) {
        if (config == null) throw new IllegalArgumentException("The configuration is empty");
        if (config.Name.isEmpty()) throw new IllegalArgumentException("The location name is empty");
        if (config.Description.isEmpty()) throw new IllegalArgumentException("The location description is empty");

        _name = config.Name;
        _baseDescription = config.Description;
        _adjacentLocations = config.AdjacentLocations;

        for (String character : config.Characters) {
            _NPCs.put(character, InitiationService.InitiateCharacter(character));
        }
        for (String items : config.Items) {
            _items.put(items, InitiationService.InitiateItem(items));
        }
    }

    public String GetName() { return _name; }
    public String GetDescription(){ return (_baseDescription + GetItemDescriptions() + GetNpcDescriptions()); }
    public Map<DirectionEnum, String> GetAdjacentLocations() {
        return _adjacentLocations;
    }
    public NPC GetNpc(String NpcName){ return _NPCs.get(NpcName); }
    public Item GetItem(String ItemName){
        return _items.get(ItemName);
    }

    public void SetBaseDescription(String description) { _baseDescription = description; }
    public void SetAdjacentLocation(DirectionEnum direction, String locationName) { _adjacentLocations.replace(direction, locationName); }
    public void SetNpc(String NpcName, NPC npc) { _NPCs.replace(NpcName, npc); }
    public void SetItem(String ItemName, Item item){
        _items.replace(ItemName, item);
    }

    public void AddNpc(String NpcName, NPC npc) { _NPCs.put(NpcName, npc); }
    public void AddItem(String ItemName, Item item){
        _items.put(ItemName, item);
    }

    private String GetItemDescriptions() {
        String itemDescriptions = "";
        for(Item item : _items.values()) {
            itemDescriptions = itemDescriptions + item.GetDescription();
        }
        return itemDescriptions;
    }

    private String GetNpcDescriptions() {
        String NpcDescriptions = "";
        for(NPC NPC: _NPCs.values()) {
            NpcDescriptions = NpcDescriptions + NPC.GetDescription();
        }
        return NpcDescriptions;
    }
}
