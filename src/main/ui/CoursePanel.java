package ui;

import model.Course;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays courses
public class CoursePanel extends TabbedPanel {
    private ToDoList toDoList;
    private final CourseListPanel courseListPanel;

    // EFFECTS: constructs panel to display courses
    public CoursePanel(ToDoList toDoList, int width, ToDoListAppGUI frame) {
        super(width, frame);
        courseListPanel = new CourseListPanel(frame);
        add(courseListPanel, BorderLayout.CENTER);
        update(toDoList);
    }

    // EFFECTS: updates panel to adjust for changes in to-do list
    public void update(ToDoList toDoList) {
        this.toDoList = toDoList;
        courseListPanel.update(toDoList);
    }

    // EFFECTS: provides dialog to add new course
    protected void add() {
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
            updateAll();
        }
    }
}
