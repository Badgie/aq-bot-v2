package util;

import bot.Bot;
import common.RectDimension;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Util {
    private static Stage PRIMARY_STAGE;
    private static Stage LIVE_STATS_STAGE;
    private static Bot BOT;
    private static String USR_DIR = System.getProperty("user.dir");
    private static Thread BOT_THREAD;

    private static int imgCount = 0;

    public Util() {}

    public static Thread getBotThread() {
        return BOT_THREAD;
    }

    public static void setBotThread(Thread botThread) {
        BOT_THREAD = botThread;
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
        int relativeWidth = (int)(gameScreenDims.getWidth() * relativeWidthPercent /*- obj.getInt("ORIGIN_WIDTH_INDENT_RIGHT")*/);
        int relativeHeight = (int)(gameScreenDims.getHeight() * relativeHeightPercent/* - obj.getInt("ORIGIN_HEIGHT_INDENT_BOTTOM")*/);

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

        Point p = new Point(
                rand.nextInt(maxX - minX) + minX + 1,
                rand.nextInt(maxY - minY) + minY + 1
        );

        return p;
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
