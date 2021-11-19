package ui;

import model.Date;

import javax.swing.*;
import java.awt.*;

// Represents input for date
public class DateInput extends JPanel {
    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;

    // EFFECTS: constructs date with no existing date
    public DateInput() {
        init(null, null, null);
    }

    // EFFECTS: constructs date with existing date
    public DateInput(Date existingDate) {
        init(existingDate.getYear(), existingDate.getMonth(), existingDate.getDay());
    }

    // EFFECTS: initializes text fields
    private void init(Integer year, Integer month, Integer day) {
        setLayout(new FlowLayout(FlowLayout.LEADING, 4, 0));
        yearField = new JTextField(4);
        monthField = new JTextField(2);
        dayField = new JTextField(2);
        if (year != null) {
            yearField.setText(year.toString());
        }
        if (month != null) {
            monthField.setText(month.toString());
        }
        if (day != null) {
            dayField.setText(day.toString());
        }
        add(yearField);
        add(monthField);
        add(dayField);
    }

    // EFFECTS: returns date
    public Date getDate() {
        return new Date(
                Integer.parseInt(yearField.getText()),
                Integer.parseInt(monthField.getText()),
                Integer.parseInt(dayField.getText()));
    }
}
