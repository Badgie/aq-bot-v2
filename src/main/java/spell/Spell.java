package spell;

import common.Affinity;

public abstract class Spell {
    private String name;
    private Affinity affinity;

    public abstract String getName();
    public abstract Affinity getAffinity();

    public static Spell create(String name, Affinity affinity) {
        switch (name) {
            default:
                return new GenericSpell(name, affinity);
        }
    }

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", affinity=" + affinity +
                '}';
    }
}
