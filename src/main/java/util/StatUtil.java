package util;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StatUtil {
    private final TessUtil tess = new TessUtil();
    private final Util util = new Util();

    public StatUtil() throws IOException, AWTException {
    }

    /**
     * Player methods
     */

    /**
     * Gets the player HP as a double value between 0 and 1
     * @return player HP
     */
    public double getPlayerHp() throws IOException, AWTException {
        JSONObject hp = util.getDimsAsJSONObject("combat/statusbar/player/hp");
        double playerHp = getPlayerPercentage(hp) * 100;
        return playerHp;
    }

    /**
     * Gets the player MP as a double value between 0 and 1
     * @return player MP
     */
    public double getPlayerMp() throws IOException, AWTException {
        JSONObject mp = util.getDimsAsJSONObject("combat/statusbar/player/mp");
        return getPlayerPercentage(mp) * 100;
    }

    /**
     * Gets the player SP as a double value between 0 and 1
     * @return player SP
     */
    public double getPlayerSp() throws IOException, AWTException {
        JSONObject sp = util.getDimsAsJSONObject("combat/statusbar/player/sp");
        double playerSp = getPlayerPercentage(sp) * 100;
        return playerSp;
    }

    /**
     * Gets the player XP as a double value between 0 and 1
     * @return player XP
     */
    public double getPlayerXp() throws IOException, AWTException {
        JSONObject xp = util.getDimsAsJSONObject("combat/statusbar/player/xp");
        return getPlayerPercentage(xp) * 100;
    }

    /**
     * Enemy methods
     */

    /**
     * Gets the name of the enemy using tesseract
     * @return enemy name as string
     * @throws IOException if file can't be read
     */
    public String getEnemyName() throws IOException, InterruptedException, AWTException {
        JSONObject enemyNameJson = util.getDimsAsJSONObject("combat/enemyname");
        BufferedImage bi = util.takeScrot(enemyNameJson);
        return tess.getImageText(bi);
    }

    /**
     * Gets the enemy HP as a double value between 0 and 1
     * @return enemy HP
     */
    public double getEnemyHp() throws IOException, AWTException {
        JSONObject hp = util.getDimsAsJSONObject("combat/statusbar/enemy/hp");
        return getEnemyPercentage(hp) * 100;
    }

    /**
     * Gets the enemy MP as a double value between 0 and 1
     * @return enemy MP
     */
    public double getEnemyMp() throws IOException, AWTException {
        JSONObject mp = util.getDimsAsJSONObject("combat/statusbar/enemy/mp");
        return getEnemyPercentage(mp) * 100;
    }

    /**
     * Gets the enemy SP as a double value between 0 and 1
     * @return enemy SP
     */
    public double getEnemySp() throws IOException, AWTException {
        JSONObject sp = util.getDimsAsJSONObject("combat/statusbar/enemy/sp");
        return getEnemyPercentage(sp) * 100;
    }

    /**
     * Helper methods
     */

    /**
     * Gets the amount of horizontal pixels until a black pixel is met
     * Checks player values
     */
    private double getPlayerPercentage(JSONObject obj) throws AWTException, IOException {
        double bound = 0;
        BufferedImage bi = util.takeScrot(obj);
        double width = (double) bi.getWidth();

        // search image and look for black pixels
        for (int i = 1; i < bi.getWidth(); i++) {
            // if pixel matches, set bound value
            if (util.pixelIsBlack(Integer.toHexString(bi.getRGB(i, 1)))) {
                bound = i;
                break;
            }
        }
        double percentage = ((width - bound) / width);

        if (percentage == 1) {
            return 1;
        } else {
            return 1 - percentage;
        }
    }

    /**
     * Gets the amount of horizontal pixels until a black pixel is met
     * Checks enemy values
     */
    private double getEnemyPercentage(JSONObject obj) throws AWTException, IOException {
        double bound = 0;
        BufferedImage bi = util.takeScrot(obj);
        double width = bi.getWidth();

        // search image and look for black pixels
        // enemy bars are read in reverse, as such 1 is subtracted from width to avoid out of bounds
        for (int i = bi.getWidth() - 1; i > 0; i--) {
            // if pixel matches, set bound value
            if (util.pixelIsBlack(Integer.toHexString(bi.getRGB(i, 1)))) {
                bound = i;
                break;
            }
        }

        return ((width - bound) / width);
    }
}
