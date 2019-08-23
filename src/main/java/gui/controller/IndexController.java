package gui.controller;

import bot.Bot;
import bot.RandomBattleBot;
import bot.event.CivilWar3Bot;
import common.RectDimension;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import log.Log;
import player.Player;
import util.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class IndexController implements Initializable {
    public ComboBox<String> PROFILE_BOX;
    public ComboBox<String> BOT_BOX;
    public Button EDIT_PROFILE_BUTTON;
    public Button NEW_PROFILE_BUTTON;
    public Button START_BUTTON;
    public Button SETTINGS_BUTTON;
    public Button DELETE_BUTTON;
    public HBox EXTRAS_HBOX;
    public TextField ROUND_FIELD;
    public static boolean IS_NEW_PROFILE;
    public static String PROFILE_NAME;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newProfileButton();
        editProfileButton();
        startBotButton();
        deleteProfileButton();
        try {
            settingsButton();
            setChoiceBoxes();
        } catch (IOException e) {
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
                if (PROFILE_BOX.getSelectionModel().getSelectedItem() == null) {
                    profileSelectionError();
                } else {
                    IS_NEW_PROFILE = false;
                    PROFILE_NAME = PROFILE_BOX.getSelectionModel().getSelectedItem();
                    try {
                        launchProfileConfig();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void deleteProfileButton() {
        DELETE_BUTTON.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (PROFILE_BOX.getSelectionModel().getSelectedItem() == null) {
                    profileSelectionError();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("You are about to delete profile \"" +
                            PROFILE_BOX.getSelectionModel().getSelectedItem() +
                            "\". This action is irreversible. Are you sure?");
                    ButtonType yes = new ButtonType("Yes");
                    ButtonType no = new ButtonType("No");

                    alert.getButtonTypes().setAll(yes, no);

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get().equals(yes)) {
                        String profileFile = PROFILE_BOX.getSelectionModel().getSelectedItem()
                                .toLowerCase().replace(" ", "-");
                        try {
                            Files.delete(Paths.get(Util.getUsrDir() + "/profile/" + profileFile));
                            updateProfileBox();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void profileSelectionError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please choose a profile.");
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
                    Service<Void> service = new Service<Void>() {
                        @Override
                        protected Task<Void> createTask() {
                            return new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    bot.bot();
                                    final CountDownLatch latch = new CountDownLatch(1);
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                primaryStage.show();
                                                Util.getLiveStatsStage().close();
                                                setPostBotStats(bot.getLog());
                                            } finally {
                                                latch.countDown();
                                            }
                                        }
                                    });
                                    latch.await();
                                    return null;
                                }
                            };
                        }
                    };
                    service.start();
                    Util.setBotService(service);
                    launchLiveStats();
                    primaryStage.hide();
                } catch (IOException | AWTException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void settingsButton() throws IOException {
        SETTINGS_BUTTON.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }

    /*private void screenCapButton() throws AWTException, IOException {
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

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new ScreenCapture(screen, rectDims);
                        }
                    });
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
    }*/

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

    private void setPostBotStats(Log log) {
        String logs = "";
        String os = System.getProperty("os.name");

        if (os.equals("Linux")) logs = "/logs/";
        else if (os.contains("Windows")) logs = "\\logs\\";

        VBox vbox = new VBox();

        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(5);

        Text header = new Text("Bot finished");
        header.setUnderline(true);
        Text xp = new Text("XP: " + log.getXp());
        Text gold = new Text("Gold: " + log.getGold());
        Text ztokens = new Text("Z-Tokens: " + log.getZtoken());
        Text deaths = new Text("Deaths: " + log.getDeaths());
        Text enemies = new Text("Enemies killed: " + (log.getEnemies().size() - log.getDeaths()));
        Text duration = new Text("Duration: " + log.getDuration());
        Text logLoc = new Text("Logs can be found in\n" +
                Util.getUsrDir() + logs);
        logLoc.setTextAlignment(TextAlignment.CENTER);

        vbox.getChildren().setAll(
                header,
                xp,
                gold,
                ztokens,
                deaths,
                enemies,
                duration,
                logLoc
        );

        EXTRAS_HBOX.getChildren().setAll(vbox);
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
                Util.getLiveStatsStage().close();
                Util.getBotService().cancel();
                setPostBotStats(Util.getBot().getLog());
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
