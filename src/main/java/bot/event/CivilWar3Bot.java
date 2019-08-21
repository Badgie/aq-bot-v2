package bot.event;

import bot.Bot;
import enemy.Enemy;
import log.Log;
import org.json.JSONObject;
import player.Player;
import util.CursorUtil;
import util.StatUtil;
import util.Util;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class CivilWar3Bot extends Bot {
    private int battles;
    private Player player;
    private Log log;
    private CursorUtil cursor;
    private Util util;
    private StatUtil stats;
    //TODO: add loyalist or rebel as choice


    public CivilWar3Bot(int battles, Player player) throws IOException, AWTException {
        this.battles = battles;
        this.player = player;
        this.log = new Log();
        this.cursor = new CursorUtil();
        this.util = new Util();
        this.stats = new StatUtil();
    }

    @Override
    public void bot() throws AWTException, InterruptedException, IOException {
        boolean win = false;
        for (int i = 0; i < battles; i++) {
            if (!win) {
                goToEvent();
            }

            skipIntro();
            Thread.sleep(700);

            takePotions();
            player.setHealthPots(5);

            loyalist();

            endlessBattle();
            Thread.sleep(500);

            cursor.click();
            Thread.sleep(2000);

            for (int j = 0; j < 4; j++) {
                Enemy enemy = Enemy.create(player, log, stats.getEnemyName());
                win = enemy.fight();
                if (!win) break;
            }

            if (win) {
                nClicks(2);
                nClicks(6);

                Enemy enemy = Enemy.create(player, log, stats.getEnemyName());
                win = enemy.fight();
                Thread.sleep(2000);

                nClicks(1);
                nClicks(1);
                nClicks(8);
                nClicks(20);
                nClicks(4);
                nClicks(11);
                nClicks(14);
                nClicks(1);

                exitShop();
                playAgain();
                Thread.sleep(1000);
            }
        }
        log.writeToFile();
    }

    @Override
    public int getBattles() {
        return battles;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Log getLog() {
        return log;
    }

    private void goToEvent() throws IOException, InterruptedException, AWTException {
        cursor.travelMap();
        Thread.sleep(1000);
        travelMapCivilWar();
        travelMapCivilWarAccept();
        chooseCivilWar3();
        Thread.sleep(500);

    }

    private void travelMapCivilWar() throws IOException, InterruptedException, AWTException {
        JSONObject travelMapCivilWar = util.getDimsAsJSONObject("town/travelmap/civilwar/civilwar");
        Point p = util.getRandomPoint(travelMapCivilWar);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    private void travelMapCivilWarAccept() throws IOException, InterruptedException, AWTException {
        JSONObject travelMapCivilWarAccept = util.getDimsAsJSONObject("town/travelmap/civilwar/findout");
        Point p = util.getRandomPoint(travelMapCivilWarAccept);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    private void exitShop() throws IOException, InterruptedException, AWTException {
        JSONObject exitShop = util.getDimsAsJSONObject("town/event/civilwar3/exitshop");
        Point p = util.getRandomPoint(exitShop);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    private void playAgain() throws IOException, InterruptedException, AWTException {
        JSONObject playAgain = util.getDimsAsJSONObject("town/event/civilwar3/playagain");
        Point p = util.getRandomPoint(playAgain);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    private void takePotions() throws IOException, InterruptedException, AWTException {
        JSONObject takePotions = util.getDimsAsJSONObject("town/event/civilwar3/potion");
        Point p = util.getRandomPoint(takePotions);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    private void leaveLoyalist() throws IOException, InterruptedException, AWTException {
        JSONObject leaveLoyalist = util.getDimsAsJSONObject("town/event/civilwar3/leavepostloyalist");
        Point p = util.getRandomPoint(leaveLoyalist);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    @Deprecated
    private void chooseEvent() throws IOException, InterruptedException, AWTException {
        JSONObject civilWar3 = util.getDimsAsJSONObject("town/event/civilwar3/civilwar3");
        Point p = util.getRandomPoint(civilWar3);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    private void chooseCivilWar3() throws IOException, InterruptedException, AWTException {
        JSONObject chooseCivilWar3 = util.getDimsAsJSONObject("town/event/civilwar3/choosecivilwar3");
        Point p = util.getRandomPoint(chooseCivilWar3);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    private void skipIntro() throws IOException, InterruptedException, AWTException {
        JSONObject skipIntro = util.getDimsAsJSONObject("town/event/civilwar3/skipintro");
        Point p = util.getRandomPoint(skipIntro);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    private void loyalist() throws IOException, InterruptedException, AWTException {
        JSONObject loyalist = util.getDimsAsJSONObject("town/event/civilwar3/loyalist");
        Point p = util.getRandomPoint(loyalist);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    private void endlessBattle() throws IOException, InterruptedException, AWTException {
        JSONObject endlessBattle = util.getDimsAsJSONObject("town/event/civilwar3/fight/endless");
        Point p = util.getRandomPoint(endlessBattle);
        cursor.moveAndClick(p.getX(), p.getY());
    }

    private void nClicks(int n) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            cursor.click();
        }
        Thread.sleep(1500);
    }

    @Override
    public String toString() {
        return "CivilWar3Bot{" +
                "battles=" + battles +
                ", player=" + player +
                ", log=" + log +
                '}';
    }
}
