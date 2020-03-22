//
// Default consumable
//

package Default_classes;

import Configuration_models.ConsumConfig;
import Configuration_models.ItemConfig;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import Enumerators.StatEnum;
import Game_data.GameState;

public class Consumable extends Item{
    private int _statChange;
    private boolean _isDangerous;
    private StatEnum _affectedStat;

    private TextIO textIO = TextIoFactory.getTextIO(); //for reading input and selecting values, output optional
    private TextTerminal terminal = textIO.getTextTerminal(); //strictly for output

    public Consumable(ItemConfig config, ConsumConfig addon) {
        super(config);
        _statChange = addon.StatChange;
        _isDangerous = addon.IsDangerous;
        _affectedStat = addon.AffectedStat;
    }

    @Override
    public void Use() {
        if (!_isDangerous) {
            int oldStat;
            switch (_affectedStat){
                case Strength:
                    oldStat = GameState.MainCharacter.GetStrength();
                    GameState.MainCharacter.SetStrength(oldStat + _statChange);
                    break;
                case Dexterity:
                    oldStat = GameState.MainCharacter.GetDexterity();
                    GameState.MainCharacter.SetDexterity(oldStat + _statChange);
                    break;
                case Constitution:
                    oldStat = GameState.MainCharacter.GetConstitution();
                    GameState.MainCharacter.SetConstitution(oldStat + _statChange);
                    break;
                case HitPoints:
                    oldStat = GameState.MainCharacter.GetHitPoints();
                    GameState.MainCharacter.SetHitPoints(oldStat + _statChange);
                    break;
                case Attack:
                    oldStat = GameState.MainCharacter.GetAttack();
                    GameState.MainCharacter.SetAttack(oldStat + _statChange);
                    break;
                case Stamina:
                    oldStat = GameState.MainCharacter.GetStamina();
                    GameState.MainCharacter.SetStamina(oldStat + _statChange);
                    break;
                default:
                    throw new IllegalArgumentException("The configuration is empty, misses AffectedStat");
            }
            terminal.printf("You consumed the item and raised your %s with %d.\n", _affectedStat, _statChange);
            GameState.MainCharacter.RemoveFromInventory(_name);
        }
        else {
            terminal.print("This is a consumable item is dangerous to use on yourself. Use it on someone else.\n");
        }
    }

    @Override
    public void Use(Item targetItem) {
        terminal.print("This is a consumable item. Consumable items can only be used on yourself or someone else.\n");
    }

    @Override
    public void Use(NPC targetNpc) {
        if (_isDangerous) {
            int oldStat;
            switch (_affectedStat){
                case Strength:
                    oldStat = targetNpc.GetStrength();
                    targetNpc.SetStrength(oldStat + _statChange);
                    break;
                case Dexterity:
                    oldStat = targetNpc.GetDexterity();
                    targetNpc.SetDexterity(oldStat + _statChange);
                    break;
                case Constitution:
                    oldStat = targetNpc.GetConstitution();
                    targetNpc.SetConstitution(oldStat + _statChange);
                    break;
                case HitPoints:
                    oldStat = targetNpc.GetHitPoints();
                    targetNpc.SetHitPoints(oldStat + _statChange);
                    break;
                case Attack:
                    oldStat = targetNpc.GetAttack();
                    targetNpc.SetAttack(oldStat + _statChange);
                    break;
                case Stamina:
                    oldStat = targetNpc.GetStamina();
                    targetNpc.SetStamina(oldStat + _statChange);
                    break;
                default:
                    throw new IllegalArgumentException("The configuration is empty, misses AffectedStat");
            }
            terminal.printf("You used the consumable item on %s and changed their %s with %d.\n", targetNpc.GetName(), _affectedStat, _statChange);
            GameState.MainCharacter.RemoveFromInventory(_name);
        }
        else {
            terminal.print("This is a consumable item is helpful to you, use it on yourself.\n");
        }
    }
}
