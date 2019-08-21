package item;

public class GenericItem extends Item {
    private String name;

    public GenericItem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GenericItem{" +
                "name='" + name + '\'' +
                '}';
    }
}
