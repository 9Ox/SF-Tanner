package scripts.sftanner.actions;

import java.util.HashMap;
import org.tribot.api.DynamicClicking;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;
import scripts.framework.Action;

/**
 * @author Starfox
 * @version 11/29/13
 */
public class OpenTanningInterface extends Action {
    
    private RSNPC[] npcs;
    private RSNPC tanner;
    private String untannedHideName;
    private String tannerName;
    private RSInterface child;
    private RSTile dest;

    @Override
    public boolean shouldExecute() {
        this.npcs = NPCs.find(tannerName);
        return Inventory.getCount(new String[]{untannedHideName}) > 0 &&
               npcs.length > 0 &&
               (child == null || child.isHidden()) &&
               dest.distanceTo(Player.getPosition()) <= 3;
    }

    @Override
    public void execute() {
        tanner = npcs[0];
        if(tanner.isOnScreen()) {
            if(DynamicClicking.clickRSNPC(tanner, "Trade")) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return (child != null && !child.isHidden()) || !Player.isMoving();
                    }
                }, 4000);
            }
        } else {
            Camera.turnToTile(tanner);
            if(!tanner.isOnScreen()) {
                PathFinding.aStarWalk(tanner);
            }
        }
    }
    
    @Override
    public void initOptions(HashMap<String, String> ops) {
        this.untannedHideName = ops.get("Untanned hide type");
        this.dest = new RSTile(3274, 3191, 0);
        this.tannerName = ops.get("Tanner name");
    }
}
