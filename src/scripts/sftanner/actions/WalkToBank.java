package scripts.sftanner.actions;

import java.util.HashMap;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.ext.Doors;
import org.tribot.api2007.types.RSTile;
import scripts.framework.Action;
import scripts.utl.RSUtil;

/**
 * @author Starfox
 * @version 11/29/13
 */
public class WalkToBank extends Action {
    
    private String hideName;
    private String untannedHideName;
    private RSTile dest;
    private RSTile doorPos;
    private RSTile openDoorPos;

    @Override
    public boolean shouldExecute() {
        return !Banking.isBankScreenOpen() &&
               (Inventory.getCount(new String[]{hideName}) > 0 ||
               Inventory.getCount(new String[]{untannedHideName}) <= 0) &&
               dest.getPosition().distanceTo(Player.getPosition()) > 2;
    }

    @Override
    public void execute() {
        if(doorPos != null && Doors.isDoorAt(doorPos, false) && Objects.getAt(doorPos).length > 0) {
            if(Objects.getAt(doorPos)[0].isOnScreen()) {
                if(Doors.handleDoorAt(doorPos, true)) {
                    Timing.waitCondition(new Condition(){
                        @Override
                        public boolean active() {
                            return Doors.isDoorAt(openDoorPos, true);
                        }
                    }, 3500);
                }
            } else {
                PathFinding.aStarWalk(doorPos);
            }
        } else {
            if(Game.getDestination() == null || !RSUtil.isAcceptableDestination(dest)) {
                PathFinding.aStarWalk(dest);
            }
        }
    }

    @Override
    public void initOptions(HashMap<String, String> ops) {
        hideName = ops.get("Hide type");
        untannedHideName = ops.get("Untanned hide type");
        dest = new RSTile(Integer.parseInt(ops.get("Bank x")), Integer.parseInt(ops.get("Bank y")), 0);
        doorPos = new RSTile(Integer.parseInt(ops.get("Door x")), Integer.parseInt(ops.get("Door y")), 0);
        openDoorPos = new RSTile(Integer.parseInt(ops.get("Open door x")), Integer.parseInt(ops.get("Open door y")));
    }
}
