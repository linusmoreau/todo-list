package ui;

import model.ToDoList;

public class Main {

    /*
    TODO:
     Add a [V]iew option for courses
     */

    // EFFECTS: runs the user interface loop for commands and responses
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        Interpreter interpreter = new Interpreter(toDoList);

        System.out.println("Welcome to the To-Do List!");
        interpreter.chooseCategory();
    }
}
