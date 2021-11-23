package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;

// To-do list for tracking various items
public class ToDoList implements Writable {
    private final CourseList courses;
    private final ArrayList<Assignment> assignments;
    private final ArrayList<Exam> exams;
    private final ArrayList<Task> tasks;
    private final ArrayList<Quote> quotes;
    private final ArrayList<Movie> movies;

    // EFFECTS: create to-do list which tracks various items
    public ToDoList() {
        courses = new CourseList();
        assignments = new ArrayList<>();
        exams = new ArrayList<>();
        tasks = new ArrayList<>();
        quotes = new ArrayList<>();
        movies = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds course to to-do list
    public boolean add(Course course) {
        if (courses.add(course)) {
            logAdd(course);
            return true;
        }
        EventLog.getInstance().logEvent(new Event("Failed to add course to to-do list"));
        return false;
    }

    // REQUIRES: assignment's course is in the course list
    // MODIFIES: this, assignment's course
    // EFFECTS:  adds assignment to to-do list
    public void add(Assignment assignment) {
        assignment.getCourse().add(assignment);
        assignments.add(assignment);
        logAdd(assignment);
    }

    // REQUIRES: exam's course is in the course list
    // MODIFIES: this, exam's course
    // EFFECTS:  adds exam to to-do list
    public void add(Exam exam) {
        exam.getCourse().add(exam);
        exams.add(exam);
        logAdd(exam);
    }

    // MODIFIES: this
    // EFFECTS: adds task to to-do list
    public void add(Task task) {
        tasks.add(task);
        logAdd(task);
    }

    // MODIFIES: this
    // EFFECTS: adds quote to to-do list
    public void add(Quote quote) {
        quotes.add(quote);
        logAdd(quote);
    }

    // MODIFIES: this
    // EFFECTS: adds movie to to-do list
    public void add(Movie movie) {
        movies.add(movie);
        logAdd(movie);
    }

    // MODIFIES: EventLog
    // EFFECTS: logs item added to to-do list
    private void logAdd(Item item) {
        EventLog.getInstance().logEvent(
                new Event("Added " + item.getClass().getSimpleName() + " to to-do list"));
    }

    // REQUIRES: course is in courses
    // MODIFIES: this
    // EFFECTS: removes course from to-do list and all of its associated assignments and exams
    public void remove(Course course) {
        for (Assignment assignment : course.getAssignments()) {
            assignments.remove(assignment);
            logRemove(assignment);
        }
        for (Exam exam : course.getExams()) {
            exams.remove(exam);
            logRemove(exam);
        }
        courses.remove(course);
        logRemove(course);
    }

    // REQUIRES: assignment is in to-do list
    // MODIFIES: this, assignment's course
    // EFFECTS:  removes assignment from to-do list
    public void remove(Assignment assignment) {
        assignment.getCourse().remove(assignment);
        assignments.remove(assignment);
        logRemove(assignment);
    }

    // REQUIRES: exam is in to-do list
    // MODIFIES: this, exam's course
    // EFFECTS:  removes exam rom to-do list
    public void remove(Exam exam) {
        exam.getCourse().remove(exam);
        exams.remove(exam);
        logRemove(exam);
    }

    // REQUIRES: task is in tasks
    // MODIFIES: this
    // EFFECTS:  removes task from to-do list
    public void remove(Task task) {
        tasks.remove(task);
        logRemove(task);
    }

    // REQUIRES: quote is in quotes
    // MODIFIES: this
    // EFFECTS:  removes quote from to-do list
    public void remove(Quote quote) {
        quotes.remove(quote);
        logRemove(quote);
    }

    // REQUIRES: movie is in movies
    // MODIFIES: this
    // EFFECTS:  removes movie from to-do list
    public void remove(Movie movie) {
        movies.remove(movie);
        logRemove(movie);
    }

    // MODIFIES: EventLog
    // EFFECTS: logs item removal from to-do list
    private void logRemove(Item item) {
        EventLog.getInstance().logEvent(
                new Event("Removed " + item.getClass().getSimpleName() + " from to-do list"));
    }

    // EFFECTS: returns the number of courses in to-do list
    public int getNumberOfCourses() {
        return courses.size();
    }

    // EFFECTS: returns the number of assignments in to-do list
    public int getNumberOfAssignments() {
        return assignments.size();
    }

    // EFFECTS: returns the number of exams in to-do list
    public int getNumberOfExams() {
        return exams.size();
    }

    // EFFECTS: returns the number of tasks in to-do list
    public int getNumberOfTasks() {
        return tasks.size();
    }

    // EFFECTS: returns the number of quotes in to-do list
    public int getNumberOfQuotes() {
        return quotes.size();
    }

    // EFFECTS: returns the number of movies in to-do list
    public int getNumberOfMovies() {
        return movies.size();
    }

    public CourseList getCourses() {
        return courses;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Quote> getQuotes() {
        return quotes;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    // EFFECTS: returns JSONArray of assignments
    private JSONArray assignmentsToJson() {
        JSONArray array = new JSONArray();
        for (Assignment assignment : assignments) {
            array.put(assignment.toJson());
        }
        return array;
    }

    // EFFECTS: returns JSONArray of exams
    private JSONArray examsToJson() {
        JSONArray array = new JSONArray();
        for (Exam exam : exams) {
            array.put(exam.toJson());
        }
        return array;
    }

    // EFFECTS: returns JSONArray of tasks
    private JSONArray tasksToJson() {
        JSONArray array = new JSONArray();
        for (Task task : tasks) {
            array.put(task.toJson());
        }
        return array;
    }

    // EFFECTS: returns JSONArray of quotes
    private JSONArray quotesToJson() {
        JSONArray array = new JSONArray();
        for (Quote quote : quotes) {
            array.put(quote.toJson());
        }
        return array;
    }

    // EFFECTS: returns JSONArray of movies
    private JSONArray moviesToJson() {
        JSONArray array = new JSONArray();
        for (Movie movie : movies) {
            array.put(movie.toJson());
        }
        return array;
    }

    // MODIFIES: EventLog
    // EFFECTS: saves to-do list to file
    @Override
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("courses", courses.toJson());
        object.put("assignments", assignmentsToJson());
        object.put("exams", examsToJson());
        object.put("tasks", tasksToJson());
        object.put("quotes", quotesToJson());
        object.put("movies", moviesToJson());
        EventLog.getInstance().logEvent(new Event("Saved to-do list to file"));
        return object;
    }

    // EFFECTS: prints out every event in the event log to console
    public void quit() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }
}
