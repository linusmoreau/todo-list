package ui;

import javax.swing.*;
import java.awt.*;

// Represents a panel contained in tabs
public abstract class TabbedPanel extends JPanel {
    protected ToDoListAppGUI frame;
    private final JButton addButton;

    // EFFECTS: constructs tabbed panel with add button and banners
    public TabbedPanel(int width, ToDoListAppGUI frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        addButton = new JButton("Add");
        addButton.addActionListener(e -> add());
        add(addButton, BorderLayout.NORTH);
        add(makeBanner(width), BorderLayout.WEST);
        add(makeBanner(width), BorderLayout.EAST);
    }

    // EFFECTS: makes banner
    private JPanel makeBanner(int width) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(21, 72, 21));
        panel.setPreferredSize(new Dimension(width / 8, 0));
        panel.setMinimumSize(new Dimension(width / 8, 0));
        return panel;
    }

    // EFFECTS: updates all GUI
    public void updateAll() {
        frame.updateAll();
    }

    // EFFECTS: adds element to the tab
    protected abstract void add();
}
