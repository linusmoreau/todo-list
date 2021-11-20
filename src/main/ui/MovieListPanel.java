package ui;

import model.Movie;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents list panel for movies
public class MovieListPanel extends ListPanel {

    // EFFECTS: constructs list panel for movies
    public MovieListPanel(ToDoListAppGUI frame) {
        super(frame);
    }

    // MODIFIES: this
    // EFFECTS: updates movie display to match given movies
    public void update(ToDoList toDoList, ArrayList<Movie> movies) {
        this.toDoList = toDoList;
        removeAll();
        for (Movie movie : movies) {
            add(makeComponent(movie));
        }
        updateUI();
    }

    // EFFECTS: makes panel for the given movie
    private JComponent makeComponent(Movie movie) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Color background = Color.lightGray;
        JPanel coursePanel = makeTextPanel(movie);
        JPanel buttonPanel = makeButtonPanel(movie);
        coursePanel.setBackground(background);
        buttonPanel.setBackground(background);
        panel.add(coursePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.setMaximumSize(new Dimension(10000, panel.getPreferredSize().height));
        panel.setBackground(background);
        return panel;
    }

    // EFFECTS: makes panel for text displaying movie info
    private JPanel makeTextPanel(Movie movie) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 16, 8));

        JLabel nameLabel = new JLabel(movie.getName());
        JLabel bookmarkLabel = new JLabel(movie.getBookmark());

        nameLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);
        bookmarkLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);

        nameLabel.setPreferredSize(new Dimension(756, nameLabel.getPreferredSize().height));

        panel.add(nameLabel);
        panel.add(bookmarkLabel);

        return panel;
    }

    // EFFECTS: makes panel for buttons allowing for editing and deletion
    private JPanel makeButtonPanel(Movie movie) {
        JPanel panel = new JPanel();
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        editButton.addActionListener(e -> edit(movie));
        deleteButton.addActionListener(e -> delete(movie));
        panel.add(editButton);
        panel.add(deleteButton);
        return panel;
    }

    // MODIFIES: toDoList
    // EFFECTS:  makes changes to movie, affecting the model and rest of GUI
    private void edit(Movie movie) {
        JOptionMovie panel = new JOptionMovie(movie.getName(), movie.getBookmark());
        if (panel.getConfirmed()) {
            movie.setName(panel.getName());
            movie.setBookmark(panel.getBookmark());
            frame.updateAll();
        }
    }

    // MODIFIES: toDoList
    // EFFECTS: deletes movie, affecting the model and rest of GUI
    private void delete(Movie movie) {
        toDoList.remove(movie);
        frame.updateAll();
    }

    // MODIFIES: toDoList
    // EFFECTS: provides dialog for adding a new movie
    protected void add() {
        JOptionMovie panel = new JOptionMovie();
        if (panel.getConfirmed()) {
            Movie movie = new Movie(panel.getName(), panel.getBookmark());
            toDoList.add(movie);
            frame.updateAll();
        }
    }
}
