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
        init(courses, null, courses[0], null);
    }

    // EFFECTS: constructs pop-up window with existing values
    public JOptionAssignment(String[] courses, String name, String courseName, Date date) {
        init(courses, name, courseName, date);
    }

    // EFFECTS: initializes GUI components for the pop-up window
    private void init(String[] courses, String name, String courseName, Date dueDate) {
        confirmed = false;
        setLayout(new GridLayout(0, 1));
        JTextField nameField = new JTextField(name, 24);
        JComboBox<String> courseField = new JComboBox<>(courses);
        courseField.setSelectedItem(courseName);
        DateInput dateInput;
        if (dueDate != null && dueDate.isSet()) {
            dateInput = new DateInput(dueDate);
        } else {
            dateInput = new DateInput();
        }
        add(new JLabel("Name: "));
        add(nameField);
        add(new JLabel("Course: "));
        add(courseField);
        add(new JLabel("Date: "));
        add(dateInput);
        showDialog(nameField, courseField, dateInput);
    }

    // EFFECTS: display and interpret dialog
    private void showDialog(JTextField nameField, JComboBox<String> courseField, DateInput dateInput) {
        int result = JOptionPane.showConfirmDialog(
                null, this, "Assignment", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            this.name = nameField.getText();
            this.course = courseField.getItemAt(courseField.getSelectedIndex());
            this.date = dateInput.getDate();
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
