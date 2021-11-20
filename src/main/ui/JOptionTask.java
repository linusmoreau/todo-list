package ui;

import model.Date;

import javax.swing.*;
import java.awt.*;

// Represents pop-up window for adding and editing an exam
public class JOptionTask extends JPanel {
    private boolean confirmed;
    private String name;
    private Date date;
    private String desc;

    // EFFECTS: constructs pop-up window with default values
    public JOptionTask() {
        init(null, null, null);
    }

    // EFFECTS: constructs pop-up window with existing values
    public JOptionTask(String name, Date date, String desc) {
        init(name, date, desc);
    }

    // EFFECTS: initializes GUI components for the pop-up window
    private void init(String name, Date date, String desc) {
        confirmed = false;
        setLayout(new GridLayout(0, 1));
        JTextField nameField = new JTextField(name, 24);
        DateInput dateInput;
        if (date != null) {
            dateInput = new DateInput(date);
        } else {
            dateInput = new DateInput();
        }
        JTextField descField = new JTextField(desc);
        add(new JLabel("Name: "));
        add(nameField);
        add(new JLabel("Date: "));
        add(dateInput);
        add(new JLabel("Description: "));
        add(descField);
        showDialog(nameField, dateInput, descField);
    }

    // EFFECTS: display and interpret dialog
    private void showDialog(JTextField nameField, DateInput dateInput, JTextField descInput) {
        int result = JOptionPane.showConfirmDialog(
                null, this, "Task", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            this.name = nameField.getText();
            this.date = dateInput.getDate();
            this.desc = descInput.getText();
            confirmed = true;
        }
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public boolean getConfirmed() {
        return confirmed;
    }
}
