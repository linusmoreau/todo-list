package model;

import java.util.ArrayList;
import java.util.Objects;

public class CourseList {
    private final ArrayList<Course> courseList;

    // MODIFIES: this
    // EFFECTS:  makes course list
    public CourseList() {
        courseList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS:  adds course if no existing courses have the same name, otherwise throws exception
    public void add(Course course) throws CourseAlreadyExists {
        for (Course c : courseList) {
            if (Objects.equals(c.getName(), course.getName())) {
                throw new CourseAlreadyExists();
            }
        }
        courseList.add(course);
    }

    // REQUIRES: course is in the list
    // MODIFIES: this
    // EFFECTS:  removes course from the list
    public void remove(Course course) {
        courseList.remove(course);
    }

    // EFFECTS: returns the course with the given name
    public Course find(String name) {
        for (Course course : courseList) {
            if (Objects.equals(course.getName(), name)) {
                return course;
            }
        }
        return null;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public int size() {
        return courseList.size();
    }
}
