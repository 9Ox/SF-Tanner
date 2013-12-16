package scripts.awesometanner.actions;

import java.util.HashMap;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 11/20/2013
 */
public class DepositHides extends Action {
    
    private String tannedHideName;

    @Override
    public boolean shouldExecute() {
        return Banking.isBankScreenOpen() &&
               Inventory.getCount(new String[]{tannedHideName}) > 0;
    }

    @Override
    public void execute() {
        if(Banking.deposit(0, tannedHideName)) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Inventory.getCount(new String[]{tannedHideName}) <= 0;
                }
            }, 1000);
        }
    }
    
    @Override
    public void initOptions(HashMap<String, String> ops) {
        tannedHideName = ops.get("Hide type");
    }
}
