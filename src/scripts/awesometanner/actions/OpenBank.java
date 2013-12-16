package scripts.awesometanner.actions;

import java.util.Arrays;
import java.util.HashMap;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 11/20/2013
 */
public class OpenBank extends Action {
    
    private String untannedHideName;
    private final int bankBoothId = 2196;

    @Override
    public boolean shouldExecute() {
        General.println(Arrays.toString(Objects.find(10, bankBoothId)));
        return Objects.find(10, bankBoothId).length > 0 &&
               Objects.find(10, bankBoothId)[0].isOnScreen() &&
               Inventory.getCount(new String[]{untannedHideName}) <= 0 &&
               !Banking.isBankScreenOpen();
    }

    @Override
    public void execute() {
        if(Banking.openBankBooth()) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Banking.isBankScreenOpen();
                }
            }, 1000);
        }
    }
    
    @Override
    public void initOptions(HashMap<String, String> ops) {
        untannedHideName = ops.get("Untanned hide type");
    }
}
