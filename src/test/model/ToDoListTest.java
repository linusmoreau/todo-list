package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    private ToDoList toDoList;

    @BeforeEach
    public void beforeEach() {
        toDoList = new ToDoList();
    }

    @Test
    public void testCourse() throws CourseAlreadyExists {
        Course course = new Course("CPSC 210");
        assertEquals(0, toDoList.getNumberOfCourses());
        toDoList.add(course);
        assertEquals(1, toDoList.getNumberOfCourses());

        Assignment assignment = new Assignment("Project", course);
        assertEquals(0, toDoList.getNumberOfAssignments());
        toDoList.add(assignment);
        assertEquals(1, toDoList.getNumberOfAssignments());

        Exam exam = new Exam(new Date(), course);
        assertEquals(0, toDoList.getNumberOfExams());
        toDoList.add(exam);
        assertEquals(1, toDoList.getNumberOfExams());

        toDoList.remove(course);
        assertEquals(0, toDoList.getNumberOfCourses());
        assertEquals(0, toDoList.getNumberOfAssignments());
        assertEquals(0, toDoList.getNumberOfExams());
    }

    @Test
    public void testAssignment() throws CourseAlreadyExists {
        Course course = new Course("CPSC 210");
        assertEquals(0, toDoList.getNumberOfCourses());
        toDoList.add(course);
        assertEquals(1, toDoList.getNumberOfCourses());

        Assignment assignment = new Assignment("Project", course);
        assertEquals(0, toDoList.getNumberOfAssignments());
        toDoList.add(assignment);
        assertEquals(1, toDoList.getNumberOfAssignments());
        toDoList.remove(assignment);
        assertEquals(0, toDoList.getNumberOfAssignments());
    }

    @Test
    public void testExam() throws CourseAlreadyExists {
        Course course = new Course("CPSC 210");
        assertEquals(0, toDoList.getNumberOfCourses());
        toDoList.add(course);
        assertEquals(1, toDoList.getNumberOfCourses());

        Exam exam = new Exam(new Date(), course);
        assertEquals(0, toDoList.getNumberOfExams());
        toDoList.add(exam);
        assertEquals(1, toDoList.getNumberOfExams());
        toDoList.remove(exam);
        assertEquals(0, toDoList.getNumberOfExams());
    }

    @Test
    public void testTask() {
        Task task = new Task("Buy banana");
        assertEquals(0, toDoList.getNumberOfTasks());
        toDoList.add(task);
        assertEquals(1, toDoList.getNumberOfTasks());
        toDoList.remove(task);
        assertEquals(0, toDoList.getNumberOfTasks());
    }

    @Test
    public void testQuote() {
        Quote quote = new Quote("The greater our knowledge increases, the greater our ignorance unfolds.",
                "John F. Kennedy");
        assertEquals(0, toDoList.getNumberOfQuotes());
        toDoList.add(quote);
        assertEquals(1, toDoList.getNumberOfQuotes());
        toDoList.remove(quote);
        assertEquals(0, toDoList.getNumberOfQuotes());
    }

    @Test
    public void testMovie() {
        Movie movie = new Movie("The Wire");
        assertEquals(0, toDoList.getNumberOfMovies());
        toDoList.add(movie);
        assertEquals(1, toDoList.getNumberOfMovies());
        toDoList.remove(movie);
        assertEquals(0, toDoList.getNumberOfMovies());
    }
}