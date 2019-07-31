package util;

import com.github.joonasvali.naturalmouse.api.MouseMotionFactory;

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
        final Rectangle attackRect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(attackRect);

        this.m.move(
                (int) p.getX(),
                (int) p.getY()
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
