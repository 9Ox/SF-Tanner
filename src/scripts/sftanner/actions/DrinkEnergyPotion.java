package scripts.sftanner.actions;

import java.util.HashMap;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 11/20/2013
 */
public class DrinkEnergyPotion extends Action {
    
    private final String[] ENERGY_POTION_NAMES = new String[4];
    private int randomEnergy;

    @Override
    public boolean shouldExecute() {
        return !Banking.isBankScreenOpen() &&
               Inventory.getCount(ENERGY_POTION_NAMES) > 0 &&
               Game.getRunEnergy() <= randomEnergy;
    }

    @Override
    public void execute() {
        if(Inventory.find(ENERGY_POTION_NAMES)[0].click("Drink")) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Game.getRunEnergy() > randomEnergy;
                }
            }, 1250);
        }
    }
    
    @Override
    public void initOptions(HashMap<String, String> ops) {
        randomEnergy = General.random(15, 60);
        switch(ops.get("Energy potion")) {
            case "Regular":
                for(int i = 0; i < ENERGY_POTION_NAMES.length; i++) {
                    ENERGY_POTION_NAMES[i] = "Energy potion (" + (i + 1) + ")";
                }
                return;
            case "Super":
                for(int i = 0; i < ENERGY_POTION_NAMES.length; i++) {
                    ENERGY_POTION_NAMES[i] = "Super energy potion (" + (i + 1) + ")";
                }
        }
    }
}
