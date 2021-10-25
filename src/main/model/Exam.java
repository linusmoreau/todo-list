package model;

import org.json.JSONObject;
import persistence.Writable;

// Course exams set on a date
public class Exam implements Comparable, Writable {
    private Date date;
    private Course course;

    // MODIFIES: this
    // EFFECTS:  makes exam for given date and course
    public Exam(Date date, Course course) {
        this.date = date;
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // EFFECTS: returns > 0 if this date is after the given exam's date
    //          returns == 0 if they have the same date
    //          returns < 0 if this date is before the given exam's date
    @Override
    public int compareTo(Object o) {
        return (Integer.compare(this.getDate().comparator(), ((Exam) o).getDate().comparator()));
    }

    @Override
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("date", date.toJson());
        object.put("course", course.getName());
        return object;
    }
}
