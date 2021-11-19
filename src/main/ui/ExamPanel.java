package ui;

import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays exams
public class ExamPanel extends TabbedPanel {
    private final ExamListPanel examListPanel;

    // EFFECTS: constructs panel for exams
    public ExamPanel(ToDoList toDoList, ToDoListAppGUI frame) {
        super(frame);
        examListPanel = new ExamListPanel(frame);
        add(new JScrollPane(examListPanel), BorderLayout.CENTER);
        update(toDoList);
    }

    // EFFECTS: updates panel for changes in to-do list
    public void update(ToDoList toDoList) {
        examListPanel.update(toDoList, toDoList.getExams());
    }

    // EFFECTS: adds a new exam
    protected void add() {
        examListPanel.addToCourse();
    }
}
