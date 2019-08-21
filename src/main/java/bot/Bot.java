package bot;

import log.Log;
import player.Player;
import util.StatUtil;
import util.Util;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Bot {
    private final StatUtil stats = new StatUtil();

    protected Bot() throws IOException, AWTException {
    }

    private int extractAffinityPercentage(String aff) {
        aff = aff.replaceAll("[^0-9-]", "");
        return Integer.parseInt(aff);
    }

    private Map<String, Integer> processAffinities(String aff) {
        String[] affArray = aff.split("\n");
        Map<String, Integer> affMap = new HashMap<>();
        affMap.put("Fire", extractAffinityPercentage(affArray[0]));
        affMap.put("Water", extractAffinityPercentage(affArray[1]));
        affMap.put("Wind", extractAffinityPercentage(affArray[2]));
        affMap.put("Ice", extractAffinityPercentage(affArray[3]));
        affMap.put("Earth", extractAffinityPercentage(affArray[4]));
        affMap.put("Energy", extractAffinityPercentage(affArray[5]));
        affMap.put("Light", extractAffinityPercentage(affArray[6]));
        affMap.put("Darkness", extractAffinityPercentage(affArray[7]));
        return affMap;
    }

    public abstract Log getLog();
    public abstract int getBattles();
    public abstract Player getPlayer();
    public abstract void bot() throws AWTException, InterruptedException, IOException;
}
