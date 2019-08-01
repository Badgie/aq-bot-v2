package player;

import common.Affinity;

import java.util.List;

public class Armor {
    private int pos;
    private String name;
    private List<Skill> skills;
    private List<Affinity> affinities;

    public Armor(int pos, String name, List<Skill> skills, List<Affinity> affinities) {
        this.pos = pos;
        this.name = name;
        this.skills = skills;
        this.affinities = affinities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Affinity> getAffinities() {
        return affinities;
    }

    public void setAffinities(List<Affinity> affinities) {
        this.affinities = affinities;
    }
}
