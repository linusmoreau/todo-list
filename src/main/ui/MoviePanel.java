package ui;

import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents the panel that displays movies
public class MoviePanel extends TabbedPanel {
    private final MovieListPanel movieListPanel;

    // EFFECTS: constructs panel for movies
    public MoviePanel(ToDoList toDoList, ToDoListAppGUI frame) {
        super(frame);
        movieListPanel = new MovieListPanel(frame);
        add(new JScrollPane(movieListPanel), BorderLayout.CENTER);
        update(toDoList);
    }

    // EFFECTS: updates panel for changes in to-do list
    public void update(ToDoList toDoList) {
        movieListPanel.update(toDoList, toDoList.getMovies());
    }

    // EFFECTS: adds a new movie
    protected void add() {
        movieListPanel.add();
    }
}
