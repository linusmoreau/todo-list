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
    public void testCourse() {
        Course course = new Course("CPSC 210");
        assertEquals(0, toDoList.getNumberOfCourses());
        assertTrue(toDoList.add(course));
        assertEquals(1, toDoList.getNumberOfCourses());
        assertEquals(course, toDoList.getCourses().get(0));

        course.setName("Best Course!");
        assertEquals("Best Course!", course.getName());

        assertFalse(toDoList.add(course));

        Assignment assignment = new Assignment("Project", course);
        assertEquals(0, toDoList.getNumberOfAssignments());
        toDoList.add(assignment);
        assertEquals(1, toDoList.getNumberOfAssignments());
        assertEquals(assignment, toDoList.getAssignments().get(0));

        Exam exam = new Exam(new Date(), course);
        assertEquals(0, toDoList.getNumberOfExams());
        toDoList.add(exam);
        assertEquals(1, toDoList.getNumberOfExams());
        assertEquals(course, toDoList.getExams().get(0).getCourse());

        toDoList.remove(course);
        assertEquals(0, toDoList.getNumberOfCourses());
        assertEquals(0, toDoList.getNumberOfAssignments());
        assertEquals(0, toDoList.getNumberOfExams());
    }

    @Test
    public void testAssignment() {
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

        Course newCourse = new Course("STAT 200");
        assignment.setName("Assignment 1");
        assertEquals("Assignment 1", assignment.getName());
        assignment.setCourse(newCourse);
        assertEquals(newCourse, assignment.getCourse());
        Date date = new Date(2021, 10, 16);
        assignment.setDueDate(date);
        assertEquals(date, assignment.getDueDate());
    }

    @Test
    public void testExam() {
        Course course = new Course("CPSC 210");
        assertEquals(0, toDoList.getNumberOfCourses());
        toDoList.add(course);
        assertEquals(1, toDoList.getNumberOfCourses());

        Exam exam = new Exam(new Date(2021, 10, 27), course);
        assertEquals(0, toDoList.getNumberOfExams());
        toDoList.add(exam);
        assertEquals(1, toDoList.getNumberOfExams());
        toDoList.remove(exam);
        assertEquals(0, toDoList.getNumberOfExams());

        Course newCourse = new Course("MATH 200");
        exam.setCourse(newCourse);
        assertEquals(newCourse, exam.getCourse());
        Date date = new Date(2021, 10, 29);
        exam.setDate(date);
        assertEquals(date, exam.getDate());
    }

    @Test
    public void testTask() {
        Task task = new Task("Buy banana");
        assertEquals(0, toDoList.getNumberOfTasks());
        toDoList.add(task);
        assertEquals(1, toDoList.getNumberOfTasks());
        assertEquals(task, toDoList.getTasks().get(0));
        toDoList.remove(task);
        assertEquals(0, toDoList.getNumberOfTasks());

        Date date = new Date(2021, 10, 15);
        task.setDate(date);
        assertEquals(date, task.getDate());
        task.setName("Visit Biodiversity Museum");
        assertEquals("Visit Biodiversity Museum", task.getName());
        task.setDesc("Look at the whale!");
        assertEquals("Look at the whale!", task.getDesc());
    }

    @Test
    public void testQuote() {
        Quote quote = new Quote(
                "The greater our knowledge increases, the greater our ignorance unfolds.",
                "John F. Kennedy");
        assertEquals(0, toDoList.getNumberOfQuotes());
        toDoList.add(quote);
        assertEquals(1, toDoList.getNumberOfQuotes());
        assertEquals(quote, toDoList.getQuotes().get(0));
        toDoList.remove(quote);
        assertEquals(0, toDoList.getNumberOfQuotes());

        quote.setQuote("Extraordinary claims require extraordinary evidence.");
        assertEquals("Extraordinary claims require extraordinary evidence.", quote.getQuote());
        quote.setAuthor("Carl Sagan");
        assertEquals("Carl Sagan", quote.getAuthor());
    }

    @Test
    public void testMovie() {
        Movie movie = new Movie("Breaking Bad");
        assertEquals(0, toDoList.getNumberOfMovies());
        toDoList.add(movie);
        assertEquals(1, toDoList.getNumberOfMovies());
        assertEquals(movie, toDoList.getMovies().get(0));
        toDoList.remove(movie);
        assertEquals(0, toDoList.getNumberOfMovies());

        movie.setName("Better Call Saul");
        assertEquals("Better Call Saul", movie.getName());
        movie.setBookmark("Season 6");
        assertEquals("Season 6", movie.getBookmark());
    }
}