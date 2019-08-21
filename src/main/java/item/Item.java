package item;

public abstract class Item {
    private String name;

    public abstract String getName();

    public static Item create(String name) {
        switch (name) {
            default:
                return new GenericItem(name);
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                '}';
    }
}
