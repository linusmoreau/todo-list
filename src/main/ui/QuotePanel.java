package ui;

import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays quotes
public class QuotePanel extends TabbedPanel {
    private final QuoteListPanel quoteListPanel;

    // EFFECTS: constructs panel for quotes
    public QuotePanel(ToDoList toDoList, ToDoListAppGUI frame) {
        super(frame);
        quoteListPanel = new QuoteListPanel(frame);
        add(new JScrollPane(quoteListPanel), BorderLayout.CENTER);
        update(toDoList);
    }

    // EFFECTS: updates panel for changes in to-do list
    public void update(ToDoList toDoList) {
        quoteListPanel.update(toDoList, toDoList.getQuotes());
    }

    // EFFECTS: adds a new quote
    protected void add() {
        quoteListPanel.add();
    }
}
