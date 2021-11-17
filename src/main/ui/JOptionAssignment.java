package ui;

import model.Date;

import javax.swing.*;
import java.awt.*;

public class JOptionAssignment extends JPanel {
    private boolean confirmed;
    private String name;
    private String course;
    private Date date;

    public JOptionAssignment(String[] courses) {
        confirmed = false;
        setLayout(new GridLayout(0, 1));
        JTextField nameField = new JTextField(24);
        JComboBox<String> courseField = new JComboBox<>(courses);
        add(new JLabel("Name: "));
        add(nameField);
        add(new JLabel("Course: "));
        add(courseField);

        int result = JOptionPane.showConfirmDialog(
                null, this, "Assignment", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            name = nameField.getText();
            course = courseField.getItemAt(courseField.getSelectedIndex());
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
