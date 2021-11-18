package ui;

import javax.swing.*;
import java.awt.*;

// Represents a panel contained in tabs
public abstract class TabbedPanel extends JPanel {
    protected ToDoListAppGUI frame;

    // EFFECTS: constructs tabbed panel with add button and banners
    public TabbedPanel(ToDoListAppGUI frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> add());
        add(addButton, BorderLayout.NORTH);
        add(makeBanner(), BorderLayout.WEST);
        add(makeBanner(), BorderLayout.EAST);
    }

    // EFFECTS: makes banner
    private JPanel makeBanner() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Icon img = new ImageIcon("./data/falling-snow.gif");
        for (int i = 0; i < frame.getHeight() / (new JLabel(img)).getPreferredSize().height + 1; i++) {
            panel.add(new JLabel(img));
        }
        return panel;
    }

    // EFFECTS: updates all GUI
    public void updateAll() {
        frame.updateAll();
    }

    // EFFECTS: adds element to the tab
    protected abstract void add();
}
