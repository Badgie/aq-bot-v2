package bot;

import enemy.Enemy;
import log.Log;
import player.Player;
import util.CursorUtil;
import util.StatUtil;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class RandomBattleBot extends Bot {
    private int battles;
    private Player player;
    private Log log;

    public RandomBattleBot(int battles, Player player) throws IOException, AWTException {
        this.battles = battles;
        this.player = player;
        this.log = new Log();
    }

    @Override
    public void bot() throws AWTException, InterruptedException, IOException {
        CursorUtil cursor = new CursorUtil();
        StatUtil stats = new StatUtil();
        for (int i = 0; i < battles; i++) {
            cursor.heal();
            cursor.randomBattle();
            Thread.sleep(2000);
            Enemy enemy = Enemy.create(player, log, stats.getEnemyName());
            enemy.fight();
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

    @Override
    public String toString() {
        return "RandomBattleBot{" +
                "battles=" + battles +
                ", player=" + player +
                ", log=" + log +
                '}';
    }
}
