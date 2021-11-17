package ui;

import javax.swing.*;

public abstract class ListPanel extends JPanel {
    protected ToDoListAppGUI frame;

    public ListPanel(ToDoListAppGUI frame) {
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
