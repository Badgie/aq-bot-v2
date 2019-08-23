package armor.special;

import armor.Armor;
import common.Affinity;
import enemy.Enemy;
import player.Skill;
import util.CursorUtil;
import util.StatUtil;
import util.Util;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TerrorSetArmor extends Armor {
    private String name;
    private List<Skill> skills;

    public TerrorSetArmor(String name) throws IOException, AWTException {
        this.name = name;
        this.skills = addSkills();
    }

    @Override
    public void regularAttack() {

    }

    /**
     * Terror set has one skill; fear
     */
    @Override
    public void useSkill() throws IOException, AWTException, InterruptedException {
        Skill skill = this.skills.get(0);
        Util util = new Util();
        CursorUtil cursor = new CursorUtil();
        Point p = util.getRandomPoint(util.getDimsAsJSONObject(skill.getPath()));
        cursor.moveAndClick(p.getX(), p.getY());
    }

    @Override
    public List<Skill> addSkills() throws IOException {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("fear", "armor/terrorset/fear"));
        return skills;
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
    public String toString() {
        return "TerrorSetArmor{" +
                "name='" + name + '\'' +
                ", skills=" + skills +
                '}';
    }

    public boolean skillCondition(Enemy enemy) throws IOException, AWTException {
        StatUtil stats = new StatUtil();
        return (stats.getPlayerSp() >= 50 && enemy.getAffinity("Darkness") >= 100);
    }
}
