package persistence;

import model.Course;
import model.Date;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    ToDoList list;

    @Test
    void testNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testEmptyToDoList() {
        try {
            JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.json");
            list = reader.read();
            assertEquals(0, list.getNumberOfCourses());
            assertEquals(0, list.getNumberOfAssignments());
            assertEquals(0, list.getNumberOfExams());
            assertEquals(0, list.getNumberOfTasks());
            assertEquals(0, list.getNumberOfQuotes());
            assertEquals(0, list.getNumberOfMovies());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testGeneralToDoList() {
        try {
            Course cpsc = new Course("CPSC 210");
            Date emptyDate = new Date();
            Date date1 = new Date(2021, 10, 29);
            Date date2 = new Date(2021, 10, 27);

            JsonReader reader = new JsonReader("./data/testReaderGeneralToDoList.json");
            list = reader.read();
            assertEquals(2, list.getNumberOfCourses());
            checkCourse(list.getCourses().get(0), "CPSC 210");
            checkCourse(list.getCourses().get(1), "MATH 200");
            assertEquals(2, list.getNumberOfAssignments());
            checkAssignment(list.getAssignments().get(0), "Phase 2", date1, cpsc);
            checkAssignment(list.getAssignments().get(1), "Lecture Ticket", emptyDate, cpsc);
            assertEquals(1, list.getNumberOfExams());
            checkExam(list.getExams().get(0), date2, cpsc);
            assertEquals(1, list.getNumberOfTasks());
            checkTask(list.getTasks().get(0), "Do IT!", emptyDate, "");
            assertEquals(1, list.getNumberOfQuotes());
            checkQuote(list.getQuotes().get(0),
                    "Photography is a tough life: you can be taken, framed, exposed, shot, " +
                            "captured, and hung all in the same day.", "Anonymous");
            assertEquals(1, list.getNumberOfMovies());
            checkMovie(list.getMovies().get(0), "Dr. Strangelove", "");
        } catch (IOException e) {
            fail("Unexpected IOException thrown");
        }
    }
}
