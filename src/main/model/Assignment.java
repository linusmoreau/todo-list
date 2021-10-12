package model;

public class Assignment implements Item {
    private String name;
    private Date dueDate;
    private Course course;

    // MODIFIES: tihs
    // EFFECTS:  makes assignment with given name and course and placeholder date
    public Assignment(String name, Course course) {
        this.name = name;
        this.course = course;
        this.dueDate = new Date();
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

    public void setCourse(Course course) {
        this.course = course;
    }
}
