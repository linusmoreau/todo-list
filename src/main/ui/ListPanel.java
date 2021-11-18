package ui;

import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents a panel displaying a list of objects
public abstract class ListPanel extends JPanel {
    protected static final Color BACKGROUND = Color.lightGray;
    protected static final int MARGIN = 8;
    protected ToDoListAppGUI frame;
    protected ToDoList toDoList;

    // EFFECTS: constructs list panel
    public ListPanel(ToDoListAppGUI frame) {
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    // EFFECTS: adds horizontal margin to given panel
    protected void addHorizontalMargin(JPanel panel) {
        JPanel margin = new JPanel();
        margin.setBackground(BACKGROUND);
        margin.setPreferredSize(new Dimension(MARGIN, 0));
        panel.add(margin);
    }

    // EFFECS: adds vertical margin to given panel
    protected void addVerticalMargin(JPanel panel) {
        JPanel margin = new JPanel();
        margin.setBackground(BACKGROUND);
        margin.setPreferredSize(new Dimension(0, MARGIN));
        panel.add(margin);
    }
}
