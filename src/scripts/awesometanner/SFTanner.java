package scripts.awesometanner;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MousePainting;
import org.tribot.script.interfaces.Painting;
import scripts.framework.Initializer;
import scripts.utl.DynamicVariables;
import scripts.utl.RSUtil;

/**
 * @author Starfox
 * @version 11/20/2013
 */
@ScriptManifest(authors = "Startfox", name = "SF Tanner", category = "Money Making", description = 
        "Tans hides in Al-Kharid or Canafis.")
public class SFTanner extends Script implements Painting, MousePainting {
    private final String CURSOR_IMAGE_URL = "http://i.imgur.com/NSzdgpX.png";
    private Image cursorImage;
    private Font paintFont;
    private Color fontColor;
    
    /*private ZybezRSItem tannedHide;
    private ZybezRSItem untannedHide;*/
    
    private long startTime;
    
    @Override
    public void run() {
        Mouse.setSpeed(132);
        fontColor = new Color(245, 245, 247, 210);
        paintFont = new Font("Segoe UI", Font.PLAIN, 12);
        cursorImage = RSUtil.getImageFromUrl(CURSOR_IMAGE_URL);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SFTannerGui("Thank you for choosing SF Tanner.").setVisible(true);
            }
        });
        startTime = System.currentTimeMillis();
        Initializer.initialize(25);
    }

    @Override
    public void paintMouse(Graphics grphcs, Point point, Point point1) {
        Graphics2D g = (Graphics2D)grphcs;
        g.drawImage(cursorImage, point.x - 1, point.y, null);
    }

    @Override
    public void onPaint(Graphics grphcs) {
        Graphics2D g = (Graphics2D)grphcs;
        g.setFont(paintFont);
        g.setColor(new Color(0, 0, 0, 190));
        g.fillRect(2, 2, 130, 80);
        g.setColor(Color.BLACK);
        g.drawRect(2, 2, 130, 80);
        g.setColor(fontColor);
        g.drawString("SF Tanner", 41, 15);
        g.drawString("_____________", 34, 18);
        g.drawString("Time ran: " + Timing.msToString(System.currentTimeMillis() - startTime), 7, 35);
        g.drawString("Tanned (/h): " + DynamicVariables.count + " (" + RSUtil.getPerHourValue(DynamicVariables.count, startTime) + ")", 7, 50);
        if(DynamicVariables.profitPerHide > 0) {
            g.drawString("Profit (/h): " + getProfit() + " (" + RSUtil.getPerHourValue(getProfit(), startTime) + ")", 7, 65);
        } else {
            g.drawString("Profit (/h): n/a (n/a)", 7, 65);
        }
        g.drawString("v1.1", 109, 78);
    }
    
    private int getProfit() {
        DynamicVariables.profit = DynamicVariables.count * (DynamicVariables.profitPerHide - DynamicVariables.costPerHide);
        return DynamicVariables.profit;
    }
}
