package log;

import util.Util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Log {
    private PropertyChangeSupport change = new PropertyChangeSupport(this);
    private Date start;
    private Date end;
    private int xp;
    private int gold;
    private int ztoken;
    private List<String> enemies;
    private int deaths;

    public Log() {
        this.start = new Date();
        this.end = null;
        this.xp = 0;
        this.gold = 0;
        this.ztoken = 0;
        this.enemies = new ArrayList<>();
        this.deaths = 0;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        change.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        change.removePropertyChangeListener(l);
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

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public void addXp(int xp) {
        int old = this.xp;
        this.xp += xp;
        change.firePropertyChange("xp", old, this.xp);
    }

    public void addGold(int gold) {
        int old = this.gold;
        this.gold += gold;
        change.firePropertyChange("gold", old, this.gold);
    }

    public void addZtoken(int ztoken) {
        int old = this.ztoken;
        this.ztoken += ztoken;
        change.firePropertyChange("ztoken", old, this.ztoken);
    }

    public void addEnemy(String name) {
        int old = this.enemies.size();
        this.enemies.add(name);
        change.firePropertyChange("enemies", old, this.enemies.size());
    }

    public void addDeath() {
        int old = this.deaths;
        this.deaths++;
        change.firePropertyChange("deaths", old, this.deaths);
    }

    public void setEnd() {
        this.end = new Date();
    }

    public LocalTime getDuration() {
        long diff = end.getTime() - start.getTime();
        return LocalTime.ofSecondOfDay(diff / 1000);
    }

    public void writeToFile() {
        setEnd();
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
                .append(" -> ").append(end.toString())
                .append("\n")
                .append("Duration:")
                .append(getDuration().toString())
                .append("\n")
                .append("XP: ")
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
