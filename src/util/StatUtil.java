package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StatUtil {
    // native screen dims
    private double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

    // static height and width for checking status bar values
    private double barWidth = 0.11127379209370425;
    private double barHeight = 0.005208333333333333;

    // bar width is static
    private double barWidthRaw = 152;

    /**
     * Player methods
     */

    /**
     * Gets the player HP as a double value between 0 and 1
     * @return player HP
     */
    public double getPlayerHp() {
        double y = 0.8411458333333334;
        double bound = 0;
        String target = "playerhp.png";

        // scrot player hp bar
        scrotPlayer(y, target);

        // get bound for player hp bar
        bound = getPlayerBounds(target);

        // return player hp as percentage
        return 1 - ((barWidthRaw - bound) / barWidthRaw);
    }

    /**
     * Gets the player MP as a double value between 0 and 1
     * @return player MP
     */
    public double getPlayerMp() {
        double y = 0.87109375;
        double bound = 0;
        String target = "playermp.png";

        // scrot player mp bar
        scrotPlayer(y, target);

        // get bound for player mp bar
        bound = getPlayerBounds(target);

        // return player hp as percentage
        return 1 - ((barWidthRaw - bound) / barWidthRaw);
    }

    /**
     * Gets the player SP as a double value between 0 and 1
     * @return player SP
     */
    public double getPlayerSp() {
        double y = 0.8958333333333334;
        double bound = 0;
        String target = "playersp.png";

        // scrot player sp bar
        scrotPlayer(y, target);

        // get bound for player sp bar
        bound = getPlayerBounds(target);

        // return player sp as percentage
        return 1 - ((barWidthRaw - bound) / barWidthRaw);
    }

    /**
     * Gets the player XP as a double value between 0 and 1
     * @return player XP
     */
    public double getPlayerXp() {
        double y = 0.9244791666666666;
        double bound = 0;
        String target = "playerxp.png";

        // scrot player xp bar
        scrotPlayer(y, target);

        // get bound for player xp bar
        bound = getPlayerBounds(target);

        // return player xp as percentage
        return 1 - ((barWidthRaw - bound) / barWidthRaw);
    }

    /**
     * Enemy methods
     */

    /**
     * Gets the name of the enemy using tesseract CLI
     * @return enemy name as string
     * @throws IOException if file can't be read
     */
    public String getEnemyName() throws IOException {
        // screen coords of enemy name in percentages
        double x = 0.5732064421669106;
        double y = 0.8046875;
        double w = 0.156661786;
        double h = 0.026041667;
        String name = "";

        // take screenshot of enemy name with coords relative to native resolution
        takeScrot(
                (int)(screenWidth * x),
                (int)(screenHeight * y),
                (int)(screenWidth * w),
                (int)(screenHeight * h),
                "enemyname.png"
        );

        // extract text from image using tesseract in shell
        Runtime.getRuntime().exec("tesseract enemyname.png temp.txt");

        // read first line of file, i.e. enemy name
        try (BufferedReader br = new BufferedReader(new FileReader("temp.txt"))) {
            name = br.readLine();
        }

        // delete temp file and scrot
        Files.deleteIfExists(Paths.get("temp.txt"));
        Files.deleteIfExists(Paths.get("enemyname.png"));

        return name;
    }

    /**
     * Gets the enemy HP as a double value between 0 and 1
     * @return enemy HP
     */
    public double getEnemyHp() {
        double y = 0.84765625;
        double bound = 0;
        String target = "enemyhp.png";

        // scrot enemy hp bar
        scrotEnemy(y, target);

        // get bound for enemy hp bar
        // result is inverse, so it is subtracted from static width
        bound = barWidthRaw - getEnemyBounds(target);

        // return enemy hp as percentage
        return 1 - ((barWidthRaw - bound) / barWidthRaw);
    }

    /**
     * Gets the enemy MP as a double value between 0 and 1
     * @return enemy MP
     */
    public double getEnemyMp() {
        double y = 0.8802083333333334;
        double bound = 0;
        String target = "enemymp.png";

        // scrot enemy mp bar
        scrotEnemy(y, target);

        // get bound for enemy mp bar
        // result is inverse, so it is subtracted from static width
        bound = barWidthRaw - getEnemyBounds(target);

        // return enemy mp as percentage
        return 1 - ((barWidthRaw - bound) / barWidthRaw);
    }

    /**
     * Gets the enemy SP as a double value between 0 and 1
     * @return enemy SP
     */
    public double getEnemySp() {
        double y = 0.9114583333333334;
        double bound = 0;
        String target = "enemysp.png";

        // scrot enemy sp bar
        scrotEnemy(y, target);

        // get bound for enemy sp bar
        // result is inverse, so it is subtracted from static width
        bound = barWidthRaw - getEnemyBounds(target);

        // return enemy sp as percentage
        return 1 - ((barWidthRaw - bound) / barWidthRaw);
    }

    /**
     * Helper methods
     */

    /**
     * Takes a scrot of a specific area on screen
     * Scrots player values
     * @param y y point of the screen area
     * @param target name of the output file
     */
    private void scrotPlayer(double y, String target) {
        // static x value for status bar
        double playerBarX = 0.30307467057101023;

        takeScrot(
                (int)(screenWidth * playerBarX),
                (int)(screenHeight * y),
                (int)(screenWidth * barWidth),
                (int)(screenHeight * barHeight),
                target
        );
    }

    /**
     * Gets the amount of horizontal pixels until a black pixel is met
     * Checks player values
     * @param target name of image to check
     * @return count of non-black pixels
     */
    private int getPlayerBounds(String target) {
        int bound = 0;
        try {
            BufferedImage bi = ImageIO.read(new File(target));

            // search image and look for black pixels
            for (int i = 0; i < bi.getWidth(); i++) {
                // if pixel matches, set bound value
                if (pixelIsBlack(Integer.toHexString(bi.getRGB(i, 1)))) {
                    bound = i;
                    break;
                }
            }
            // delete image
            Files.deleteIfExists(Paths.get(target));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bound;
    }

    /**
     * Takes a scrot of a specific area on screen
     * Scrots enemy values
     * @param y y point of the screen area
     * @param target name of the output file
     */
    private void scrotEnemy(double y, String target) {
        // static x value for status bar
        double enemyBarX = 0.5878477306002928;

        takeScrot(
                (int)(screenWidth * enemyBarX),
                (int)(screenHeight * y),
                (int)(screenWidth * barWidth),
                (int)(screenHeight * barHeight),
                target
        );
    }

    /**
     * Gets the amount of horizontal pixels until a black pixel is met
     * Checks enemy values
     * @param target name of image to check
     * @return count of non-black pixels
     */
    private int getEnemyBounds(String target) {
        int bound = 0;
        try {
            BufferedImage bi = ImageIO.read(new File(target));

            // search image and look for black pixels
            // enemy bars are read in reverse, as such 1 is subtracted from width to avoid out of bounds
            for (int i = bi.getWidth() - 1; i > 0; i--) {
                // if pixel matches, set bound value
                if (pixelIsBlack(Integer.toHexString(bi.getRGB(i, 1)))) {
                    bound = i;
                    break;
                }
            }
            // delete file
            Files.deleteIfExists(Paths.get(target));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bound;
    }

    /**
     * Checks a hex color code against an array of black hexes
     * @param hex hex color code
     * @return true or false, determining if the pixel is black or not
     */
    private boolean pixelIsBlack(String hex) {
        String[] gimmickyArrayWithHexColorCodes = {
                "ff000000",
                "ff010101",
                "ff020202",
                "ff030303",
                "ff040404",
                "ff050505",
                "ff060606",
                "ff070707",
                "ff080808",
                "ff090909",
                "ff101010"
        };

        for (String s : gimmickyArrayWithHexColorCodes) {
            if (s.equals(hex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Takes a scrot of a screen area and saves it in project root
     * @param x x point of screen area
     * @param y y point of screen area
     * @param w width of screen area
     * @param h height of screen area
     * @param target name of file
     */
    private void takeScrot(int x, int y, int w, int h, String target) {
        try {
            Robot rob = new Robot();

            BufferedImage img = rob.createScreenCapture(new Rectangle(x, y, w, h));

            ImageIO.write(img, "png", new File(target));
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }
}
