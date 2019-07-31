package excl;

import util.StatUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class for testing purposes.
 */

public class Testing {

    /**
     * Reads the individual pixels of an image
     * and checks the colors of the pixels.
     */
    public void readImg() {
        String hex = "";
        int bound = 0;
        int width = 0;
        try {
            // read image relative to production dir
            BufferedImage img = ImageIO.read(getClass().getResource("bar.png"));
            width = img.getWidth();

            for (int i = 0; i < width; i++) {
                // get pixel color as hex code
                hex = Integer.toHexString(img.getRGB(i, 2));

                if (hex.equals("ff000000")) {
                    bound = i;
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // calculate percentage in bar
        double pMiss = (double) (width-bound) / width;
        double pRemain = 1 - pMiss;

        System.out.println("HP percentage: " + pRemain);
    }

    /**
     * Live test of stat util methods.
     * @throws IOException
     */
    void testStatUtils() throws IOException {
        StatUtil u = new StatUtil();
        System.out.println("Player HP: " + (u.getPlayerHp() * 100) + "%");
        System.out.println("Player MP: " + (u.getPlayerMp() * 100) + "%");
        System.out.println("Player SP: " + (u.getPlayerSp() * 100) + "%");
        System.out.println("Player XP: " + (u.getPlayerXp() * 100) + "%\n");

        System.out.println("Enemy name: " + u.getEnemyName());
        System.out.println("Enemy HP: " + (u.getEnemyHp() * 100) + "%");
        System.out.println("Enemy MP: " + (u.getEnemyMp() * 100) + "%");
        System.out.println("Enemy SP: " + (u.getEnemySp() * 100) + "%");
    }

    void cursorUtilTemplate() {
        /*final int x = (int) (screenWidth * );
        final int y = (int) (screenHeight * );
        final int w = (int) (screenWidth * );
        final int h = (int) (screenHeight * );
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());*/
    }
}
