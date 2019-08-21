package util;

import common.RectDimension;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class ConfigUtil {
    public final static String GAME_SCREEN_X_KEY = "GAME_SCREEN_X";
    public final static String GAME_SCREEN_Y_KEY = "GAME_SCREEN_Y";
    public final static String GAME_SCREEN_WIDTH_KEY = "GAME_SCREEN_WIDTH";
    public final static String GAME_SCREEN_HEIGHT_KEY = "GAME_SCREEN_HEIGHT";
    public final static String SCREEN_SIZE_RELATIVE_PERCENT_X_KEY = "SCREEN_SIZE_RELATIVE_PERCENT_X";
    public final static String SCREEN_SIZE_RELATIVE_PERCENT_Y_KEY = "SCREEN_SIZE_RELATIVE_PERCENT_Y";
    public final static String SCREEN_SIZE_RELATIVE_PERCENT_WIDTH_KEY = "SCREEN_SIZE_RELATIVE_PERCENT_WIDTH";
    public final static String SCREEN_SIZE_RELATIVE_PERCENT_HEIGHT_KEY = "SCREEN_SIZE_RELATIVE_PERCENT_HEIGHT";
    public final static String SCREEN_SIZE_RELATIVE_X_KEY = "SCREEN_SIZE_RELATIVE_X";
    public final static String SCREEN_SIZE_RELATIVE_Y_KEY = "SCREEN_SIZE_RELATIVE_Y";
    public final static String SCREEN_SIZE_RELATIVE_WIDTH_KEY = "SCREEN_SIZE_RELATIVE_WIDTH";
    public final static String SCREEN_SIZE_RELATIVE_HEIGHT_KEY = "SCREEN_SIZE_RELATIVE_HEIGHT";
    public final static String WIDTH_INDENT_RIGHT_KEY = "WIDTH_INDENT_RIGHT";
    public final static String HEIGHT_INDENT_BOTTOM_KEY = "HEIGHT_INDENT_BOTTOM";

    public static void firstTimeSetup() throws IOException, AWTException {
        createFolders();

        File gameScreen = new File(Util.getUsrDir() + "/config/game-screen.txt");
        if (!gameScreen.exists()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("I need to do some initial setup. Please open AdventureQuest in full screen mode" +
                    " via the Artix Launcher client.");
            ButtonType done = new ButtonType("Done");
            alert.getButtonTypes().setAll(done);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {
                if (!gameScreen.exists()) {
                    createGameScreenConfig();
                }
            } else {
                System.exit(0);
            }
        }
    }

    public RectDimension getGameScreenConfig() throws IOException, AWTException {
        FileReader reader = new FileReader(new File(Util.getUsrDir() + "/config/game-screen.txt"));
        BufferedReader bReader = new BufferedReader(reader);
        String config = bReader.readLine();
        JSONTokener tokens = new JSONTokener(config);
        JSONObject gameScreenConfig = new JSONObject(tokens);

        return new RectDimension(
                Integer.parseInt(gameScreenConfig.get("GAME_SCREEN_X").toString()),
                Integer.parseInt(gameScreenConfig.get("GAME_SCREEN_Y").toString()),
                Integer.parseInt(gameScreenConfig.get("GAME_SCREEN_WIDTH").toString()),
                Integer.parseInt(gameScreenConfig.get("GAME_SCREEN_HEIGHT").toString()),
                Double.parseDouble(gameScreenConfig.get("SCREEN_SIZE_RELATIVE_PERCENT_X").toString()),
                Double.parseDouble(gameScreenConfig.get("SCREEN_SIZE_RELATIVE_PERCENT_Y").toString()),
                Double.parseDouble(gameScreenConfig.get("SCREEN_SIZE_RELATIVE_PERCENT_WIDTH").toString()),
                Double.parseDouble(gameScreenConfig.get("SCREEN_SIZE_RELATIVE_PERCENT_HEIGHT").toString()),
                Integer.parseInt(gameScreenConfig.get("WIDTH_INDENT_RIGHT").toString()),
                Integer.parseInt(gameScreenConfig.get("HEIGHT_INDENT_BOTTOM").toString()),
                new Dimension(
                        (int) Toolkit.getDefaultToolkit().getScreenSize().width,
                        (int) Toolkit.getDefaultToolkit().getScreenSize().height
                )
        );
    }

    private static void createFolders() {
        String usrDir = Util.getUsrDir();
        File config = new File(usrDir + "/config/");
        File logs = new File(usrDir + "/logs/");

        if (!config.exists()) config.mkdir();
        if (!logs.exists()) logs.mkdir();
    }

    public static void linuxSetup() throws IOException {
        Stage stage = Util.getPrimaryStage();
        String usrDir = Util.getUsrDir();
        if (!Files.exists(Paths.get(usrDir + "/config/wm.txt"))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            FileWriter writer = new FileWriter(new File(usrDir + "/config/wm.txt"));

            alert.setContentText("I see you're using Linux. Are you using a tiling WM?");

            ButtonType yes = new ButtonType("Yes");
            ButtonType no = new ButtonType("No");

            alert.getButtonTypes().setAll(yes, no);

            Optional<ButtonType> wmResult = alert.showAndWait();

            if (wmResult.isPresent() && wmResult.get().equals(yes)) {
                alert.setContentText("When using a WM, it is favorable to have the game as the only tile present" +
                        "on your monitor, otherwise I might not work as intended. Would you like me to float " +
                        "around on your screen like a spooky ghost?\n\n" +
                        "Your selection will be saved for future use.\n\n\n\n\n\n\n\n" +
                        "Should you change your setup to a floating WM, simply navigate to the directory in which " +
                        "I am installed, and delete the file named 'wm.txt' in the config directory.");
                Optional<ButtonType> floatResult = alert.showAndWait();
                if (floatResult.isPresent() && floatResult.get().equals(yes)) {
                    writer.write("1");
                    stage.initStyle(StageStyle.UTILITY);
                } else {
                    writer.write("0");
                }
            } else {
                writer.write("0");
            }

            writer.close();
        } else {
            byte[] wmConf = Files.readAllBytes(Paths.get(usrDir + "/config/wm.txt"));
            String s = new String(wmConf, StandardCharsets.UTF_8);
            if (s.equals("1")) {
                stage.initStyle(StageStyle.UTILITY);
            }
        }
    }

    /**
     * Creates game-screen config with rectangle data of the game area
     * @throws AWTException
     * @throws IOException
     */
    public static void createGameScreenConfig() throws AWTException, IOException {
        ScreenUtil util = new ScreenUtil();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        RectDimension gameScreen = util.getGameWindowScreen();
        JSONObject json = new JSONObject();

        json.put(GAME_SCREEN_X_KEY, gameScreen.getX());
        json.put(GAME_SCREEN_Y_KEY, gameScreen.getY());
        json.put(GAME_SCREEN_WIDTH_KEY, gameScreen.getWidth());
        json.put(GAME_SCREEN_HEIGHT_KEY, gameScreen.getHeight());
        json.put(SCREEN_SIZE_RELATIVE_PERCENT_X_KEY, (gameScreen.getX() / screen.getWidth()));
        json.put(SCREEN_SIZE_RELATIVE_PERCENT_Y_KEY, (gameScreen.getY() / screen.getHeight()));
        json.put(SCREEN_SIZE_RELATIVE_PERCENT_WIDTH_KEY, (gameScreen.getWidth() / screen.getWidth()));
        json.put(SCREEN_SIZE_RELATIVE_PERCENT_HEIGHT_KEY, (gameScreen.getHeight() / screen.getHeight()));
        json.put(SCREEN_SIZE_RELATIVE_X_KEY, gameScreen.getRelativeX());
        json.put(SCREEN_SIZE_RELATIVE_Y_KEY, gameScreen.getRelativeY());
        json.put(SCREEN_SIZE_RELATIVE_WIDTH_KEY, gameScreen.getRelativeWidth());
        json.put(SCREEN_SIZE_RELATIVE_HEIGHT_KEY, gameScreen.getRelativeHeight());
        json.put(WIDTH_INDENT_RIGHT_KEY, gameScreen.getWidthIndentRight());
        json.put(HEIGHT_INDENT_BOTTOM_KEY, gameScreen.getHeightIndentBottom());

        PrintWriter writer = new PrintWriter(new File(Util.getUsrDir() + "/config/game-screen.txt"), StandardCharsets.UTF_8);
        writer.write(json.toString());
        writer.close();
    }
}
