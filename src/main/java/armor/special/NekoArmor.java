package armor.special;

import armor.Armor;
import player.Skill;
import util.CursorUtil;
import util.Util;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NekoArmor extends Armor {
    private String name;
    private List<Skill> skills;
    private int charges;

    public NekoArmor(String name) throws IOException {
        this.name = name;
        this.skills = addSkills();
        this.charges = 35;
    }

    @Override
    public void regularAttack() {
        addCharges();
    }

    public void addCharges() {
        this.charges += 5;
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
    public List<Skill> addSkills() throws IOException {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("feline fine", "armor/neko/felinefine"));
        return skills;
    }

    @Override
    public void useSkill() throws IOException, AWTException, InterruptedException {
        this.charges -= 40;
        Skill skill = this.skills.get(0);
        Util util = new Util();
        CursorUtil cursor = new CursorUtil();
        Point p = util.getRandomPoint(util.getDimsAsJSONObject(skill.getPath()));
        cursor.moveAndClick(p.getX(), p.getY());
    }

    @Override
    public String toString() {
        return "NekoArmor{" +
                "name='" + name + '\'' +
                ", skills=" + skills +
                ", charges=" + charges +
                '}';
    }

    public boolean skillCondition() {
        return this.charges >= 40;
    }
}
