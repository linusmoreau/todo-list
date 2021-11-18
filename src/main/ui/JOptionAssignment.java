package ui;

import model.Date;

import javax.swing.*;
import java.awt.*;

// Represents pop-up window for adding and editing an assignment
public class JOptionAssignment extends JPanel {
    private boolean confirmed;
    private String name;
    private String course;
    private Date date;

    // EFFECTS: constructs pop-up window with default values
    public JOptionAssignment(String[] courses) {
        init(courses, null, courses[0]);
    }

    // EFFECTS: constructs pop-up window with existing values
    public JOptionAssignment(String[] courses, String name, String courseName) {
        init(courses, name, courseName);
    }

    // EFFECTs: initializes GUI components for the pop-up window
    private void init(String[] courses, String name, String courseName) {
        confirmed = false;
        setLayout(new GridLayout(0, 1));
        JTextField nameField = new JTextField(name, 24);
        JComboBox<String> courseField = new JComboBox<>(courses);
        courseField.setSelectedItem(courseName);

        add(new JLabel("Name: "));
        add(nameField);
        add(new JLabel("Course: "));
        add(courseField);

        int result = JOptionPane.showConfirmDialog(
                null, this, "Assignment", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            this.name = nameField.getText();
            this.course = courseField.getItemAt(courseField.getSelectedIndex());
            confirmed = true;
        }
    }

    public String getName() {
        return name;
    }

    public String getCourseName() {
        return course;
    }

    public Date getDate() {
        return date;
    }

    public boolean getConfirmed() {
        return confirmed;
    }
}
