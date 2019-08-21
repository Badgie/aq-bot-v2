package shield;

public class GenericShield extends Shield {
    private String name;

    public GenericShield(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GenericShield{" +
                "name='" + name + '\'' +
                '}';
    }
}
