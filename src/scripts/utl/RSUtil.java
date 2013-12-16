package scripts.utl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.tribot.api.General;
import org.tribot.api2007.Game;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSTile;

/**
 * @author Starfox 
 * @version 11/20/2013
 */
public class RSUtil {
    
    /**
     * Turns run on or off.
     * @param active true to turn on, false to turn off
     * @return true if run was successfully turned on/off; false otherwise
     */
    public static boolean turnRunOn(final boolean active) {
        if(active) {
            if(Game.isRunOn()) {
                return true;
            }
            if(GameTab.getOpen() != GameTab.TABS.OPTIONS) {
                if(GameTab.open(GameTab.TABS.OPTIONS)) {
                    return Interfaces.get(261, 34).click("Toggle Run");
                }
            }
        } else {
            if(!Game.isRunOn()) {
                return true;
            }
            if(GameTab.getOpen() != GameTab.TABS.OPTIONS) {
                if(GameTab.open(GameTab.TABS.OPTIONS)) {
                    return Interfaces.get(261, 34).click("Toggle Run");
                }
            }
        }
        return false;
    }
    
    /**
     * Gets the the Image from the specified url.
     * @param url The url to get the Image from.
     * @return The Image retrieved; null if no Image was found. 
     */
    public static Image getImageFromUrl(final String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            General.println("Failed to retreive image from: " + url);
            return null;
        }
    }
    
    /**
     * Checks to see if the inventory contains only the RSItems that
     * have the same names as the Strings specified.
     * @param names The names to check for.
     * @return true if the inventory contains only the RSItems with the names
     * specified; false otherwise
     */
    public static boolean inventoryContainsOnly(final String... names) {
        if(Inventory.getAll() != null) {
            return Inventory.getAll().length <= Inventory.find(names).length;
        }
        return false;
        /*final int inventoryCount = Inventory.getAll().length;
        int count = 0;
        for(RSItem rsItem : Inventory.getAll()) {
            for(String string : names) {
                if(string.equalsIgnoreCase(rsItem.getDefinition().getName())) {
                    count++; break;
                }
            }
        }
        return inventoryCount == count;*/
    }
    
    public static RSTile[] getAdjacentTiles(final RSTile tile) {
        return new RSTile[]{ tile,
            new RSTile(tile.getX() + 1, tile.getY(), tile.getPlane()),
            new RSTile(tile.getX(), tile.getY() + 1, tile.getPlane()),
            new RSTile(tile.getX() + 1, tile.getY() + 1, tile.getPlane()),
            new RSTile(tile.getX() - 1, tile.getY(), tile.getPlane()),
            new RSTile(tile.getX(), tile.getY() - 1, tile.getPlane()),
            new RSTile(tile.getX() - 1, tile.getY() - 1, tile.getPlane()),
            new RSTile(tile.getX() + 1, tile.getY() - 1, tile.getPlane()),
            new RSTile(tile.getX() - 1, tile.getY() + 1, tile.getPlane())
        };
    }
    
    public static boolean isAdjacentTile(final RSTile tile1, final RSTile tile2) {
        if(tile1 == null || tile2 == null) {
            return false;
        }
        for(RSTile t : getAdjacentTiles(tile1)) {
            if(t.equals(tile2)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isAcceptableDestination(final RSTile dest) {
        return Game.getDestination() != null && isAdjacentTile(dest, Game.getDestination());
    }
    
    public static void drawBasicPaint(Graphics2D g, Font font, Color fontColor) {
        
    }
    
    public static long getPerHourValue(final int value, final long startTime) {
        return (long)(value * 3600000D / (System.currentTimeMillis() - startTime));    
    }
}
