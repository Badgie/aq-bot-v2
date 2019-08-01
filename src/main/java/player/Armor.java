package player;

import common.Affinity;

import java.util.List;

public class Armor {
    private int pos;
    private List<Skill> skills;
    private List<Affinity> affinities;

    public Armor(int pos, List<Skill> skills, List<Affinity> affinities) {
        this.pos = pos;
        this.skills = skills;
        this.affinities = affinities;
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
