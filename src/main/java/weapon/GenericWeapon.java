package weapon;

import common.Affinity;
import player.Skill;

import java.util.List;

public class GenericWeapon extends Weapon {
    private String name;
    private List<Skill> skills;
    private List<String> affinity;

    public GenericWeapon(String name, List<String> affinity) {
        this.name = name;
        this.affinity = affinity;
    }

    @Override
    public List<Skill> addSkills() {
        return null;
    }

    @Override
    public void useSkill() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Skill> getSkills() {
        return skills;
    }

    @Override
    public List<String> getAffinity() {
        return affinity;
    }

    @Override
    public String toString() {
        return "GenericWeapon{" +
                "name='" + name + '\'' +
                ", skills=" + skills +
                ", affinity=" + affinity +
                '}';
    }
}

