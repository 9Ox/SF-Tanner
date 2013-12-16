package scripts.awesometanner.actions;

import java.util.HashMap;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;
import scripts.utl.RSUtil;

/**
 * @author Starfox
 * @version 11/21/2013
 */
public class WithdrawHides extends Action {
    
    private String untannedHideName;
    private String energyPotionType;
    private String[] energyPotions;

    @Override
    public boolean shouldExecute() {
        return Banking.isBankScreenOpen() &&
               RSUtil.inventoryContainsOnly("Coins", energyPotions[0], energyPotions[1],
                       energyPotions[2], energyPotions[3]);
    }

    @Override
    public void execute() {
        if(Banking.withdraw(0, untannedHideName)) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Inventory.getCount(new String[]{untannedHideName}) > 0;
                }
            }, 1250);
        }
    }

    @Override
    public void initOptions(HashMap<String, String> ops) {
        untannedHideName = ops.get("Untanned hide type");
        energyPotionType = ops.get("Energy potion");
        if(!energyPotionType.equalsIgnoreCase("None")) {
            energyPotions = new String[4];
            switch(energyPotionType) {
                case "Regular":
                    for(int i = 0; i < energyPotions.length; i++) {
                        energyPotions[i] = "Energy potion (" + (i + 1) + ")";
                    }
                    break;
                case "Super":
                    for(int i = 0; i < energyPotions.length; i++) {
                        energyPotions[i] = "Super energy potion (" + (i + 1) + ")";
                    }
                    break;
                    
            }
        }
    }
}
