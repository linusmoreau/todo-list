package ui;

import model.ToDoList;

public class Main {

    // EFFECTS: runs the user interface loop for commands and responses
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        new ToDoListApp(toDoList);
    }
}
