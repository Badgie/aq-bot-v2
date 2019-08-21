package enemy;

import common.Affinity;
import log.Log;
import player.Player;
import util.CursorUtil;
import util.ScreenUtil;
import util.StatUtil;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Enemy {
    public Log log;
    public Player player;
    public String name;
    public Map<String, Integer> affinities;

    public final String LOAD_SCREEN_GRAY = "ff333333";

    protected Enemy() throws IOException, AWTException {

    }

    public static Enemy create(Player player, Log log, String name) throws IOException, AWTException, InterruptedException {
        StatUtil stats = new StatUtil();
        Enemy enemy;

        //TODO: add affinities
        switch (name) {
            default:
                enemy = new GenericEnemy(
                        name,
                        new HashMap<>(),
                        //processAffinities(screen.getEnemyAffinities()),
                        player,
                        log
                );
                break;
        }
        return enemy;
    }

    public void logVictoryRewards(Log log) throws IOException, AWTException {
        ScreenUtil screen = new ScreenUtil();
        String[] victoryRewards = screen.getBattleRewards().split("\n");
        List<String> xpList = new ArrayList<>();
        List<String> goldList = new ArrayList<>();
        for (String s : victoryRewards) {
            if (s.contains("XP")) {
                xpList.add(s.replaceAll("[^0-9]", ""));
            } else if (s.contains("GOLD")) {
                goldList.add(s.replaceAll("[^0-9]", ""));
            }
        }

        xpList.forEach(s -> log.addXp(Integer.parseInt(s)));
        goldList.forEach(s -> log.addGold(Integer.parseInt(s)));
    }

    public abstract boolean fight() throws IOException, InterruptedException, AWTException;
    public abstract String getName();
    public abstract Map<String, Integer> getAffinities();
}
