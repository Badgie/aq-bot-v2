package weapon;

import common.Affinity;
import player.Skill;

import java.util.List;

public abstract class Weapon {
    private String name;
    private List<String> affinity;
    private List<Skill> skills;

    public abstract String getName();
    public abstract List<Skill> getSkills();
    public abstract List<String> getAffinity();
    public abstract List<Skill> addSkills();
    public abstract void useSkill();

    public static Weapon create(String name, List<String> affinity) {
        switch (name) {
            default:
                return new GenericWeapon(name, affinity);
        }
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", affinity=" + affinity +
                ", skills=" + skills +
                '}';
    }
}
