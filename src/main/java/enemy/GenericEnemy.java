package enemy;

import enemy.Enemy;
import log.Log;
import player.Player;
import util.CursorUtil;
import util.ScreenUtil;
import util.StatUtil;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class GenericEnemy extends Enemy {
    private String name;
    private Map<String, Integer> affinities;
    private Player player;
    private Log log;

    public GenericEnemy(String name, Map<String, Integer> affinities, Player player, Log log) throws IOException, AWTException {
        this.name = name;
        this.affinities = affinities;
        this.player = player;
        this.log = log;
    }

    @Override
    public boolean fight() throws IOException, AWTException, InterruptedException {
        ScreenUtil screen = new ScreenUtil();
        CursorUtil cursor = new CursorUtil();
        StatUtil stats = new StatUtil();
        log.addEnemy(stats.getEnemyName());
        boolean win;
        while(true) {
            boolean hpUnderThreshold = stats.getPlayerHp() < (double) player.getHealthPotThreshold();
            boolean playerHasHealthPots = player.getHealthPots() > 0;
            boolean playerArmorHasSkills = player.getArmor().getSkills() != null && player.getArmor().getSkills().size() != 0;
            if (screen.checkTurn()) {
                if (hpUnderThreshold && playerHasHealthPots) {
                    player.useHealthPotion();
                } else {
                    if (playerArmorHasSkills && player.getArmor().skillCondition()) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getAffinities() {
        return affinities;
    }

    public void setAffinities(Map<String, Integer> affinities) {
        this.affinities = affinities;
    }
}
