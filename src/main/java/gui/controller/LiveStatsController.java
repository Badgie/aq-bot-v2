package gui.controller;

import bot.Bot;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
import log.Log;
import util.Util;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LiveStatsController implements Initializable {

    public Text XP_TEXT;
    public Text GOLD_TEXT;
    public Text ZTOKEN_TEXT;
    public Text DEATH_TEXT;
    public Text ENEMY_TEXT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            Log log = Util.getBot().getLog();
            XP_TEXT.setText("XP: " + log.getXp());
            GOLD_TEXT.setText("Gold: " + log.getGold());
            ZTOKEN_TEXT.setText("Z-Tokens: " + log.getZtoken());
            DEATH_TEXT.setText("Deaths: " + log.getDeaths());
            ENEMY_TEXT.setText("Enemies: " + log.getEnemies().size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
