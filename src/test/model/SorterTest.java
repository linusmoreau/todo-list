package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SorterTest {
    Date date1;
    Date date2;
    Date date3;
    Date date4;
    Course course;
    Sorter sorter;

    @BeforeEach
    public void beforeEach() {
        date1 = new Date();
        date2 = new Date(976, 10, 3);
        date3 = new Date(1341, 1, 24);
        date4 = new Date(3451, 2, 23);
        course = new Course("course");
        sorter = new Sorter();
    }

    @Test
    public void testSortedAssignments() {
        Assignment a1 = new Assignment("1", course, date1);
        Assignment a2 = new Assignment("2", course, date2);
        Assignment a3 = new Assignment("0", course, date3);
        Assignment a4 = new Assignment("fascinating", course, date4);
        ArrayList<Assignment> assignments = new ArrayList<>();
        assignments.add(a1);
        assignments.add(a3);
        assignments.add(a2);
        assignments.add(a4);

        assertTrue(a1.compareTo(a2) > 0);
        ArrayList<Assignment> sorted = sorter.sortedAssignments(assignments);
        assertEquals(a2, sorted.get(0));
        assertEquals(a3, sorted.get(1));
        assertEquals(a4, sorted.get(2));
        assertEquals(a1, sorted.get(3));
    }

    @Test
    public void testSortedExams() {
        Exam a1 = new Exam(date1, course);
        Exam a2 = new Exam(date2, course);
        Exam a3 = new Exam(date3, course);
        Exam a4 = new Exam(date4, course);
        ArrayList<Exam> exams = new ArrayList<>();
        exams.add(a1);
        exams.add(a3);
        exams.add(a2);
        exams.add(a4);

        assertTrue(a1.compareTo(a2) > 0);
        ArrayList<Exam> sorted = sorter.sortedExams(exams);
        assertEquals(a2, sorted.get(0));
        assertEquals(a3, sorted.get(1));
        assertEquals(a4, sorted.get(2));
        assertEquals(a1, sorted.get(3));
    }

    @Test
    public void testSortedTasks() {
        Task a1 = new Task("eat", date1);
        Task a2 = new Task("sleep", date2);
        Task a3 = new Task("study", date3);
        Task a4 = new Task("drink", date4);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(a1);
        tasks.add(a3);
        tasks.add(a2);
        tasks.add(a4);

        assertTrue(a1.compareTo(a2) > 0);
        ArrayList<Task> sorted = sorter.sortedTasks(tasks);
        assertEquals(a2, sorted.get(0));
        assertEquals(a3, sorted.get(1));
        assertEquals(a4, sorted.get(2));
        assertEquals(a1, sorted.get(3));
    }
}
