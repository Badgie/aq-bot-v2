package util;

import gui.controller.IndexController;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GUIUtil {
    private static IndexController indexController;

    public static IndexController getIndexController() {
        return indexController;
    }

    public static void setIndexController(IndexController indexController) {
        GUIUtil.indexController = indexController;
    }

    public String[] getProfiles() {
        File dir = new File(Util.getUsrDir() + "/profile/");
        return dir.list();
    }

    public List<String> getBots() {
        List<String> bots = new ArrayList<>();
        bots.add("Battleon");
        bots.add("Civil War III");
        return bots;
    }
}
