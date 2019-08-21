package spell;

import common.Affinity;

public class GenericSpell extends Spell {
    private String name;
    private Affinity affinity;

    public GenericSpell(String name, Affinity affinity) {
        this.name = name;
        this.affinity = affinity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Affinity getAffinity() {
        return affinity;
    }

    @Override
    public String toString() {
        return "GenericSpell{" +
                "name='" + name + '\'' +
                ", affinity=" + affinity +
                '}';
    }
}
