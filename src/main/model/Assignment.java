package model;

// Course assignments with associated names and, optionally, a due date
public class Assignment implements Comparable {
    private String name;
    private Date dueDate;
    private Course course;

    // MODIFIES: this
    // EFFECTS:  makes assignment with given name and course and placeholder date
    public Assignment(String name, Course course) {
        this.name = name;
        this.course = course;
        this.dueDate = new Date();
    }

    // MODIFIES: this
    // EFFECTS:  makes assignment with given name course, and due date
    public Assignment(String name, Course course, Date dueDate) {
        this.name = name;
        this.course = course;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course newCourse) {
        course = newCourse;
    }

    // EFFECTS: returns > 0 if this date is after the given assignment's date
    //          returns == 0 if they have the same date
    //          returns < 0 if this date is before the given assignment's date
    @Override
    public int compareTo(Object o) {
        return (Integer.compare(this.getDueDate().comparator(), ((Assignment) o).getDueDate().comparator()));
    }
}
