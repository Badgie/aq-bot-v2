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
    public List<Affinity> affinities;

    public final String LOAD_SCREEN_GRAY = "ff333333";

    protected Enemy(String name, List<Affinity> affinities, Player player, Log log) {
        this.name = name;
        this.affinities = affinities;
        this.player = player;
        this.log = log;
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
                System.out.println(enemy.toString());
                break;
        }
        return enemy;
    }

    public boolean fight() throws IOException, AWTException, InterruptedException {
        ScreenUtil screen = new ScreenUtil();
        CursorUtil cursor = new CursorUtil();
        StatUtil stats = new StatUtil();
        log.addEnemy(stats.getEnemyName());
        boolean win;
        fightSetup();
        while(true) {
            boolean hpUnderThreshold = stats.getPlayerHp() < (double) player.getHealthPotThreshold();
            boolean playerHasHealthPots = player.getHealthPots() > 0;
            boolean playerArmorHasSkills = player.getArmor().getSkills() != null && player.getArmor().getSkills().size() != 0;
            if (screen.checkTurn()) {
                if (hpUnderThreshold && playerHasHealthPots) {
                    player.useHealthPotion();
                } else {
                    if (playerArmorHasSkills && player.getArmor().skillCondition(affinities)) {
                        player.useSkill();
                    } else {
                        player.getArmor().regularAttack();
                        cursor.attack();
                        cursor.clearAttack();
                    }
                }
            } else if (screen.checkIfWin()) {
                logVictoryRewards(log);
                cursor.victory();
                cursor.clearAvatar();
                if (screen.checkZTokens()) {
                    log.addZtoken(screen.getZtokens());
                    cursor.zTokens();
                }
                // if load screen has started, player did not level up
                if (screen.getLoadScreenPixelHex().equals(LOAD_SCREEN_GRAY)) {
                    win = true;
                    break;
                }
                Thread.sleep(1000);
                if (screen.checkIfLevelUp()) {
                    cursor.levelUp();
                }
                win = true;
                break;
            } else if (screen.checkIfDead()) {
                log.addDeath();
                cursor.death();
                win = false;
                break;
            } else {
                Thread.sleep(2000);
            }
        }
        return win;
    }

    /**
     * Swaps equipment based on enemy
     */
    public void fightSetup() throws IOException, AWTException, InterruptedException {
        List<Affinity> weaknesses = getEnemyWeaknessesSorted();
        if (player.isDynamicWeapons()) changeWeapon(weaknesses);
    }

    private void changeWeapon(List<Affinity> weaknesses) throws IOException, AWTException, InterruptedException {
        CursorUtil cursor = new CursorUtil();
        int weaponIndex = -1;
        outerloop:
        for (Affinity weakness : weaknesses) {
            for (Weapon w : player.getWeapons()) {
                if (w.getAffinity().contains(weakness.getAffinity())) {
                    weaponIndex = player.getWeapons().indexOf(w);
                    System.out.println("Enemy weakness: " + weakness.toString());
                    System.out.println("Weapon of choice: " + w.toString());
                    break outerloop;
                }
            }
        }

        // fix this abomination
        if (weaponIndex != -1 && !player.getWeapon().getAffinity().get(0)
                .equals(player.getWeapons().get(weaponIndex).getAffinity().get(0))) {
            System.out.println("Weapon of choice is different from equipped, swapping");
            cursor.weapon(weaponIndex);
            player.setWeapon(player.getWeapons().get(weaponIndex));
        } else {
            System.out.println("Weapon of choice is already equipped");
        }

        System.out.println("\n###########################################\n");
    }

    private List<Affinity> getEnemyWeaknessesSorted() {
        List<Affinity> weaknesses = new ArrayList<>(affinities);
        weaknesses.sort(Comparator.comparingInt(Affinity::getPercentage).reversed());
        return weaknesses;
    }

    public static List<Affinity> processAffinities(String affinities) {
        List<Affinity> affinityList = new ArrayList<>();
        String[] affinitySets = affinities.split("\n");
        String[] affinity;
        String id;
        String percentage;

        for (String s : affinitySets) {
            affinity = s.split(" ");
            id = affinity[0];
            percentage = affinity[1].replaceAll("[^0-9-]", "");

            if (Integer.parseInt(percentage) > 300) percentage = percentage.substring(0, percentage.length() - 1);

            affinityList.add(new Affinity(Integer.parseInt(percentage), id));
        }

        return affinityList;
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

    @Override
    public String toString() {
        return "Enemy {\n" +
                "name='" + name + "\',\n" +
                "affinities=" + affinities +
                '}';
    }

    public abstract String getName();
    public abstract List<Affinity> getAffinities();
}
