package common;

public class Affinity {
    private int percentage;
    private String affinity;

    public Affinity(int percentage, String affinity) {
        this.percentage = percentage;
        this.affinity = affinity;
    }

    public Affinity(String affinity) {
        this.affinity = affinity;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getAffinity() {
        return affinity;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    @Override
    public String toString() {
        return "Affinity{" +
                affinity + ";" +
                percentage +
                "}\n";
    }
}
