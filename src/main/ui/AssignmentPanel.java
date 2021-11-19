package ui;

import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays assignments
public class AssignmentPanel extends TabbedPanel {
    private final AssignmentListPanel assignmentListPanel;

    // EFFECTS: constructs panel for assignments
    public AssignmentPanel(ToDoList toDoList, ToDoListAppGUI frame) {
        super(frame);
        assignmentListPanel = new AssignmentListPanel(frame);
        add(new JScrollPane(assignmentListPanel), BorderLayout.CENTER);
        update(toDoList);
    }

    // EFFECTS: updates panel for changes in to-do list
    public void update(ToDoList toDoList) {
        assignmentListPanel.update(toDoList, toDoList.getAssignments());
    }

    // EFFECTS: adds a new assignment
    protected void add() {
        assignmentListPanel.addToCourse();
    }
}
