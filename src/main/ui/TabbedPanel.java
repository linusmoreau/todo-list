package ui;

import model.ToDoList;

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
        add(makeBanner("./data/snowscapeleft.gif"), BorderLayout.WEST);
        add(makeBanner("./data/snowscaperight.gif"), BorderLayout.EAST);
    }

    // EFFECTS: makes banner with given image path
    // EFFECTS: makes left-side banner
    private JPanel makeBanner(String path) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ImageIcon icon = new ImageIcon(path);
        icon.setImage(icon.getImage().getScaledInstance(
                (int) (((float) frame.getHeight() / icon.getIconHeight()) * icon.getIconWidth()),
                frame.getHeight(), Image.SCALE_DEFAULT));
        panel.add(new JLabel(icon));
        return panel;
    }

    // EFFECTS: adds element to the tab
    protected abstract void add();

    protected abstract void update(ToDoList toDoList);
}
