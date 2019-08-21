package util;

import common.RectDimension;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utilities to check the screen for various texts and images.
 */
public class ScreenUtil {
    private final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final TessUtil tess = new TessUtil();
    private final Util util = new Util();

    public ScreenUtil() throws AWTException, IOException {}

    /**
     * Checks if it is the players turn
     * @return
     * @throws IOException, AWTException
     */
    public boolean checkTurn() throws IOException, AWTException {
        JSONObject attackBtn = util.getDimsAsJSONObject("combat/menu/attack");
        BufferedImage img = util.takeScrot(attackBtn);
        System.out.println(tess.getImageText(img));
        return tess.getImageText(img).contains("Attack");
    }

    public boolean checkIfWin() throws IOException, AWTException {
        JSONObject winBtn = util.getDimsAsJSONObject("combat/victory/confirm");
        BufferedImage img = util.takeScrot(winBtn);
        return tess.getImageText(img).contains("Next");
    }

    public boolean checkZTokens() throws IOException, AWTException {
        JSONObject zTokenBtn = util.getDimsAsJSONObject("combat/ztoken/confirm");
        BufferedImage img = util.takeScrot(zTokenBtn);
        return tess.getImageText(img).contains("Next");
    }

    public boolean checkIfDead() throws IOException, AWTException {
        JSONObject deadBtn = util.getDimsAsJSONObject("combat/death/confirm");
        BufferedImage img = util.takeScrot(deadBtn);
        return tess.getImageText(img).contains("Next");
    }

    public boolean checkIfLevelUp() throws IOException, AWTException {
        JSONObject levelUpBtn = util.getDimsAsJSONObject("combat/levelup/next");
        BufferedImage img = util.takeScrot(levelUpBtn);
        return tess.getImageText(img).contains("Next");
    }

    public int getZtokens() throws IOException, AWTException {
        JSONObject zTokenField = util.getDimsAsJSONObject("combat/ztoken/reward");
        BufferedImage img = util.takeScrot(zTokenField);
        String result = tess.getImageText(img);
        String zTokens = result.replaceAll("[^0-9]", "");
        return Integer.parseInt(zTokens);
    }

    public int getPlayerHealthPotionsTown() throws IOException, AWTException {
        JSONObject healthPotions = util.getDimsAsJSONObject("town/healthpotions");
        BufferedImage img = util.takeScrot(healthPotions);
        return Integer.parseInt(tess.getImageText(img));
    }

    public int getPlayerManaPotionsTown() throws IOException, AWTException {
        JSONObject manaPotions = util.getDimsAsJSONObject("town/manapotions");
        BufferedImage img = util.takeScrot(manaPotions);
        return Integer.parseInt(tess.getImageText(img));
    }

    public String getBattleRewards() throws IOException, AWTException {
        JSONObject victoryRewardField = util.getDimsAsJSONObject("combat/victory/reward");
        BufferedImage img = util.takeScrot(victoryRewardField);
        return tess.getImageText(img);
    }

    public String getEnemyAffinities() throws IOException, AWTException, InterruptedException {
        CursorUtil cursor = new CursorUtil();
        JSONObject enemyAffinities = util.getDimsAsJSONObject("enemy/affinities");
        cursor.hoverEnemy();
        BufferedImage img = util.takeScrot(enemyAffinities);
        cursor.clearAvatar();
        return tess.getImageText(img);
    }

    /**
     * Gets the hex code of one static pixel
     * used to check if load screen is present
     * @return
     */
    public String getLoadScreenPixelHex() throws IOException, AWTException {
        JSONObject loadScreenPixel = util.getDimsAsJSONObject("combat/loadscreenpixel");
        BufferedImage img = util.takeScrot(loadScreenPixel);
        return Integer.toHexString(img.getRGB(0,0));
    }

    public RectDimension getGameWindowScreen() throws AWTException {
        Robot rob = new Robot();
        BufferedImage screen = rob.createScreenCapture(
                new Rectangle(0, 0, (int) screenWidth, (int) screenHeight));
        int xStart = 0;
        int yStart = 0;
        int xEnd = 0;
        int yEnd = 0;
        int width = 0;
        int height = 0;

        // label for the outer loop, to facilitate breaking both loops.
        outerlooptop:
        // screen height is reduced to avoid checking redundant pixels
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight / 4; y++) {
                // if the current, and the following four pixels are all black,
                // it is safe to assume, that we have entered the game region.
                if (Integer.toHexString(screen.getRGB(x, y)).equals("ff000000") &&
                        Integer.toHexString(screen.getRGB(x, y + 1)).equals("ff000000") &&
                        Integer.toHexString(screen.getRGB(x, y + 2)).equals("ff000000") &&
                        Integer.toHexString(screen.getRGB(x, y + 3)).equals("ff000000") &&
                        Integer.toHexString(screen.getRGB(x, y + 4)).equals("ff000000")) {
                    xStart = x;
                    yStart = y;
                    break outerlooptop;
                }
            }
        }

        outloopbottom:
        for (int x = (int) screenWidth - 1; x > 0; x--) {
            for (int y = (int) screenHeight - 1; y > (int) screenHeight * 0.75; y--) {
                if (Integer.toHexString(screen.getRGB(x, y)).equals("ff000000") &&
                        Integer.toHexString(screen.getRGB(x, y - 1)).equals("ff000000") &&
                        Integer.toHexString(screen.getRGB(x, y - 2)).equals("ff000000") &&
                        Integer.toHexString(screen.getRGB(x, y - 3)).equals("ff000000") &&
                        Integer.toHexString(screen.getRGB(x, y - 4)).equals("ff000000")) {
                    xEnd = x;
                    yEnd = y;
                    break outloopbottom;
                }
            }
        }

        int widthIndentRight = (int) screenWidth - xEnd;
        width = (int) screenWidth - xStart - widthIndentRight;

        int heightIndentBottom = (int) screenHeight - yEnd;
        height = (int) screenHeight - yStart - heightIndentBottom;

        return new RectDimension(
                xStart,
                yStart,
                width,
                height,
                (xStart / screenWidth),
                (yStart / screenHeight),
                (width / screenWidth),
                (height / screenHeight),
                widthIndentRight,
                heightIndentBottom,
                new Dimension(
                        (int) screenWidth,
                        (int) screenHeight
                )
        );
    }


}
