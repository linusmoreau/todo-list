package ui;

import model.Course;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays courses
public class CoursePanel extends JPanel {
    private ToDoList toDoList;
    private final JButton addButton;
    private final int width;
    private static final int VGAP = 8;
    private static final int HGAP = 16;

    public CoursePanel(ToDoList toDoList, int width) {
        this.width = width;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addButton = new JButton("Add");
        addButton.addActionListener(e -> addCourse());
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        updateCourses(toDoList);
    }

    public void updateCourses(ToDoList toDoList) {
        this.toDoList = toDoList;
        removeAll();
        add(Box.createRigidArea(new Dimension(width, VGAP)));
        add(addButton);
        add(Box.createRigidArea(new Dimension(width, VGAP)));

        for (Course course : toDoList.getCourses()) {
            add(makeCourseComponent(course));
            add(Box.createRigidArea(new Dimension(width, VGAP)));
        }
        updateUI();
    }

    private JComponent makeCourseComponent(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Color background = Color.lightGray;
        JPanel coursePanel = makeCourseTextPanel(course);
        JPanel buttonPanel = makeCourseButtonPanel(course);
        coursePanel.setBackground(background);
        buttonPanel.setBackground(background);
        panel.add(coursePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.setMaximumSize(new Dimension(width, panel.getPreferredSize().height));
        panel.setBackground(background);
        return panel;
    }

    private JPanel makeCourseTextPanel(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, HGAP, VGAP));
        panel.add(new JLabel(course.getName()));
        panel.add(new JLabel("Number of Assignments: " + course.getAssignments().size()));
        panel.add(new JLabel("Number of Exams: " + course.getExams().size()));
        return panel;
    }

    private JPanel makeCourseButtonPanel(Course course) {
        JPanel panel = new JPanel();
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> editCourse(course));
        panel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteCourse(course));
        panel.add(deleteButton);
        return panel;
    }

    private void deleteCourse(Course course) {
        toDoList.remove(course);
        updateCourses(toDoList);
    }

    private void editCourse(Course course) {
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
            updateCourses(toDoList);
        }
    }

    private void addCourse() {
        String s = (String) JOptionPane.showInputDialog(
                null,
                "Enter name of course:",
                "Add Course",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        if ((s != null) && (s.length() > 0)) {
            Course course = new Course(s);
            toDoList.add(course);
            updateCourses(toDoList);
        }
    }
}
