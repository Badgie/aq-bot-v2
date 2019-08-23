package enemy;

import common.Affinity;
import log.Log;
import player.Player;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class GenericEnemy extends Enemy {
    private String name;
    private List<Affinity> affinities;
    private Player player;
    private Log log;

    public GenericEnemy(String name, List<Affinity> affinities, Player player, Log log) {
        super(name, affinities, player, log);
    }

    @Override
    public boolean fight() throws IOException, AWTException, InterruptedException {
        return super.fight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Affinity> getAffinities() {
        return affinities;
    }

    public void setAffinities(List<Affinity> affinities) {
        this.affinities = affinities;
    }
}
