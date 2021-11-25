package ui;

import model.Task;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents list panel for tasks
public class TaskListPanel extends ListPanel {

    // EFFECTS: constructs list panel for tasks
    public TaskListPanel(ToDoListAppGUI frame) {
        super(frame);
    }

    // MODIFIES: this
    // EFFECTS: updates task display to match given tasks
    public void update(ToDoList toDoList, ArrayList<Task> tasks) {
        this.toDoList = toDoList;
        tasks = sorter.sortedTasks(tasks);
        removeAll();
        for (Task task : tasks) {
            add(makeComponent(task));
        }
        updateUI();
    }

    // EFFECTS: makes panel for the given task
    private JComponent makeComponent(Task task) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Color background = Color.lightGray;
        JPanel coursePanel = makeTextPanel(task);
        JPanel buttonPanel = makeButtonPanel(task);
        coursePanel.setBackground(background);
        buttonPanel.setBackground(background);
        panel.add(coursePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.setMaximumSize(new Dimension(10000, panel.getPreferredSize().height));
        panel.setBackground(background);
        return panel;
    }

    // EFFECTS: makes panel for text displaying task info
    private JPanel makeTextPanel(Task task) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 16, 8));

        JLabel nameLabel = new JLabel(task.getName());
        JLabel dateLabel = new JLabel(task.getDate().toString());
        JLabel descLabel = new JLabel(task.getDesc());

        nameLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);
        dateLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);
        descLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);

        nameLabel.setPreferredSize(new Dimension(256, nameLabel.getPreferredSize().height));
        dateLabel.setPreferredSize(new Dimension(256, dateLabel.getPreferredSize().height));

        panel.add(nameLabel);
        panel.add(dateLabel);
        panel.add(descLabel);

        return panel;
    }

    // EFFECTS: makes panel for buttons allowing for editing and deletion
    private JPanel makeButtonPanel(Task task) {
        JPanel panel = new JPanel();
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        editButton.addActionListener(e -> edit(task));
        deleteButton.addActionListener(e -> delete(task));
        panel.add(editButton);
        panel.add(deleteButton);
        return panel;
    }

    // MODIFIES: toDoList
    // EFFECTS:  makes changes to task, affecting the model and rest of GUI
    private void edit(Task task) {
        JOptionTask panel = new JOptionTask(task.getName(),
                task.getDate(), task.getDesc());
        if (panel.getConfirmed()) {
            task.setName(panel.getName());
            task.setDate(panel.getDate());
            task.setDesc(panel.getDesc());
            frame.updateAll();
        }
    }

    // MODIFIES: toDoList
    // EFFECTS: deletes task, affecting the model and rest of GUI
    private void delete(Task task) {
        toDoList.remove(task);
        frame.updateAll();
    }

    // MODIFIES: toDoList
    // EFFECTS: provides dialog for adding a new task
    protected void add() {
        JOptionTask panel = new JOptionTask();
        if (panel.getConfirmed()) {
            Task task = new Task(panel.getName(), panel.getDate(), panel.getDesc());
            toDoList.add(task);
            frame.updateAll();
        }
    }
}
