package excl.swingui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomDialog {

    public CustomDialog(JPanel panel) {
        JDialog dialog;

        JOptionPane optPane = new JOptionPane(
                "",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                new Icon() {
                    @Override
                    public void paintIcon(Component component, Graphics graphics, int i, int i1) {}

                    @Override
                    public int getIconWidth() {return 0;}

                    @Override
                    public int getIconHeight() {return 0;}
                },
                new Object[]{},
                null
        );

        dialog = optPane.createDialog(new Frame(), "yeet");

        dialog.setPreferredSize(new Dimension(400, 600));

        dialog.add(panel);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });

        dialog.pack();
        dialog.show();
    }

    public void runCode() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomDialog(new JPanel());
            }
        });
    }
}
