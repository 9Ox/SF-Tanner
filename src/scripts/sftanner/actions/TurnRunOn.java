package scripts.sftanner.actions;

import java.util.HashMap;
import org.tribot.api2007.Game;
import scripts.framework.Action;
import scripts.utl.DynamicVariables;
import scripts.utl.RSUtil;

/**
 * @author Starfox
 * @version 12/6/13
 */
public class TurnRunOn extends Action {

    @Override
    public boolean shouldExecute() {
        return Game.getRunEnergy() >= DynamicVariables.energyNeeded &&
               !Game.isRunOn();
    }

    @Override
    public void execute() {
        RSUtil.turnRunOn(true);
    }

    @Override
    public void initOptions(HashMap<String, String> ops) {
        
    }
}
