package ui;

import model.Course;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays courses
public class CoursePanel extends TabbedPanel {
    private final CourseListPanel courseListPanel;

    // EFFECTS: constructs panel to display courses
    public CoursePanel(ToDoList toDoList, ToDoListAppGUI frame) {
        super(frame);
        courseListPanel = new CourseListPanel(frame);
        add(new JScrollPane(courseListPanel), BorderLayout.CENTER);
        update(toDoList);
    }

    // EFFECTS: updates panel to adjust for changes in to-do list
    public void update(ToDoList toDoList) {
        courseListPanel.update(toDoList);
    }

    // EFFECTS: provides dialog to add new course
    protected void add() {
        courseListPanel.add();
    }
}
