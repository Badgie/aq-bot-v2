package log;

import util.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Log {
    private Date start;
    private int xp;
    private int gold;
    private int ztoken;
    private List<String> enemies;
    private int deaths;

    public Log() {
        this.start = new Date();
        this.xp = 0;
        this.gold = 0;
        this.ztoken = 0;
        this.enemies = new ArrayList<>();
        this.deaths = 0;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getXp() {
        return xp;
    }

    public int getGold() {
        return gold;
    }

    public int getZtoken() {
        return ztoken;
    }

    public List<String> getEnemies() {
        return enemies;
    }

    public void addXp(int xp) {
        this.xp += xp;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void addZtoken(int ztoken) {
        this.ztoken += ztoken;
    }

    public void addEnemy(String name) {
        this.enemies.add(name);
    }

    public void addDeath() {
        this.deaths++;
    }

    public void writeToFile() {
        String date = "log-" + start.toString();
        date = date.substring(0, date.indexOf(" CEST"));
        date = date.replace(" ", "-");
        date = date.replace(":", "");
        date = date.toLowerCase();
        try {
            PrintWriter writer = new PrintWriter(new File(
                    Util.getUsrDir() + "/logs/" + date + ".txt"),
                    StandardCharsets.UTF_8
            );
            writer.write(toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AQ-bot log: ")
                .append(start.toString())
                .append(" -> ").append(new Date())
                .append("\n").append("XP: ")
                .append(xp).append("\n")
                .append("Gold: ")
                .append(gold)
                .append("\n")
                .append("Z Tokens: ")
                .append(ztoken)
                .append("\n")
                .append("Deaths: ")
                .append(deaths)
                .append("\n")
                .append("Enemy count: ")
                .append(enemies.size())
                .append("\n")
                .append("Enemies:\n");

        for (String s : enemies) {
            builder.append(s)
                    .append("\n");
        }

        return builder.toString();
    }
}
