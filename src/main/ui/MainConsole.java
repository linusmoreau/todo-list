package ui;

import model.ToDoList;

// Executable file for console version
public class MainConsole {

    // EFFECTS: runs the user interface loop for commands and responses
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        new ToDoListAppConsole(toDoList);
        toDoList.quit();
    }
}
