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

        //TODO: add delay

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

    public void spell(int spell) throws InterruptedException {
        switch (spell) {
            case 0:
                quickCastSpell0();
                break;
            case 1:
                quickCastSpell1();
                break;
            case 2:
                quickCastSpell2();
                break;
            case 3:
                quickCastSpell3();
                break;
            case 4:
                quickCastSpell4();
                break;
            case 5:
                quickCastSpell5();
                break;
            case 6:
                quickCastSpell6();
                break;
            case 7:
                quickCastSpell7();
                break;
            default:

                break;
        }
    }

    public void skill() throws InterruptedException {
        final int x = (int) (screenWidth * 0.44216691068814057);
        final int y = (int) (screenHeight * 0.484375);
        final int w = (int) (screenWidth * 0.116398243045388);
        final int h = (int) (screenHeight * 0.03125);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());

        //TODO: make framework for different armors with different skill sets
    }

    public void item(int item) throws InterruptedException {
        final int x = (int) (screenWidth * 0.44216691068814057);
        final int y = (int) (screenHeight * 0.5234375);
        final int w = (int) (screenWidth * 0.1171303074670571);
        final int h = (int) (screenHeight * 0.03515625);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());

        //TODO: add delay

        switch (item) {
            case -2:
                healthPot();
                break;
            case -1:
                manaPot();
                break;
            case 0:
                item0();
                break;
            case 1:
                item1();
                break;
            case 2:
                item2();
                break;
            case 3:
                item3();
                break;
            case 4:
                item4();
                break;
            case 5:
                item5();
                break;
            case 6:
                item6();
                break;
            case 7:
                item7();
                break;
            default:

                break;
        }
    }

    public void weapon(int weapon) throws InterruptedException {
        final int x = (int) (screenWidth * 0.44216691068814057);
        final int y = (int) (screenHeight * 0.5651041666666666);
        final int w = (int) (screenWidth * 0.116398243045388);
        final int h = (int) (screenHeight * 0.03515625);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());

        //TODO: add delay

        switch(weapon) {
            case 0:
                weapon0();
                break;
            case 1:
                weapon1();
                break;
            case 2:
                weapon2();
                break;
            case 3:
                weapon3();
                break;
            case 4:
                weapon4();
                break;
            case 5:
                weapon5();
                break;
            case 6:
                weapon6();
                break;
            case 7:
                weapon7();
                break;
            default:

                break;
        }
    }

    public void shield(int shield) throws InterruptedException {
        final int x = (int) (screenWidth * 0.44216691068814057);
        final int y = (int) (screenHeight * 0.6067708333333334);
        final int w = (int) (screenWidth * 0.11566617862371889);
        final int h = (int) (screenHeight * 0.03515625);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());

        //TODO: add delay

        switch(shield) {
            case -1:
                noShield();
                break;
            case 0:
                shield0();
                break;
            case 1:
                shield1();
                break;
            case 2:
                shield2();
                break;
            case 3:
                shield3();
                break;
            case 4:
                shield4();
                break;
            case 5:
                shield5();
                break;
            case 6:
                shield6();
                break;
            case 7:
                shield7();
                break;
            default:

                break;
        }
    }

    public void armor(int armor) throws InterruptedException {
        final int x = (int) (screenWidth * 0.44216691068814057);
        final int y = (int) (screenHeight * 0.6497395833333334);
        final int w = (int) (screenWidth * 0.116398243045388);
        final int h = (int) (screenHeight * 0.029947916666666668);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());

        //TODO: add delay
        //TODO: layout is different with and without subrace, create case without

        switch(armor) {
            case -1:
                race();
                break;
            case 0:
                armor0();
                break;
            case 1:
                armor1();
                break;
            case 2:
                armor2();
                break;
            case 3:
                armor3();
                break;
            case 4:
                armor4();
                break;
            case 5:
                armor5();
                break;
            case 6:
                armor6();
                break;
            case 7:
                armor7();
                break;
            default:

                break;
        }
    }

    /**
     * Armor helper methods
     */

    private void race() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.42578125);
        final int w = (int) (screenWidth * 0.1815519765739385);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor0() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.4609375);
        final int w = (int) (screenWidth * 0.1815519765739385);
        final int h = (int) (screenHeight * 0.022135416666666668);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor1() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.4947916666666667);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.0234375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor2() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.53125);
        final int w = (int) (screenWidth * 0.1815519765739385);
        final int h = (int) (screenHeight * 0.0234375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor3() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.5651041666666666);
        final int w = (int) (screenWidth * 0.1815519765739385);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor4() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.5989583333333334);
        final int w = (int) (screenWidth * 0.18301610541727673);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor5() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.6341145833333334);
        final int w = (int) (screenWidth * 0.18301610541727673);
        final int h = (int) (screenHeight * 0.0234375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor6() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.66796875);
        final int w = (int) (screenWidth * 0.18301610541727673);
        final int h = (int) (screenHeight * 0.02734375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void armor7() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6215226939970717);
        final int y = (int) (screenHeight * 0.7057291666666666);
        final int w = (int) (screenWidth * 0.17349926793557832);
        final int h = (int) (screenHeight * 0.022135416666666668);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Shield helper methods
     */

    private void noShield() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.4427083333333333);
        final int w = (int) (screenWidth * 0.18301610541727673);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield0() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.4791666666666667);
        final int w = (int) (screenWidth * 0.1815519765739385);
        final int h = (int) (screenHeight * 0.0234375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield1() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.51171875);
        final int w = (int) (screenWidth * 0.18301610541727673);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield2() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.546875);
        final int w = (int) (screenWidth * 0.1808199121522694);
        final int h = (int) (screenHeight * 0.026041666666666668);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield3() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.5833333333333334);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.0234375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield4() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.6171875);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.0234375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield5() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.65234375);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield6() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6185944363103953);
        final int y = (int) (screenHeight * 0.6861979166666666);
        final int w = (int) (screenWidth * 0.1800878477306003);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void shield7() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6222547584187409);
        final int y = (int) (screenHeight * 0.72265625);
        final int w = (int) (screenWidth * 0.17276720351390923);
        final int h = (int) (screenHeight * 0.022135416666666668);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Weapon helper methods
     */

    private void weapon0() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.4609375);
        final int w = (int) (screenWidth * 0.18301610541727673);
        final int h = (int) (screenHeight * 0.02734375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon1() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.4973958333333333);
        final int w = (int) (screenWidth * 0.1815519765739385);
        final int h = (int) (screenHeight * 0.026041666666666668);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon2() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.5338541666666666);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.0234375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon3() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.5677083333333334);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon4() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.6028645833333334);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.0234375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon5() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6171303074670571);
        final int y = (int) (screenHeight * 0.63671875);
        final int w = (int) (screenWidth * 0.18301610541727673);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon6() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.671875);
        final int w = (int) (screenWidth * 0.1815519765739385);
        final int h = (int) (screenHeight * 0.0234375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void weapon7() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6207906295754027);
        final int y = (int) (screenHeight * 0.70703125);
        final int w = (int) (screenWidth * 0.17349926793557832);
        final int h = (int) (screenHeight * 0.022135416666666668);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Item helper methods
     */

    private void healthPot() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6178623718887262);
        final int y = (int) (screenHeight * 0.4153645833333333);
        final int w = (int) (screenWidth * 0.03953147877013177);
        final int h = (int) (screenHeight * 0.07421875);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void manaPot() throws InterruptedException {
        final int x = (int) (screenWidth * 0.664714494875549);
        final int y = (int) (screenHeight * 0.4153645833333333);
        final int w = (int) (screenWidth * 0.03806734992679356);
        final int h = (int) (screenHeight * 0.07552083333333333);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void item0() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6156661786237189);
        final int y = (int) (screenHeight * 0.5325520833333334);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.020833333333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void item1() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6156661786237189);
        final int y = (int) (screenHeight * 0.5638020833333334);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.01953125);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void item2() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6156661786237189);
        final int y = (int) (screenHeight * 0.5924479166666666);
        final int w = (int) (screenWidth * 0.18301610541727673);
        final int h = (int) (screenHeight * 0.022135416666666668);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void item3() throws InterruptedException {
        final int x = (int) (screenWidth * 0.616398243045388);
        final int y = (int) (screenHeight * 0.6236979166666666);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.020833333333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void item4() throws InterruptedException {
        final int x = (int) (screenWidth * 0.616398243045388);
        final int y = (int) (screenHeight * 0.6536458333333334);
        final int w = (int) (screenWidth * 0.1815519765739385);
        final int h = (int) (screenHeight * 0.01953125);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void item5() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6156661786237189);
        final int y = (int) (screenHeight * 0.6822916666666666);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.020833333333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void item6() throws InterruptedException {
        final int x = (int) (screenWidth * 0.616398243045388);
        final int y = (int) (screenHeight * 0.7135416666666666);
        final int w = (int) (screenWidth * 0.1822840409956076);
        final int h = (int) (screenHeight * 0.018229166666666668);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void item7() throws InterruptedException {
        final int x = (int) (screenWidth * 0.6215226939970717);
        final int y = (int) (screenHeight * 0.7434895833333334);
        final int w = (int) (screenWidth * 0.17130307467057102);
        final int h = (int) (screenHeight * 0.01953125);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    /**
     * Spell helper methods
     */

    private void quickCastSpell0() throws InterruptedException {
        final int x = (int) (screenWidth * 0.44216691068814057);
        final int y = (int) (screenHeight * 0.4518229166666667);
        final int w = (int) (screenWidth * 0.010248901903367497);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell1() throws InterruptedException {
        final int x = (int) (screenWidth * 0.4568081991215227);
        final int y = (int) (screenHeight * 0.4518229166666667);
        final int w = (int) (screenWidth * 0.010248901903367497);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell2() throws InterruptedException {
        final int x = (int) (screenWidth * 0.47291361639824303);
        final int y = (int) (screenHeight * 0.4518229166666667);
        final int w = (int) (screenWidth * 0.00951683748169839);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell3() throws InterruptedException {
        final int x = (int) (screenWidth * 0.4868228404099561);
        final int y = (int) (screenHeight * 0.4518229166666667);
        final int w = (int) (screenWidth * 0.010248901903367497);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell4() throws InterruptedException {
        final int x = (int) (screenWidth * 0.5021961932650073);
        final int y = (int) (screenHeight * 0.4518229166666667);
        final int w = (int) (screenWidth * 0.010248901903367497);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell5() throws InterruptedException {
        final int x = (int) (screenWidth * 0.5175695461200586);
        final int y = (int) (screenHeight * 0.4518229166666667);
        final int w = (int) (screenWidth * 0.010248901903367497);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell6() throws InterruptedException {
        final int x = (int) (screenWidth * 0.5314787701317716);
        final int y = (int) (screenHeight * 0.4518229166666667);
        final int w = (int) (screenWidth * 0.012445095168374817);
        final int h = (int) (screenHeight * 0.024739583333333332);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
    }

    private void quickCastSpell7() throws InterruptedException {
        final int x = (int) (screenWidth * 0.5475841874084919);
        final int y = (int) (screenHeight * 0.453125);
        final int w = (int) (screenWidth * 0.010980966325036604);
        final int h = (int) (screenHeight * 0.0234375);
        final Rectangle rect = new Rectangle(x, y, w, h);
        Point p = getRandomPoint(rect);
        moveAndClick(p.getX(), p.getY());
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
