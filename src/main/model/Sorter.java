package model;

import java.util.ArrayList;

// Sorter which sorts sortable lists
public class Sorter {

    // EFFECTS: returns assignments sorted based on due date
    public ArrayList<Assignment> sortedAssignments(ArrayList<Assignment> assignments) {
        ArrayList<Assignment> sorted = (ArrayList<Assignment>) assignments.clone();
        sorted.sort(Assignment::compareTo);
        return sorted;
    }

    // EFFECTS: returns exams sorted based on exam date
    public ArrayList<Exam> sortedExams(ArrayList<Exam> exams) {
        ArrayList<Exam> sorted = (ArrayList<Exam>) exams.clone();
        sorted.sort(Exam::compareTo);
        return sorted;
    }

    // EFFECTS: returns tasks sorted based on associated date
    public ArrayList<Task> sortedTasks(ArrayList<Task> tasks) {
        ArrayList<Task> sorted = (ArrayList<Task>) tasks.clone();
        sorted.sort(Task::compareTo);
        return sorted;
    }
}
