package player;

public class Skill {
    private String name;
    private double px;
    private double py;
    private double pw;
    private double ph;

    public Skill(String name, double px, double py, double pw, double ph) {
        this.name = name;
        this.px = px;
        this.py = py;
        this.pw = pw;
        this.ph = ph;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPx() {
        return px;
    }

    public void setPx(double px) {
        this.px = px;
    }

    public double getPy() {
        return py;
    }

    public void setPy(double py) {
        this.py = py;
    }

    public double getPw() {
        return pw;
    }

    public void setPw(double pw) {
        this.pw = pw;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }
}
