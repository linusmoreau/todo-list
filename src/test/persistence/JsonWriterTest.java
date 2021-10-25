package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    ToDoList list;

    @BeforeEach
    void beforeEach() {
        list = new ToDoList();
    }

    @Test
    void testInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testEmptyToDoList() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
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
            Course math = new Course("MATH 200");
            Date emptyDate = new Date();
            Date date1 = new Date(2021, 10, 29);
            Date date2 = new Date(2021, 10, 27);
            list.add(cpsc);
            list.add(math);
            list.add(new Assignment("Phase 2", cpsc, date1));
            list.add(new Assignment("Lecture Ticket", cpsc));
            list.add(new Exam(date2, cpsc));
            list.add(new Task("Do IT!"));
            list.add(new Quote("Photography is a tough life: you can be taken, framed, exposed, shot, " +
                    "captured, and hung all in the same day.", "Anonymous"));
            list.add(new Movie("Dr. Strangelove"));


            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDoList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.json");
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
            fail("IOException should not have been thrown");
        }
    }

}
