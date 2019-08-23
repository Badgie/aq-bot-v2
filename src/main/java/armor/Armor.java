package armor;

import armor.special.NekoArmor;
import armor.special.TerrorSetArmor;
import common.Affinity;
import enemy.Enemy;
import player.Skill;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public abstract class Armor {
    private String name;
    private List<Skill> skills;

    public abstract String getName();
    public abstract List<Skill> getSkills();
    public abstract List<Skill> addSkills() throws IOException;
    public abstract void useSkill() throws IOException, AWTException, InterruptedException;
    public abstract boolean skillCondition(List<Affinity> affinities) throws IOException, AWTException;
    public abstract void regularAttack();

    public static Armor create(String name) throws IOException, AWTException {
        String lowerCaseName = name.toLowerCase();
        if (lowerCaseName.equals("terror set") ||
                arrayContains(SpecialArmor.TERROR_SET, lowerCaseName)) {
            return new TerrorSetArmor(name);
        } else if (lowerCaseName.equals("neko") ||
                arrayContains(SpecialArmor.NEKO, lowerCaseName)) {
            return new NekoArmor(name);
        } else {
            return new GenericArmor(name);
        }
    }

    @Override
    public String toString() {
        return "Armor{" +
                "name='" + name + '\'' +
                ", skills=" + skills +
                '}';
    }

    private static boolean arrayContains(String[] array, String name) {
        for (String s : array) {
            if (s.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
