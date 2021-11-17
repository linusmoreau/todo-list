package ui;

import javax.swing.*;
import java.awt.*;

public class BanneredPanel extends JPanel {
    protected ToDoListAppGUI frame;

    public BanneredPanel(int width, ToDoListAppGUI frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        add(makeBanner(width), BorderLayout.WEST);
        add(makeBanner(width), BorderLayout.EAST);
    }

    private JPanel makeBanner(int width) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(21, 72, 21));
        panel.setPreferredSize(new Dimension(width / 8, 0));
        panel.setMinimumSize(new Dimension(width / 8, 0));
        return panel;
    }
}
