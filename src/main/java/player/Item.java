package player;

public class Item {
    private int pos;
    private String name;

    public Item(int pos, String name) {
        this.pos = pos;
        this.name = name;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
