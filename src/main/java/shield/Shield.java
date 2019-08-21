package shield;

import common.Affinity;

import java.util.List;

public abstract class Shield {
    private String name;

    public abstract String getName();

    public static Shield create(String name) {
        switch (name) {
            default:
                return new GenericShield(name);
        }
    }

    @Override
    public String toString() {
        return "Shield{" +
                "name='" + name + '\'' +
                '}';
    }
}
