package ui;

import model.Assignment;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents list panel for assignments
public class AssignmentListPanel extends ListPanel {

    // EFFECTS: constructs list panel for assignments
    public AssignmentListPanel(ToDoListAppGUI frame) {
        super(frame);
    }

    // MODIFIES: this
    // EFFECTS: updates assignment display to match given assignments
    public void update(ToDoList toDoList, ArrayList<Assignment> assignments) {
        this.toDoList = toDoList;
        removeAll();
        for (Assignment assignment : assignments) {
            add(makeComponent(assignment));
        }
        updateUI();
    }

    // EFFECTS: makes panel for the given assignment
    private JComponent makeComponent(Assignment assignment) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Color background = Color.lightGray;
        JPanel coursePanel = makeTextPanel(assignment);
        JPanel buttonPanel = makeButtonPanel(assignment);
        coursePanel.setBackground(background);
        buttonPanel.setBackground(background);
        panel.add(coursePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.setMaximumSize(new Dimension(10000, panel.getPreferredSize().height));
        panel.setBackground(background);
        return panel;
    }

    // EFFECTS: makes panel for text displaying assignment info
    private JPanel makeTextPanel(Assignment assignment) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 16, 8));

        JLabel nameLabel = new JLabel(assignment.getName());
        JLabel courseLabel = new JLabel(assignment.getCourse().getName());
        JLabel dateLabel = new JLabel(assignment.getDueDate().toString());

        nameLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);
        courseLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);
        dateLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);

        nameLabel.setPreferredSize(new Dimension(512, nameLabel.getPreferredSize().height));
        courseLabel.setPreferredSize(new Dimension(256, courseLabel.getPreferredSize().height));
        dateLabel.setPreferredSize(new Dimension(256, dateLabel.getPreferredSize().height));

        panel.add(nameLabel);
        panel.add(courseLabel);
        panel.add(dateLabel);

        return panel;
    }

    // EFFECTS: makes panel for buttons allowing for editing and deletion
    private JPanel makeButtonPanel(Assignment assignment) {
        JPanel panel = new JPanel();
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        editButton.addActionListener(e -> edit(assignment));
        deleteButton.addActionListener(e -> delete(assignment));
        panel.add(editButton);
        panel.add(deleteButton);
        return panel;
    }

    // MODIFIES: toDoList
    // EFFECTS:  makes changes to assignment, affecting the model and rest of GUI
    private void edit(Assignment assignment) {
        JOptionAssignment panel = new JOptionAssignment(toDoList.getCourses().getNames(),
                assignment.getName(), assignment.getCourse().getName(), assignment.getDueDate());
        if (panel.getConfirmed()) {
            assignment.setName(panel.getName());
            assignment.setCourse(toDoList.getCourses().get(panel.getCourseName()));
            assignment.setDueDate(panel.getDate());
            frame.updateAll();
        }
    }

    // MODIFIES: toDoList
    // EFFECTS: deletes assignment, affecting the odel and rest of GUI
    private void delete(Assignment assignment) {
        toDoList.remove(assignment);
        frame.updateAll();
    }

    // MODIFIES: toDoList
    // EFFECTS: provides dialog for adding a new assignment
    protected void add() {
        if (toDoList.getCourses().size() > 0) {
            JOptionAssignment panel = new JOptionAssignment(toDoList.getCourses().getNames());
            if (panel.getConfirmed()) {
                Assignment assignment = new Assignment(
                        panel.getName(), toDoList.getCourses().get(panel.getCourseName()), panel.getDate());
                toDoList.add(assignment);
                frame.updateAll();
            }
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Assignments can only be added to existing courses. Currently there are no courses.",
                    "Add assignment warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
