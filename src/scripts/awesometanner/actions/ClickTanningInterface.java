/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scripts.awesometanner.actions;

import java.util.HashMap;
import org.tribot.api.General;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;
import scripts.utl.DynamicVariables;

/**
 * @author Starfox
 * @version 11/21/2013
 */
public class ClickTanningInterface extends Action {
    
    private String hideName;
    private int child;

    @Override
    public boolean shouldExecute() {
        return Interfaces.get(324, child) != null &&
               !Interfaces.get(324, child).isHidden() &&
               Inventory.getCount(new String[]{hideName}) <= 0;
    }

    @Override
    public void execute() {
        if(Interfaces.get(324, child).click("Tan All")) {
            final int startCount = Inventory.getCount(new String[]{hideName});
            General.println(startCount);
            for(int i = 0; i < 200; i++) {
                if(Inventory.getCount(new String[]{hideName}) > startCount) {
                    DynamicVariables.count += Inventory.getCount(new String[]{hideName});
                    return;
                }
                General.sleep(10);
            }
        }
    }

    @Override
    public void initOptions(HashMap<String, String> ops) {
        hideName = ops.get("Hide type");
        child = Integer.parseInt(ops.get("Child interface id"));
    }
}
