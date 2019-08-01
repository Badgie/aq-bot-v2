package player;

import common.Affinity;

public class Spell {
    private int pos;
    private String name;
    private Affinity affinity;
    private boolean summoning;

    public Spell(int pos, String name, Affinity affinity, boolean summoning) {
        this.pos = pos;
        this.name = name;
        this.affinity = affinity;
        this.summoning = summoning;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Affinity getAffinity() {
        return affinity;
    }

    public void setAffinity(Affinity affinity) {
        this.affinity = affinity;
    }

    public boolean isSummoning() {
        return summoning;
    }

    public void setSummoning(boolean summoning) {
        this.summoning = summoning;
    }
}
