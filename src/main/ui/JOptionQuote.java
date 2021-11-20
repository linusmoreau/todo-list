package ui;

import model.Date;

import javax.swing.*;
import java.awt.*;

// Represents pop-up window for adding and editing an exam
public class JOptionQuote extends JPanel {
    private boolean confirmed;
    private String quote;
    private String author;

    // EFFECTS: constructs pop-up window with default values
    public JOptionQuote() {
        init(null, null);
    }

    // EFFECTS: constructs pop-up window with existing values
    public JOptionQuote(String quote, String author) {
        init(quote, author);
    }

    // EFFECTS: initializes GUI components for the pop-up window
    private void init(String quote, String author) {
        confirmed = false;
        setLayout(new GridLayout(0, 1));
        JTextField quoteField = new JTextField(quote, 64);
        JTextField authorField = new JTextField(author, 24);
        add(new JLabel("Quote: "));
        add(quoteField);
        add(new JLabel("Author: "));
        add(authorField);
        showDialog(quoteField, authorField);
    }

    // EFFECTS: display and interpret dialog
    private void showDialog(JTextField quoteField, JTextField authorField) {
        int result = JOptionPane.showConfirmDialog(
                null, this, "Quote", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            this.quote = quoteField.getText();
            this.author = authorField.getText();
            confirmed = true;
        }
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getConfirmed() {
        return confirmed;
    }
}
