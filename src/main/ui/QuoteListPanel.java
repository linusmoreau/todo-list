package ui;

import model.Quote;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents list panel for quotes
public class QuoteListPanel extends ListPanel {

    // EFFECTS: constructs list panel for quotes
    public QuoteListPanel(ToDoListAppGUI frame) {
        super(frame);
    }

    // MODIFIES: this
    // EFFECTS: updates quote display to match given quotes
    public void update(ToDoList toDoList, ArrayList<Quote> quotes) {
        this.toDoList = toDoList;
        removeAll();
        for (Quote quote : quotes) {
            add(makeComponent(quote));
        }
        updateUI();
    }

    // EFFECTS: makes panel for the given quote
    private JComponent makeComponent(Quote quote) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Color background = Color.lightGray;
        JPanel coursePanel = makeTextPanel(quote);
        JPanel buttonPanel = makeButtonPanel(quote);
        coursePanel.setBackground(background);
        buttonPanel.setBackground(background);
        panel.add(coursePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.setMaximumSize(new Dimension(10000, panel.getPreferredSize().height));
        panel.setBackground(background);
        return panel;
    }

    // EFFECTS: makes panel for text displaying quote info
    private JPanel makeTextPanel(Quote quote) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 16, 8));

        JLabel quoteLabel = new JLabel(quote.getQuote());
        JLabel authorLabel = new JLabel(quote.getAuthor());

        quoteLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);
        authorLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);

        quoteLabel.setPreferredSize(new Dimension(756, quoteLabel.getPreferredSize().height));

        panel.add(quoteLabel);
        panel.add(authorLabel);

        return panel;
    }

    // EFFECTS: makes panel for buttons allowing for editing and deletion
    private JPanel makeButtonPanel(Quote quote) {
        JPanel panel = new JPanel();
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        editButton.addActionListener(e -> edit(quote));
        deleteButton.addActionListener(e -> delete(quote));
        panel.add(editButton);
        panel.add(deleteButton);
        return panel;
    }

    // MODIFIES: toDoList
    // EFFECTS:  makes changes to quote, affecting the model and rest of GUI
    private void edit(Quote quote) {
        JOptionQuote panel = new JOptionQuote(quote.getQuote(), quote.getAuthor());
        if (panel.getConfirmed()) {
            quote.setQuote(panel.getQuote());
            quote.setAuthor(panel.getAuthor());
            frame.updateAll();
        }
    }

    // MODIFIES: toDoList
    // EFFECTS: deletes quote, affecting the model and rest of GUI
    private void delete(Quote quote) {
        toDoList.remove(quote);
        frame.updateAll();
    }

    // MODIFIES: toDoList
    // EFFECTS: provides dialog for adding a new quote
    protected void add() {
        JOptionQuote panel = new JOptionQuote();
        if (panel.getConfirmed()) {
            Quote quote = new Quote(panel.getQuote(), panel.getAuthor());
            toDoList.add(quote);
            frame.updateAll();
        }
    }
}
