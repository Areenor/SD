package Default_classes;

import Configuration_models.LocationConfig;
import Game_data.GameState;
import Services.InitiationService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import Enumerators.DirectionEnum;

import java.util.HashMap;
import java.util.Map;

public class Location {
    public final String _name;
    public String _description;
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
        _description = config.Description;
        _adjacentLocations = config.AdjacentLocations;

        for (String character : config.Characters) {
            _NPCs.put(character, InitiationService.InitiateCharacter(character));
        }
        for (String items : config.Items) {
            _items.put(items, InitiationService.InitiateItem(items));
        }
    }

    public String ReturnLocationName() {
        return _name;
    }

    public Map<DirectionEnum, String> ReturnAdjacentLocations() {
        return _adjacentLocations;
    }

    public String ReturnLocationDescription(){
        return _description;
    }

    public String ReturnResidingItemsDescriptions() {
        String itemDescriptions = "";
        for(Item item : _items.values()) {
            itemDescriptions = itemDescriptions + item.ReturnItemDescription();
        }
        return itemDescriptions;
    }
    public String ReturnResidingNPCDescriptions() {
        String NPCDescriptions = "";
        for(NPC NPC: _NPCs.values()) {
            NPCDescriptions = NPCDescriptions + NPC.ReturnNPCDescription();
        }
        return NPCDescriptions;
    }

    public NPC ReturnResidingNPC(String NPCName){
        return _NPCs.get(NPCName);
    }

    public Item ReturnResidingItem(String ItemName){
        return _items.get(ItemName);
    }
}
