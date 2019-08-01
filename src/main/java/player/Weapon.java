package player;

import common.Affinity;

import java.util.List;

public class Weapon {
    private int pos;
    private String name;
    private List<Special> specials;
    private List<Affinity> affinities;

    public Weapon(int pos, String name, List<Special> specials, List<Affinity> affinities) {
        this.pos = pos;
        this.name = name;
        this.specials = specials;
        this.affinities = affinities;
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

    public List<Special> getSpecials() {
        return specials;
    }

    public void setSpecials(List<Special> specials) {
        this.specials = specials;
    }

    public List<Affinity> getAffinities() {
        return affinities;
    }

    public void setAffinities(List<Affinity> affinities) {
        this.affinities = affinities;
    }
}
