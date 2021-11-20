package ui;

import model.Date;

import javax.swing.*;
import java.awt.*;

// Represents pop-up window for adding and editing an exam
public class JOptionExam extends JPanel {
    private boolean confirmed;
    private String course;
    private Date date;

    // EFFECTS: constructs pop-up window with default values
    public JOptionExam(String[] courses) {
        init(courses, null, courses[0]);
    }

    // EFFECTS: constructs pop-up window with existing values
    public JOptionExam(String[] courses, Date date, String courseName) {
        init(courses, date, courseName);
    }

    // EFFECTS: initializes GUI components for the pop-up window
    private void init(String[] courses, Date date, String courseName) {
        confirmed = false;
        setLayout(new GridLayout(0, 1));
        JComboBox<String> courseField = new JComboBox<>(courses);
        courseField.setSelectedItem(courseName);
        DateInput dateInput;
        if (date != null && date.isSet()) {
            dateInput = new DateInput(date);
        } else {
            dateInput = new DateInput();
        }
        add(new JLabel("Course: "));
        add(courseField);
        add(new JLabel("Date: "));
        add(dateInput);
        showDialog(dateInput, courseField);
    }

    // EFFECTS: display and interpret dialog
    private void showDialog(DateInput dateInput, JComboBox<String> courseField) {
        int result = JOptionPane.showConfirmDialog(
                null, this, "Exam", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            this.course = courseField.getItemAt(courseField.getSelectedIndex());
            this.date = dateInput.getDate();
            confirmed = true;
        }
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
