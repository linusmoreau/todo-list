package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseListTest {
    private CourseList courseList;

    @BeforeEach
    public void beforeEach() {
        courseList = new CourseList();
    }

    @Test
    public void testFind() throws CourseAlreadyExists {
        courseList.add(new Course("CPSC 210"));
        Course math = new Course("MATH 200");
        courseList.add(math);
        courseList.add(new Course("STAT 200"));
        assertEquals(3, courseList.size());
        assertEquals(math, courseList.find("MATH 200"));
        assertNull(courseList.find("GEOG 250"));
    }
}
