package gui.controller;

import bot.Bot;
import bot.RandomBattleBot;
import bot.event.CivilWar3Bot;
import common.RectDimension;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import player.Player;
import tools.ScreenCapture;
import util.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    public ComboBox<String> PROFILE_BOX;
    public ComboBox<String> BOT_BOX;
    public Button EDIT_PROFILE_BUTTON;
    public Button NEW_PROFILE_BUTTON;
    public Button START_BUTTON;
    public Button SETTINGS_BUTTON;
    public Button SCREEN_CAP_BUTTON;
    public TextField ROUND_FIELD;
    public static boolean IS_NEW_PROFILE;
    public static String PROFILE_NAME;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newProfileButton();
        editProfileButton();
        startBotButton();
        try {
            settingsButton();
            screenCapButton();
            setChoiceBoxes();
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }

    private void newProfileButton() {
        NEW_PROFILE_BUTTON.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                IS_NEW_PROFILE = true;
                try {
                    launchProfileConfig();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void editProfileButton() {
        EDIT_PROFILE_BUTTON.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                IS_NEW_PROFILE = false;
                PROFILE_NAME = PROFILE_BOX.getSelectionModel().getSelectedItem();
                try {
                    launchProfileConfig();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void startBotButton() {
        START_BUTTON.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    SetupUtil setup = new SetupUtil();
                    Stage primaryStage = Util.getPrimaryStage();
                    final Bot bot = getBotType(
                            Integer.parseInt(ROUND_FIELD.getText()),
                            setup.createPlayer(
                                    PROFILE_BOX.getSelectionModel().getSelectedItem()
                                            .toLowerCase().replace(" ", "-")
                            )
                    );
                    Util.setBot(bot);
                    Thread t = new Thread(() -> {
                        try {
                            bot.bot();
                        } catch (AWTException | InterruptedException | IOException e) {
                            e.printStackTrace();
                        }
                    });
                    t.start();
                    Util.setBotThread(t);

                    launchLiveStats();
                    primaryStage.hide();

                    while (t.isAlive()) {}

                    primaryStage.show();
                    Util.getLiveStatsStage().close();
                } catch (IOException | AWTException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void settingsButton() throws IOException {
        SETTINGS_BUTTON.setText("Settings");
        SETTINGS_BUTTON.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }

    private void screenCapButton() throws AWTException, IOException {
        SCREEN_CAP_BUTTON.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Robot robot = new Robot();
                    ScreenUtil u = new ScreenUtil();
                    ConfigUtil conf = new ConfigUtil();

                    BufferedImage screen;
                    Rectangle gameScreen;
                    final RectDimension rectDims;

                    if (Files.exists(Paths.get("./config/game-screen.txt"))) {
                        rectDims = conf.getGameScreenConfig();
                    } else {
                        rectDims = u.getGameWindowScreen();
                    }

                    gameScreen = new Rectangle(
                            (int) rectDims.getX(),
                            (int) rectDims.getY(),
                            (int) rectDims.getWidth(),
                            (int) rectDims.getHeight()
                    );

                    screen = robot.createScreenCapture(gameScreen);

                    /*SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new ScreenCapture(screen, rectDims);
                        }
                    });*/
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            new ScreenCapture(screen, rectDims);
                        }
                    });
                } catch (AWTException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setChoiceBoxes() {
        GUIUtil gui = new GUIUtil();
        String[] profiles = gui.getProfiles();
        List<String> bots = gui.getBots();
        List<String> formattedProfiles = formatProfileNames(profiles);

        PROFILE_BOX.setItems(FXCollections.observableArrayList(formattedProfiles));
        BOT_BOX.setItems(FXCollections.observableArrayList(bots));
    }

    public void updateProfileBox() {
        GUIUtil gui = new GUIUtil();
        String[] profiles = gui.getProfiles();
        List<String> formattedProfiles = formatProfileNames(profiles);
        PROFILE_BOX.setItems(FXCollections.observableArrayList(formattedProfiles));
    }

    private Bot getBotType(int battles, Player player) throws IOException, AWTException {
        Bot bot;
        switch (BOT_BOX.getSelectionModel().getSelectedItem()) {
            case "Civil War III":
                bot = new CivilWar3Bot(battles, player);
                break;
            case "Battleon":
                bot = new RandomBattleBot(battles, player);
                break;
            default:
                bot = null;
                break;
        }
        return bot;
    }

    /**
     * Changes profile names from file name to display
     * name in choice box
     * @param profiles
     * @return
     */
    private List<String> formatProfileNames(String[] profiles) {
        List<String> formattedNames = new ArrayList<>();
        for (String s : profiles) {
            StringBuilder result = new StringBuilder();
            String[] temp = s.split("-");
            for (String s2 : temp) {
                result.append(s2.substring(0, 1).toUpperCase())
                        .append(s2.substring(1))
                        .append(" ");
            }
            // remove last whitespace after loop
            result.deleteCharAt(result.length() - 1);
            formattedNames.add(result.toString());
        }
        return formattedNames;
    }

    private void launchLiveStats() throws IOException, AWTException {
        ConfigUtil conf = new ConfigUtil();
        double width = 100;
        double height = 200;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/livestats.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, width, height);

        RectDimension dims = conf.getGameScreenConfig();

        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Live Stats");
        stage.setScene(scene);
        stage.setX(dims.getX() + dims.getWidth() - width);
        stage.setY(dims.getY());

        Util.setLiveStatsStage(stage);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Util.getPrimaryStage().show();
                Util.getBot().getLog().writeToFile();
                Util.getBotThread().interrupt();
            }
        });

        stage.setAlwaysOnTop(true);

        stage.show();
    }

    private void launchProfileConfig() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/configprofile.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root, 476, 500);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Configure Profile");
        stage.setScene(scene);
        stage.show();
    }
}
