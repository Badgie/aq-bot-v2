package pet;

import common.Affinity;

import java.util.List;

public abstract class Pet {
    private String name;
    private Affinity affinity;

    public abstract String getName();
    public abstract Affinity getAffinity();

    public static Pet create(String name, Affinity affinity) {
        switch (name) {
            default:
                return new GenericPet(name, affinity);
        }
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", affinity=" + affinity +
                '}';
    }
}
