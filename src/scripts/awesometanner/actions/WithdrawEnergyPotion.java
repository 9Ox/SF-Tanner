/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scripts.awesometanner.actions;

import java.util.HashMap;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.framework.Action;
import scripts.utl.RSUtil;

/**
 * @author Starfox
 * @version 11/20/2013
 */
public class WithdrawEnergyPotion extends Action {
    
    private final String[] ENERGY_POTION_NAMES = new String[4];

    @Override
    public boolean shouldExecute() {
        General.println("shouldActivate withdrawPot");
        return Banking.isBankScreenOpen() &&
               RSUtil.inventoryContainsOnly("Coins");
    }

    @Override
    public void execute() {
        if(Banking.withdraw(1, ENERGY_POTION_NAMES[3])) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Inventory.find(ENERGY_POTION_NAMES).length >= 0;
                }
            }, 1250);
        }
    }

    @Override
    public void initOptions(HashMap<String, String> ops) {
        switch(ops.get("Energy potion")) {
            case "Regular":
                for(int i = 0; i < ENERGY_POTION_NAMES.length; i++) {
                    ENERGY_POTION_NAMES[i] = "Energy potion (" + (i + 1) + ")";
                }
                return;
            case "Super":
                for(int i = 0; i < ENERGY_POTION_NAMES.length; i++) {
                    ENERGY_POTION_NAMES[i] = "Super potion (" + (i + 1) + ")";
                }
        }
    }
}
