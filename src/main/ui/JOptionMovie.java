package ui;

import javax.swing.*;
import java.awt.*;

// Represents pop-up window for adding and editing an exam
public class JOptionMovie extends JPanel {
    private boolean confirmed;
    private String name;
    private String bookmark;

    // EFFECTS: constructs pop-up window with default values
    public JOptionMovie() {
        init(null, null);
    }

    // EFFECTS: constructs pop-up window with existing values
    public JOptionMovie(String name, String author) {
        init(name, author);
    }

    // EFFECTS: initializes GUI components for the pop-up window
    private void init(String name, String bookmark) {
        confirmed = false;
        setLayout(new GridLayout(0, 1));
        JTextField nameField = new JTextField(name, 12);
        JTextField bookmarkField = new JTextField(bookmark);
        add(new JLabel("Name: "));
        add(nameField);
        add(new JLabel("Bookmark: "));
        add(bookmarkField);
        showDialog(nameField, bookmarkField);
    }

    // EFFECTS: display and interpret dialog
    private void showDialog(JTextField nameField, JTextField authorField) {
        int result = JOptionPane.showConfirmDialog(
                null, this, "Quote", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            this.name = nameField.getText();
            this.bookmark = authorField.getText();
            confirmed = true;
        }
    }

    public String getName() {
        return name;
    }

    public String getBookmark() {
        return bookmark;
    }

    public boolean getConfirmed() {
        return confirmed;
    }
}
