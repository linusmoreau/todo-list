package ui;

import model.ToDoList;

public class Main {

    /*
    TODO:
     Fix bug when there are no items to edit/delete
     Add sortability
     Add special display cases when empty
     */

    // EFFECTS: Runs the user interface loop for commands and responses
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        Interpreter interpreter = new Interpreter(toDoList);

        System.out.println("Welcome to the To-Do List!");
        interpreter.chooseCategory();
    }
}
