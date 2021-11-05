package ui;

import model.Sorter;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents window where all user interface is displayed
public class ToDoListAppGUI extends JFrame implements ActionListener {
    private static final String FILE_LOCATION = "./data/todolist.json";
    private final Sorter sorter;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private ToDoList toDoList;

    // MODIFIES: this
    // EFFECTS: provides graphical user interface for managing to-do list
    public ToDoListAppGUI(ToDoList toDoList) {
        super("To-Do List");
        this.toDoList = toDoList;
        sorter = new Sorter();
        jsonReader = new JsonReader(FILE_LOCATION);
        jsonWriter = new JsonWriter(FILE_LOCATION);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // EFFECTS: saves the to-do list to file
    private void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(toDoList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            // File not found
        }
    }

    // MODIFIES: this
    // EFFECTS: loads to-do list from file
    private void loadToDoList() {
        try {
            toDoList = jsonReader.read();
        } catch (IOException e) {
            // File not found
        }
    }
}
