package model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Objects;

// List of courses that can only contain courses with unique names
public class CourseList extends ArrayList<Course> {

    // MODIFIES: this
    // EFFECTS:  makes course list
    public CourseList() {
        super();
    }

    // MODIFIES: this
    // EFFECTS:  adds course if no existing courses have the same name and returns true, else returns false
    public boolean add(Course course) {
        for (Course c : this) {
            if (Objects.equals(c.getName(), course.getName())) {
                return false;
            }
        }
        super.add(course);
        return true;
    }

    // EFFECTS: returns the course with the given name; if no corresponding course exists, returns null
    public Course get(String name) {
        for (Course course : this) {
            if (Objects.equals(course.getName(), name)) {
                return course;
            }
        }
        return null;
    }

    // EFFECTS: returns this as a JSON array
    public JSONArray toJson() {
        JSONArray array = new JSONArray();
        for (Course course : this) {
            array.put(course.toJson());
        }
        return array;
    }
}
