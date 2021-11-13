package ui;

import model.Course;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays courses
public class CoursePanel extends JPanel {
    private ToDoList toDoList;

    public CoursePanel(ToDoList toDoList) {
        this.toDoList = toDoList;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void updateCourses(ToDoList toDoList) {
        this.toDoList = toDoList;
        removeAll();
        for (Course course : toDoList.getCourses()) {
            add(makeCourseComponent(course));
            add(Box.createRigidArea(new Dimension(0, 16)));
        }
        updateUI();
    }

    private JComponent makeCourseComponent(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 8, 4));
        panel.add(new JLabel(course.getName()));
        panel.add(new JLabel("Number of Assignments: " + course.getAssignments().size()));
        panel.add(new JLabel("Number of Exams: " + course.getExams().size()));

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> editCourse(course));
        panel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteCourse(course));
        panel.add(deleteButton);

        panel.setBackground(Color.lightGray);
        panel.setMaximumSize(new Dimension(getWidth() * 3 / 4, 64));
        return panel;
    }

    private void deleteCourse(Course course) {
        toDoList.remove(course);
        updateCourses(toDoList);
    }

    private void editCourse(Course course) {
        //
    }
}
