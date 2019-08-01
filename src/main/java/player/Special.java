package player;

public class Special {
    private String name;
    private double px;
    private double py;
    private double pw;
    private double ph;

    public Special(String name, double px, double py, double pw, double ph) {
        this.name = name;
        this.px = px;
        this.py = py;
        this.pw = pw;
        this.ph = ph;
    }

    public String getName() {
        return name;
    }

    public double getPx() {
        return px;
    }

    public double getPy() {
        return py;
    }

    public double getPw() {
        return pw;
    }

    public double getPh() {
        return ph;
    }
}
