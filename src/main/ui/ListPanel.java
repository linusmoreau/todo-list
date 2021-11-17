package ui;

import model.ToDoList;

import javax.swing.*;

public abstract class ListPanel extends JPanel {
    protected ToDoListAppGUI frame;
    protected ToDoList toDoList;

    public ListPanel(ToDoListAppGUI frame) {
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
