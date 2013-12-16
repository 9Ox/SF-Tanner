package scripts.awesometanner.actions;

import java.util.HashMap;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Game;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.ext.Doors;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSTile;
import scripts.framework.Action;
import scripts.utl.RSUtil;

/**
 * @author Starfox
 * @version 11/29/13
 */
public class WalkToTanner extends Action {

    private String untannedHideName;
    private RSInterface master;
    private RSTile dest;
    private RSTile doorPos;
    private RSTile openDoorPos;

    @Override
    public boolean shouldExecute() {
        return Inventory.getCount(new String[]{untannedHideName}) > 0
               && (master == null || master.isHidden())
               && dest.getPosition().distanceTo(Player.getPosition()) > 2;
    }

    @Override
    public void execute() {
        if(doorPos != null && Doors.isDoorAt(doorPos, false) && Objects.getAt(doorPos).length > 0) {
            if(Objects.getAt(doorPos)[0].isOnScreen()) {
                if(Doors.handleDoorAt(doorPos, true)) {
                    Timing.waitCondition(new Condition() {
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
        untannedHideName = ops.get("Untanned hide type");
        master = Interfaces.get(324);
        dest = new RSTile(Integer.parseInt(ops.get("Tanning x")), Integer.parseInt(ops.get("Tanning y")), 0);
        doorPos = new RSTile(Integer.parseInt(ops.get("Door x")), Integer.parseInt(ops.get("Door y")));
        openDoorPos = new RSTile(Integer.parseInt(ops.get("Open door x")), Integer.parseInt(ops.get("Open door y")));
    }
}
