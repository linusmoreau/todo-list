package ui;

import model.Course;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

public class CourseListPanel extends ListPanel {
    private ToDoList toDoList;

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

    private JComponent makeComponent(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Color background = Color.lightGray;
        JPanel coursePanel = makeTextPanel(course);
        JPanel buttonPanel = makeButtonPanel(course);
        coursePanel.setBackground(background);
        buttonPanel.setBackground(background);
        panel.add(coursePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.setMaximumSize(new Dimension(10000, panel.getPreferredSize().height));
        panel.setBackground(background);
        return panel;
    }

    private JPanel makeTextPanel(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 16, 8));

        JLabel courseLabel = new JLabel(course.getName());
        courseLabel.setPreferredSize(new Dimension(64, 24));
        panel.add(courseLabel);
        panel.add(new JLabel("Assignments: " + course.getAssignments().size()));
        panel.add(new JLabel("Exams: " + course.getExams().size()));
        return panel;
    }

    private JPanel makeButtonPanel(Course course) {
        JPanel panel = new JPanel();
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> edit(course));
        panel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> delete(course));
        panel.add(deleteButton);
        return panel;
    }

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

    private void delete(Course course) {
        toDoList.remove(course);
        frame.updateAll();
    }
}
