package gui.controller;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import log.Log;
import util.Util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;

public class LiveStatsController implements Initializable, PropertyChangeListener {

    public Text XP_TEXT;
    public Text GOLD_TEXT;
    public Text ZTOKEN_TEXT;
    public Text DEATH_TEXT;
    public Text ENEMY_TEXT;
    private Log LOG;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LOG = Util.getBot().getLog();
        LOG.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();
        switch (prop) {
            case "xp":
                XP_TEXT.setText("XP: " + LOG.getXp());
                break;
            case "gold":
                GOLD_TEXT.setText("Gold: " + LOG.getGold());
                break;
            case "ztoken":
                ZTOKEN_TEXT.setText("Z-Tokens: " + LOG.getZtoken());
                break;
            case "deaths":
                DEATH_TEXT.setText("Deaths: " + LOG.getDeaths());
                break;
            case "enemies":
                ENEMY_TEXT.setText("Enemies: " + LOG.getEnemies().size());
                break;
        }
    }
}
