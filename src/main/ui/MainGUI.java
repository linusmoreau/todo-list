package ui;

import model.ToDoList;

// Executable file for graphics user interface version
public class MainGUI {

    // EFFECTS: runs the user interface loop for commands and responses
    public static void main(String[] args) {
        new ToDoListAppGUI(new ToDoList());
    }
}
