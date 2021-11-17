package ui;

import model.Assignment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AssignmentListPanel extends ListPanel {

    public AssignmentListPanel(ToDoListAppGUI frame) {
        super(frame);
    }

    public void update(ArrayList<Assignment> assignments) {
        removeAll();
        for (Assignment assignment : assignments) {
            add(makeComponent(assignment));
            add(Box.createRigidArea(new Dimension(0, 16)));
        }
        updateUI();
    }

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

    private JPanel makeTextPanel(Assignment assignment) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 16, 8));

        JLabel name = new JLabel(assignment.getName());
        name.setPreferredSize(new Dimension(256, 24));
        panel.add(name);

        JLabel course = new JLabel(assignment.getCourse().getName());
        panel.add(course);

        return panel;
    }

    private JPanel makeButtonPanel(Assignment assignment) {
        JPanel panel = new JPanel();
//        JButton editButton = new JButton("Edit");
//        editButton.addActionListener(e -> edit(assignment));
//        panel.add(editButton);
//
//        JButton deleteButton = new JButton("Delete");
//        deleteButton.addActionListener(e -> delete(assignment));
//        panel.add(deleteButton);
        return panel;
    }
}
