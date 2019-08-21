package armor;

import armor.special.NekoArmor;
import armor.special.TerrorSetArmor;
import common.Affinity;
import org.json.JSONObject;
import player.Skill;
import util.Util;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public abstract class Armor {
    private String name;
    private List<Skill> skills;

    private static String[] TERROR_SET = {
            "Guardian Menace Raiment"
    };

    private static String[] NEKO = {
            "Sol Neko",
            "Luna Neko"
    };


    public abstract String getName();
    public abstract List<Skill> getSkills();
    public abstract List<Skill> addSkills() throws IOException;
    public abstract void useSkill() throws IOException, AWTException, InterruptedException;
    public abstract boolean skillCondition() throws IOException, AWTException;
    public abstract void regularAttack();

    public static Armor create(String name) throws IOException, AWTException {
        if (arrayContains(TERROR_SET, name)) {
            return new TerrorSetArmor(name);
        } else if (arrayContains(NEKO, name)) {
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
