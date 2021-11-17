package ui;

import model.Course;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays courses
public class CoursePanel extends BanneredPanel {
    private ToDoList toDoList;
    private final CourseListPanel courseListPanel;

    public CoursePanel(ToDoList toDoList, int width, ToDoListAppGUI frame) {
        super(width, frame);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> add());
        add(addButton, BorderLayout.NORTH);
        courseListPanel = new CourseListPanel(frame);
        add(courseListPanel, BorderLayout.CENTER);
        update(toDoList);
    }

    public void update(ToDoList toDoList) {
        this.toDoList = toDoList;
        courseListPanel.update(toDoList);
    }

    public void updateAll() {
        frame.updateAll();
    }

    private void add() {
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
