package ui;

import model.Assignment;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays assignments
public class AssignmentPanel extends TabbedPanel {
    private ToDoList toDoList;
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
        this.toDoList = toDoList;
        assignmentListPanel.update(toDoList, toDoList.getAssignments());
    }

    // EFFECTS: adds a new assignment
    protected void add() {
        if (toDoList.getCourses().size() > 0) {
            JOptionAssignment panel = new JOptionAssignment(toDoList.getCourses().getNames());
            if (panel.getConfirmed()) {
                Assignment assignment = new Assignment(
                        panel.getName(), toDoList.getCourses().get(panel.getCourseName()));
                toDoList.add(assignment);
                updateAll();
            }
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Assignments can only be added to existing courses. Currently there are no courses.",
                    "Add assignment warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
