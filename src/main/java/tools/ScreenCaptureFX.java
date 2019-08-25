package tools;

import common.RectDimension;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;
import util.Util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class ScreenCaptureFX {
    private Rectangle captureRect = new Rectangle(0, 0, 0, 0);

    public ScreenCaptureFX(final BufferedImage screen, RectDimension gameScreenDims) {
        File tempDir = new File(Util.getUsrDir() + "/coords/temp");
        if (!tempDir.exists()) tempDir.mkdir();

        Stage stage = new Stage();
        ImageView img = new ImageView();
        img.setImage(SwingFXUtils.toFXImage(screen, null));
        ScrollPane screenScroll = new ScrollPane();
        Group imageLayer = new Group();
        imageLayer.getChildren().add(img);

        screenScroll.setContent(imageLayer);

        screenScroll.setPrefWidth(screen.getWidth() - (screen.getWidth() / 10));
        screenScroll.setPrefHeight(screen.getHeight() - (screen.getHeight() / 10));

        captureRect.setStroke(Color.RED);
        captureRect.setFill(new Color(1,1,1,0.5));
        captureRect.setStrokeWidth(1);

        imageLayer.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                captureRect.setX(0);
                captureRect.setY(0);
                captureRect.setWidth(0);
                captureRect.setHeight(0);

                imageLayer.getChildren().remove(captureRect);

                captureRect.setX(mouseEvent.getX());
                captureRect.setY(mouseEvent.getY());
            }
        });

        imageLayer.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                imageLayer.getChildren().remove(captureRect);
                captureRect.setWidth(mouseEvent.getX() - captureRect.getX());
                captureRect.setHeight(mouseEvent.getY() - captureRect.getY());
                imageLayer.getChildren().add(captureRect);
            }
        });

        imageLayer.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                JSONObject json = new JSONObject();
                TextInputDialog dialog = new TextInputDialog();
                dialog.setHeaderText("Please give a path for these coordinates");

                Optional<String> name = dialog.showAndWait();

                if (name.isPresent()) {
                    json.put("GAME_SCREEN_X", captureRect.getX());
                    json.put("GAME_SCREEN_Y", captureRect.getY());
                    json.put("GAME_SCREEN_WIDTH", captureRect.getWidth());
                    json.put("GAME_SCREEN_HEIGHT", captureRect.getHeight());
                    json.put("ORIGIN_GAME_SCREEN_X", gameScreenDims.getX());
                    json.put("ORIGIN_GAME_SCREEN_Y", gameScreenDims.getY());
                    json.put("ORIGIN_GAME_SCREEN_WIDTH", gameScreenDims.getWidth());
                    json.put("ORIGIN_GAME_SCREEN_HEIGHT", gameScreenDims.getHeight());
                    json.put("ORIGIN_WIDTH_INDENT_RIGHT", gameScreenDims.getWidthIndentRight());
                    json.put("ORIGIN_HEIGHT_INDENT_BOTTOM", gameScreenDims.getHeightIndentBottom());

                    try {
                        FileWriter writer = new FileWriter(Util.getUsrDir() + "/coords/" + name.get());
                        writer.write(json.toString());
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Created file: " + name.get());
                }
            }
        });

        Scene scene = new Scene(screenScroll);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
    }
}
