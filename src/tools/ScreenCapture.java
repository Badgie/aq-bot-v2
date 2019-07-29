package tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class ScreenCapture {

    /**
     * Utility for grabbing screen regions.
     * Yields x, y, w, and h of the region,
     * as well as the relative percentage of
     * the individual points.
     */

    private Rectangle captureRect;

    public ScreenCapture(final BufferedImage screen) {
        final BufferedImage screenCopy = new BufferedImage(
                screen.getWidth(),
                screen.getHeight(),
                screen.getType());
        final JLabel screenLabel = new JLabel(new ImageIcon(screenCopy));
        JScrollPane screenScroll = new JScrollPane(screenLabel);

        screenScroll.setPreferredSize(new Dimension(
                (int) (screen.getWidth() - (screen.getWidth()/10)),
                (int) (screen.getHeight() - (screen.getHeight()/10))));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(screenScroll, BorderLayout.CENTER);

        final JLabel selectionLabel = new JLabel(
                "Drag a rectangle in the screen shot!");
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

        JOptionPane.showMessageDialog(null, panel);

        System.out.println("Rectangle of interest: \n" +
                "x: " + captureRect.x + "\n" +
                "y: " + captureRect.y + "\n" +
                "x%: " + (double)(captureRect.getX() / screen.getWidth()) + "\n" +
                "y%: " + (double)(captureRect.getY() / screen.getHeight()) + "\n" +
                "w: " + captureRect.getWidth() + "\n" +
                "h: " + captureRect.getHeight() + "\n" +
                "w%: " + (double)(captureRect.getWidth() / screen.getWidth()) + "\n" +
                "h%: " + (double)(captureRect.getHeight() / screen.getHeight())
        );

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
        Robot robot = new Robot();
        final Dimension screenSize = Toolkit.getDefaultToolkit().
                getScreenSize();
        final BufferedImage screen = robot.createScreenCapture(
                new Rectangle(screenSize));

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScreenCapture(screen);
            }
        });
    }
}
