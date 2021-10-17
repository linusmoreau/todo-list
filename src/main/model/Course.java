package model;

import java.util.ArrayList;

// Course with a name, assignments, and exams
public class Course {
    private String name;
    private final ArrayList<Assignment> assignments;
    private final ArrayList<Exam> exams;

    // MODIFIES: this
    // EFFECTS:  makes task with given name and initializes assignments and exams lists
    public Course(String name) {
        this.name = name;
        assignments = new ArrayList<>();
        exams = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: adds assignment to course
    public void add(Assignment assignment) {
        assignments.add(assignment);
    }

    // MODIFIES: this
    // EFFECTS: adds exam to course
    public void add(Exam exam) {
        exams.add(exam);
    }

    // REQUIRES: assignment was previously added to course
    // MODIFIES: this
    // EFFECTS: removes assignment from course
    public void remove(Assignment assignment) {
        assignments.remove(assignment);
    }

    // REQUIRES: exam was previously added to course
    // MODIFIES: this
    // EFFECTS: removes exam from course
    public void remove(Exam exam) {
        exams.remove(exam);
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }
}
