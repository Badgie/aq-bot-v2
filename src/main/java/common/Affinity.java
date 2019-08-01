package common;

public class Affinity {
    private int percentage;
    private Affinity affinity;

    public class FireAffinity {

        public FireAffinity() {}
    }

    public class WaterAffinity {

        public WaterAffinity() {}
    }

    public class WindAffinity {

        public WindAffinity() {}
    }

    public class IceAffinity {

        public IceAffinity() {}
    }

    public class EarthAffinity {

        public EarthAffinity() {}
    }

    public class EnergyAffinity {

        public EnergyAffinity() {}
    }

    public class LightAffinity {

        public LightAffinity() {}
    }

    public class DarknessAffinity {

        public DarknessAffinity() {}
    }

    public Affinity(int percentage, Affinity affinity) {
        this.percentage = percentage;
        this.affinity = affinity;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Affinity getAffinity() {
        return affinity;
    }

    public void setAffinity(Affinity affinity) {
        this.affinity = affinity;
    }
}
