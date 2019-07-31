package util;

import com.github.joonasvali.naturalmouse.api.MouseMotionFactory;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

public class CursorUtil {
    private final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

    MouseMotionFactory m;
    Robot rob;

    public CursorUtil() throws AWTException {
        this.m = new MouseMotionFactory();
        this.rob = new Robot();
    }

    /**
     * Performs an attack in the game
     * @throws InterruptedException
     */
    public void attack() throws InterruptedException {
        final int x = (int) (screenWidth * 0.44216691068814057);
        final int y = (int) (screenHeight * 0.3033854166666667);
        final int w = (int) (screenWidth * 0.1171303074670571);
        final int h = (int) (screenHeight * 0.06380208333333333);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);

        moveAndClick(p.getX(), p.getY());
    }

    public void allyAssist(String ally) throws InterruptedException {
        final int x = (int) (screenWidth * 0.44216691068814057);
        final int y = (int) (screenHeight * 0.375);
        final int w = (int) (screenWidth * 0.116398243045388);
        final int h = (int) (screenHeight * 0.03125);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());

        switch (ally) {
            case "artix":
                artixAlly();
                break;
            case "galanoth":
                galanothAlly();
                break;
            case "aquella":
                aquellaAlly();
                break;
            case "zorbak":
                zorbakAlly();
                break;
            case "robina":
                robinaAlly();
                break;
            default:
                cancelAlly();
                break;
        }
    }


    /**
     * Ally assist helper methods
     */

    private void artixAlly() throws InterruptedException {
        final int x = (int) (screenWidth * 0.42825768667642755);
        final int y = (int) (screenHeight * 0.19661458333333334);
        final int w = (int) (screenWidth * 0.1398243045387994);
        final int h = (int) (screenHeight * 0.052083333333333336);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);

        moveAndClick(p.getX(), p.getY());
    }

    private void galanothAlly() throws InterruptedException {
        final int x = (int) (screenWidth * 0.42972181551976574);
        final int y = (int) (screenHeight * 0.29296875);
        final int w = (int) (screenWidth * 0.13689604685212298);
        final int h = (int) (screenHeight * 0.05078125);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);

        moveAndClick(p.getX(), p.getY());
    }

    private void aquellaAlly() throws InterruptedException {
        final int x = (int) (screenWidth * 0.42825768667642755);
        final int y = (int) (screenHeight * 0.3880208333333333);
        final int w = (int) (screenWidth * 0.1376281112737921);
        final int h = (int) (screenHeight * 0.052083333333333336);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);

        moveAndClick(p.getX(), p.getY());
    }

    private void zorbakAlly() throws InterruptedException {
        final int x = (int) (screenWidth * 0.4267935578330893);
        final int y = (int) (screenHeight * 0.484375);
        final int w = (int) (screenWidth * 0.14202049780380674);
        final int h = (int) (screenHeight * 0.049479166666666664);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);

        moveAndClick(p.getX(), p.getY());
    }

    private void robinaAlly() throws InterruptedException {
        final int x = (int) (screenWidth * 0.42825768667642755);
        final int y = (int) (screenHeight * 0.5768229166666666);
        final int w = (int) (screenWidth * 0.1376281112737921);
        final int h = (int) (screenHeight * 0.052083333333333336);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);

        moveAndClick(p.getX(), p.getY());
    }

    private void cancelAlly() throws InterruptedException {
        final int x = (int) (screenWidth * 0.4267935578330893);
        final int y = (int) (screenHeight * 0.6692708333333334);
        final int w = (int) (screenWidth * 0.1478770131771596);
        final int h = (int) (screenHeight * 0.03515625);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);

        moveAndClick(p.getX(), p.getY());
    }

    /**
     * General helper methods
     */

    /**
     * Moves cursor and simulates a click
     * @param x coordinate of the point to click
     * @param y coordinate of the point to click
     * @throws InterruptedException
     */
    private void moveAndClick(double x, double y) throws InterruptedException {
        this.m.move(
                (int) x,
                (int) y
        );
        click();
    }

    /**
     * Simulates a mouse click
     * @throws InterruptedException
     */
    private void click() throws InterruptedException {
        this.rob.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(200);
        this.rob.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }


    /**
     * Gets a random point within a rectangle
     * @param rect rectangle to find a point within
     * @return random point object
     */
    private Point getRandomPoint(Rectangle rect) {
        int minX = (int) rect.getX();
        int minY = (int) rect.getY();
        int maxX = (int) (rect.getX() + rect.getWidth());
        int maxY = (int) (rect.getY() + rect.getHeight());
        Random rand = new Random();

        Point p = new Point(
                rand.nextInt(maxX - minX) + minX + 1,
                rand.nextInt(maxY - minY) + minY + 1
        );

        return p;
    }
}
