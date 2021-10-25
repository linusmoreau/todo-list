package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(Course course, String name) {
        assertEquals(name, course.getName());
    }

    protected void checkAssignment(Assignment assignment, String name, Date dueDate, Course course) {
        assertEquals(name, assignment.getName());
        assertEquals(dueDate.comparator(), assignment.getDueDate().comparator());
        assertEquals(course.getName(), assignment.getCourse().getName());
    }

    protected void checkExam(Exam exam, Date date, Course course) {
        assertEquals(date.comparator(), exam.getDate().comparator());
        assertEquals(course.getName(), exam.getCourse().getName());
    }

    protected void checkTask(Task task, String name, Date date, String desc) {
        assertEquals(name, task.getName());
        assertEquals(date.comparator(), task.getDate().comparator());
        assertEquals(desc, task.getDesc());
    }

    protected void checkQuote(Quote quote, String s, String author) {
        assertEquals(s, quote.getQuote());
        assertEquals(author, quote.getAuthor());
    }

    protected void checkMovie(Movie movie, String name, String bookmark) {
        assertEquals(name, movie.getName());
        assertEquals(bookmark, movie.getBookmark());
    }
}
