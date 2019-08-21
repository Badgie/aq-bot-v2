import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.json.JSONTokener;
import util.ConfigUtil;
import util.GUIUtil;
import util.Util;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GUIMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("/fxml/index.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 600);
        String os = System.getProperty("os.name");
        stage.setScene(scene);
        GUIUtil.setIndexController(loader.getController());
        Util.setPrimaryStage(stage);
        ConfigUtil.firstTimeSetup();
        if (os.equals("Linux")) {
            ConfigUtil.linuxSetup();
        }
        stage.show();
    }
}
