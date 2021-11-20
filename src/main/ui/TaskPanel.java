package ui;

import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays tasks
public class TaskPanel extends TabbedPanel {
    private final TaskListPanel taskListPanel;

    // EFFECTS: constructs panel for tasks
    public TaskPanel(ToDoList toDoList, ToDoListAppGUI frame) {
        super(frame);
        taskListPanel = new TaskListPanel(frame);
        add(new JScrollPane(taskListPanel), BorderLayout.CENTER);
        update(toDoList);
    }

    // EFFECTS: updates panel for changes in to-do list
    public void update(ToDoList toDoList) {
        taskListPanel.update(toDoList, toDoList.getTasks());
    }

    // EFFECTS: adds a new task
    protected void add() {
        taskListPanel.add();
    }
}
