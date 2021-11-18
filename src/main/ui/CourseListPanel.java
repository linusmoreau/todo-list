package ui;

import model.Course;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents list panel for courses
public class CourseListPanel extends ListPanel {
    private ToDoList toDoList;

    // EFFECTS: constructs list panel for courses
    public CourseListPanel(ToDoListAppGUI frame) {
        super(frame);
    }

    public void update(ToDoList toDoList) {
        this.toDoList = toDoList;
        removeAll();
        for (Course course : toDoList.getCourses()) {
            add(makeComponent(course));
            add(Box.createRigidArea(new Dimension(0, 16)));
        }
        updateUI();
    }

    // EFFECTS: makes panel for the given course
    private JComponent makeComponent(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel titlePanel = makeTitlePanel(course);
        JPanel assignmentLabel = makeAssignmentLabel(course);
        JPanel assignmentPanel = makeAssignmentPanel(course);
        panel.add(titlePanel);
        panel.add(assignmentLabel);
        panel.add(assignmentPanel);
        addVerticalMargin(panel);
        panel.setMaximumSize(new Dimension(10000, panel.getPreferredSize().height));
        return panel;
    }

    // EFFECTS: makes panel displaying assignments of the given course
    private JPanel makeAssignmentPanel(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        AssignmentListPanel assignmentPanel = new AssignmentListPanel(frame);
        assignmentPanel.update(toDoList, course.getAssignments());
        panel.add(assignmentPanel);
        return panel;
    }

    // EFFECTS: makes panel for the assignment label
    private JPanel makeAssignmentLabel(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, MARGIN * 2, MARGIN));
        JLabel assignmentLabel = new JLabel("Assignments: " + course.getAssignments().size());
        assignmentLabel.setFont(new Font(ToDoListAppGUI.FONT, Font.PLAIN, 18));
        panel.add(assignmentLabel);
        panel.setBackground(BACKGROUND);
        return panel;
    }

    // EFFECTS: makes panel with the course's name and buttons
    private JPanel makeTitlePanel(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, MARGIN * 2, MARGIN));
        JLabel courseLabel = new JLabel(course.getName());
        JPanel buttonPanel = makeButtonPanel(course);
        courseLabel.setFont(new Font(ToDoListAppGUI.FONT, Font.PLAIN, 24));
        panel.add(courseLabel);
        panel.add(buttonPanel);
        panel.setBackground(BACKGROUND);
        return panel;
    }

    // EFFECTS: makes panel with buttons for editing and deletion
    private JPanel makeButtonPanel(Course course) {
        JPanel panel = new JPanel();
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        editButton.addActionListener(e -> edit(course));
        deleteButton.addActionListener(e -> delete(course));
        panel.add(editButton);
        panel.add(deleteButton);
        panel.setBackground(BACKGROUND);
        return panel;
    }

    // EFFECTS: provides dialog for editing course
    private void edit(Course course) {
        String s = (String) JOptionPane.showInputDialog(
                null,
                "Enter new course name:",
                "Edit Course",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                course.getName());
        if ((s != null) && (s.length() > 0)) {
            course.setName(s);
            frame.updateAll();
        }
    }

    // EFFECTS: deletes course
    private void delete(Course course) {
        toDoList.remove(course);
        frame.updateAll();
    }
}
