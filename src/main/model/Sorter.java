package model;

import java.util.ArrayList;

public class Sorter {
    public ArrayList<Assignment> sortedAssignments(ArrayList<Assignment> assignments) {
        ArrayList<Assignment> sorted = (ArrayList<Assignment>) assignments.clone();
        sorted.sort(Assignment::compareTo);
        return sorted;
    }

    public ArrayList<Exam> sortedExams(ArrayList<Exam> exams) {
        ArrayList<Exam> sorted = (ArrayList<Exam>) exams.clone();
        sorted.sort(Exam::compareTo);
        return sorted;
    }

    public ArrayList<Task> sortedTasks(ArrayList<Task> tasks) {
        ArrayList<Task> sorted = (ArrayList<Task>) tasks.clone();
        sorted.sort(Task::compareTo);
        return sorted;
    }
}
