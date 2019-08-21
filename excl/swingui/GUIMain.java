package excl.swingui;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class GUIMain {

    public GUIMain() throws IOException {
        JFrame frame = new JFrame();
        JPanel rootPanel = new JPanel();
        JPanel profilePanel = new JPanel();
        JPanel botPanel = new JPanel();
        JComboBox<String> profiles = new JComboBox<>();
        JComboBox<String> bots = new JComboBox<>();
        JButton newProfileButton = new JButton("New");
        JButton editProfileButton = new JButton("Edit");
        JButton startButton = new JButton("Start");

        addProfilesToComboBox(profiles);
        addBotsToComboBox(bots);

        profilePanel.add(profiles);
        profilePanel.add(newProfileButton);
        profilePanel.add(editProfileButton);
        botPanel.add(bots);
        botPanel.add(startButton);

        profilePanel.setMinimumSize(new Dimension(200, 100));
        botPanel.setMinimumSize(new Dimension(200, 100));

        rootPanel.add(profilePanel);
        rootPanel.add(botPanel);

        frame.add(rootPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.show();
    }

    private void addProfilesToComboBox(JComboBox<String> box) throws IOException {
        GUIUtil util = new GUIUtil();
        String[] profiles = util.getProfiles();

        for (String s : profiles) {
            box.addItem(s);
        }
    }

    private void addBotsToComboBox(JComboBox<String> box) {
        GUIUtil util = new GUIUtil();
        List<String> bots = util.getBots();

        for (String s : bots) {
            box.addItem(s);
        }
    }
}
