package enemy;

import common.Affinity;
import log.Log;
import player.Player;
import util.CursorUtil;
import util.ScreenUtil;
import util.StatUtil;
import weapon.Weapon;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public abstract class Enemy {
    public Log log;
    public Player player;
    public String name;
    public Map<String, Integer> affinities;

    public final String LOAD_SCREEN_GRAY = "ff333333";

    protected Enemy() throws IOException, AWTException {

    }

    public static Enemy create(Player player, Log log, String name) throws IOException, AWTException, InterruptedException {
        ScreenUtil screen = new ScreenUtil();
        Enemy enemy;

        //TODO: add affinities
        switch (name) {
            default:
                enemy = new GenericEnemy(
                        name,
                        processAffinities(screen.getEnemyAffinities()),
                        player,
                        log
                );
                break;
        }
        return enemy;
    }

    /**
     * Swaps equipment based on enemy
     */
    public void fightSetup() throws IOException, AWTException, InterruptedException {
        List<Map.Entry<String, Integer>> weaknesses = getEnemyWeaknessesSorted();

        if (player.isDynamicWeapons()) changeWeapon(weaknesses);
    }

    private void changeWeapon(List<Map.Entry<String, Integer>> weaknesses) throws IOException, AWTException, InterruptedException {
        List<Weapon> playerWeapons = player.getWeapons();
        CursorUtil cursor = new CursorUtil();
        int weaponIndex = -1;

        outerloop:
        for (Map.Entry<String, Integer> weakness : weaknesses) {
            for (Weapon w : playerWeapons) {
                if (w.getAffinity().contains(weakness.getKey())) {
                    weaponIndex = playerWeapons.indexOf(w);
                    break outerloop;
                }
            }
        }

        if (weaponIndex != -1) {
            cursor.weapon(weaponIndex);
        }
    }

    private List<Map.Entry<String, Integer>> getEnemyWeaknessesSorted() {
        List<Map.Entry<String, Integer>> weaknesses = new ArrayList<>(affinities.entrySet());
        weaknesses.sort(Map.Entry.comparingByValue());
        return weaknesses;
    }

    public static Map<String, Integer> processAffinities(String affinities) {
        Map<String, Integer> affinityMap = new HashMap<>();
        String[] affinitySets = affinities.split("\n");
        String[] affinity;
        String id;
        String percentage;

        for (String s : affinitySets) {
            affinity = s.split(" ");
            id = affinity[0];
            percentage = affinity[1].replaceAll("[^0-9-]", "");
            affinityMap.put(id, Integer.parseInt(percentage));
        }

        return affinityMap;
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
