package scripts.sftanner.actions;

import java.util.HashMap;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 11/20/2013
 */
public class DepositEmptyVial extends Action {
    
    private final String[] VIAL_NAMES = {"Empty vial"};

    @Override
    public boolean shouldExecute() {
        General.println("shouldActivate depositVial");
        return Banking.isBankScreenOpen() &&
               Inventory.getCount(VIAL_NAMES) > 0;
    }

    @Override
    public void execute() {
        if(Banking.deposit(0, "Empty vial")) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Inventory.getCount(VIAL_NAMES) <= 0;
                }
            }, 1000);
        }
    }

    @Override
    public void initOptions(HashMap<String, String> ops) {
        
    }
}
