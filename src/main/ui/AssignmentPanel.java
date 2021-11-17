package ui;

import model.Assignment;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays courses
public class AssignmentPanel extends BanneredPanel {
    private ToDoList toDoList;
    private final AssignmentListPanel assignmentListPanel;

    public AssignmentPanel(ToDoList toDoList, int width, ToDoListAppGUI frame) {
        super(width, frame);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> add());
        add(addButton, BorderLayout.NORTH);
        assignmentListPanel = new AssignmentListPanel(frame);
        add(assignmentListPanel, BorderLayout.CENTER);
        update(toDoList);
    }

    public void update(ToDoList toDoList) {
        this.toDoList = toDoList;
        assignmentListPanel.update(toDoList, toDoList.getAssignments());
    }

    public void updateAll() {
        frame.updateAll();
    }

    private void add() {
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
