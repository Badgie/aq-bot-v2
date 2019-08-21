package tools;

import com.google.gson.Gson;
import common.RectDimension;
import org.json.JSONObject;
import util.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class ScreenCapture {

    /**
     * Utility for grabbing screen regions.
     * Yields x, y, w, and h of the region,
     * as well as the relative percentage of
     * the individual points.
     */
    private Rectangle captureRect;

    public ScreenCapture(final BufferedImage screen, RectDimension gameScreenDims) {
        final BufferedImage screenCopy = new BufferedImage(
                screen.getWidth(),
                screen.getHeight(),
                screen.getType());
        final JLabel screenLabel = new JLabel(new ImageIcon(screenCopy));
        JScrollPane screenScroll = new JScrollPane(screenLabel);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        screenScroll.setPreferredSize(new Dimension(
                (int) (screen.getWidth() - (screen.getWidth()/10)),
                (int) (screen.getHeight() - (screen.getHeight()/10))));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(screenScroll, BorderLayout.CENTER);

        final JLabel selectionLabel = new JLabel(
                "Drag a rectangle in the screenshot!");
        panel.add(selectionLabel, BorderLayout.SOUTH);

        repaint(screen, screenCopy);
        screenLabel.repaint();

        screenLabel.addMouseMotionListener(new MouseMotionAdapter() {

            Point start = new Point();

            @Override
            public void mouseMoved(MouseEvent me) {
                start = me.getPoint();
                repaint(screen, screenCopy);
                selectionLabel.setText("Start Point: " + start
                    + "(x: " + (double)((screen.getWidth() - start.x) / screen.getWidth()*100) + "%)" +
                        "(y: " + (double)((screen.getHeight() - start.y) / screen.getHeight()*100) + "%)"
                );
                screenLabel.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent me) {
                Point end = me.getPoint();
                captureRect = new Rectangle(start,
                        new Dimension(end.x - start.x, end.y - start.y));
                repaint(screen, screenCopy);
                screenLabel.repaint();
                selectionLabel.setText("Rectangle: " + captureRect);
            }

        });

        screenLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                JSONObject json = new JSONObject();

                String name = JOptionPane.showInputDialog("Enter path of this area.");

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
                    FileWriter writer = new FileWriter("./coords/" + name);
                    writer.write(json.toString());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Created file: " + name);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        JOptionPane.showMessageDialog(null, panel);

    }

    public void repaint(BufferedImage orig, BufferedImage copy) {
        Graphics2D g = copy.createGraphics();
        g.drawImage(orig,0,0, null);
        if (captureRect!=null) {
            g.setColor(Color.RED);
            g.draw(captureRect);
            g.setColor(new Color(255,255,255,150));
            g.fill(captureRect);
        }
        g.dispose();
    }

    public void addThisToMain() throws AWTException {

    }
}
