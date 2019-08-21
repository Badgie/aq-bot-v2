package pet;

import common.Affinity;

public class GenericPet extends Pet {
    private String name;
    private Affinity affinity;

    public GenericPet(String name, Affinity affinity) {
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
        return "GenericPet{" +
                "name='" + name + '\'' +
                ", affinity=" + affinity +
                '}';
    }
}
