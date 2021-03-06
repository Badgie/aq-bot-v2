package util;

import bot.Bot;
import common.RectDimension;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.json.JSONTokener;
import tools.ScreenCaptureFX;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Util {
    private static Stage PRIMARY_STAGE;
    private static Stage LIVE_STATS_STAGE;
    private static Bot BOT;
    private static String USR_DIR = System.getProperty("user.dir");
    private static Service BOT_SERVICE;

    public Util() {}

    public static Service getBotService() {
        return BOT_SERVICE;
    }

    public static void setBotService(Service botService) {
        BOT_SERVICE = botService;
    }

    public static String getUsrDir() {
        return USR_DIR;
    }

    public static void setPrimaryStage(Stage stage) {
        PRIMARY_STAGE = stage;
    }

    public static Stage getPrimaryStage() {
        return PRIMARY_STAGE;
    }

    public static Bot getBot() {
        return BOT;
    }

    public static void setBot(Bot bot) {
        BOT = bot;
    }

    public static Stage getLiveStatsStage() {
        return LIVE_STATS_STAGE;
    }

    public static void setLiveStatsStage(Stage liveStatsStage) {
        LIVE_STATS_STAGE = liveStatsStage;
    }

    /**
     * Takes a scrot of a screen area
     */
    public BufferedImage takeScrot(JSONObject obj) throws AWTException, IOException {
        Rectangle area = JSONtoRectangle(obj);
        return new Robot().createScreenCapture(area);
    }

    /**
     * Converts JSON object to Rectangle object with proper dimensions
     * @param obj
     * @return
     */
    public Rectangle JSONtoRectangle(JSONObject obj) throws IOException, AWTException {
        RectDimension gameScreenDims = new ConfigUtil().getGameScreenConfig();

        double relativeXPercent = (double) obj.getInt("GAME_SCREEN_X") / obj.getInt("ORIGIN_GAME_SCREEN_WIDTH");
        double relativeYPercent = (double) obj.getInt("GAME_SCREEN_Y") / obj.getInt("ORIGIN_GAME_SCREEN_HEIGHT");
        double relativeWidthPercent = (double) obj.getInt("GAME_SCREEN_WIDTH") / obj.getInt("ORIGIN_GAME_SCREEN_WIDTH");
        double relativeHeightPercent = (double) obj.getInt("GAME_SCREEN_HEIGHT") / obj.getInt("ORIGIN_GAME_SCREEN_HEIGHT");

        int relativeX = (int)(gameScreenDims.getX() + (gameScreenDims.getWidth() * relativeXPercent));
        int relativeY = (int)(gameScreenDims.getY() + (gameScreenDims.getHeight() * relativeYPercent));
        int relativeWidth = (int)(gameScreenDims.getWidth() * relativeWidthPercent);
        int relativeHeight = (int)(gameScreenDims.getHeight() * relativeHeightPercent);

        return new Rectangle(
                relativeX,
                relativeY,
                relativeWidth,
                relativeHeight
        );
    }

    /**
     * Gets a random point within a rectangle
     * @param obj object to find a point from
     * @return random point object
     */
    public Point getRandomPoint(JSONObject obj) throws IOException, AWTException {
        Rectangle bounds = JSONtoRectangle(obj);

        int minX = (int) bounds.getX();
        int minY = (int) bounds.getY();
        int maxX = (int) (bounds.getX() + bounds.getWidth());
        int maxY = (int) (bounds.getY() + bounds.getHeight());
        Random rand = new Random();

        return new Point(
                rand.nextInt(maxX - minX) + minX + 1,
                rand.nextInt(maxY - minY) + minY + 1
        );
    }

    /**
     * Gets input path json file as a json object
     * @param path
     * @return
     * @throws IOException
     */
    public JSONObject getDimsAsJSONObject(String path) throws IOException {
        FileReader reader = new FileReader(new File(Util.getUsrDir() + "/coords/" + path));
        BufferedReader bReader = new BufferedReader(reader);
        String s = bReader.readLine();
        JSONTokener tokens = new JSONTokener(s);

        return new JSONObject(tokens);
    }

    public static void launchScrotStage() {
        try {
            Robot robot = new Robot();
            ScreenUtil u = new ScreenUtil();
            ConfigUtil conf = new ConfigUtil();

            BufferedImage screen;
            Rectangle gameScreen;
            final RectDimension rectDims;

            if (Files.exists(Paths.get(Util.getUsrDir() + "/config/game-screen.txt"))) {
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

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    new ScreenCaptureFX(screen, rectDims);
                }
            });
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks a hex color code against an array of black hexes
     * @param hex hex color code
     * @return true or false, determining if the pixel is black or not
     */
    public boolean pixelIsBlack(String hex) {
        final String[] gimmickyArrayWithHexColorCodes = {
                "ff000000",
                "ff010101",
                "ff020202",
                "ff030303",
                "ff040404",
                "ff050505",
                "ff060606",
                "ff070707",
                "ff080808",
                "ff090909",
                "ff101010",
                "ff0a0a0a"
        };

        for (String s : gimmickyArrayWithHexColorCodes) {
            if (s.equals(hex)) {
                return true;
            }
        }
        return false;
    }
}
