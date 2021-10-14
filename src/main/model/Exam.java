package model;

public class Exam {
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
}
