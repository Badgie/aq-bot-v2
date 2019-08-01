package player;

import common.Affinity;

import java.util.List;

public class Shield {
    private int pos;
    private String name;
    private List<Affinity> affinities;

    public Shield(int pos, String name, List<Affinity> affinities) {
        this.pos = pos;
        this.name = name;
        this.affinities = affinities;
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

    public List<Affinity> getAffinities() {
        return affinities;
    }

    public void setAffinities(List<Affinity> affinities) {
        this.affinities = affinities;
    }
}
