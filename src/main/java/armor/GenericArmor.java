package armor;

import common.Affinity;
import enemy.Enemy;
import player.Skill;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenericArmor extends Armor {
    private String name;
    private List<Skill> skills;

    public GenericArmor(String name) throws IOException, AWTException {
        this.name = name;
    }

    @Override
    public void regularAttack() {

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
        return new ArrayList<>();
    }

    @Override
    public void useSkill() throws IOException, AWTException, InterruptedException {

    }

    @Override
    public boolean skillCondition(List<Affinity> affinities) throws IOException, AWTException {
        // no skill conditions, but no skills
        return false;
    }

    @Override
    public String toString() {
        return "GenericArmor{" +
                "name='" + name + '\'' +
                ", skills=" + skills +
                '}';
    }
}
