package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private final String source;

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses ToDoList from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList list = new ToDoList();
        addCourses(list, jsonObject);
        addAssignments(list, jsonObject);
        addExams(list, jsonObject);
        addTasks(list, jsonObject);
        addQuotes(list, jsonObject);
        addMovies(list, jsonObject);
        return list;
    }

    // EFFECTS: parses JSONArray for courses and adds them to the ToDoList
    private void addCourses(ToDoList list, JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("courses");
        for (Object json : array) {
            JSONObject courseData = (JSONObject) json;
            String name = courseData.getString("name");
            Course course = new Course(name);
            list.add(course);
        }
    }

    // EFFECTS: parses JSONArray for assignments and adds them to the ToDoList
    private void addAssignments(ToDoList list, JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("assignments");
        for (Object json : array) {
            JSONObject data = (JSONObject) json;
            String name = data.getString("name");
            Course course = list.getCourses().get(data.getString("course"));
            Date dueDate = readDate(data.getJSONObject("dueDate"));
            Assignment assignment = new Assignment(name, course, dueDate);
            list.add(assignment);
        }
    }

    // EFFECTS: parses JSONArray for exams and adds them to the ToDoList
    private void addExams(ToDoList list, JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("exams");
        for (Object json : array) {
            JSONObject data = (JSONObject) json;
            Course course = list.getCourses().get(data.getString("course"));
            Date date = readDate(data.getJSONObject("date"));
            Exam exam = new Exam(date, course);
            list.add(exam);
        }
    }

    // EFFECTS: parses JSONArray for tasks and adds them to the ToDoList
    private void addTasks(ToDoList list, JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("tasks");
        for (Object json : array) {
            JSONObject data = (JSONObject) json;
            String name = data.getString("name");
            Date date = readDate(data.getJSONObject("date"));
            String desc = data.getString("desc");
            Task task = new Task(name, date, desc);
            list.add(task);
        }
    }

    // EFFECTS: parses JSONArray for tasks and adds them to the ToDoList
    private void addQuotes(ToDoList list, JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("quotes");
        for (Object json : array) {
            JSONObject data = (JSONObject) json;
            String s = data.getString("quote");
            String author = data.getString("author");
            Quote quote = new Quote(s, author);
            list.add(quote);
        }
    }

    // EFFECTS: parses JSONArray for tasks and adds them to the ToDoList
    private void addMovies(ToDoList list, JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("movies");
        for (Object json : array) {
            JSONObject data = (JSONObject) json;
            String name = data.getString("name");
            String bookmark = data.getString("bookmark");
            Movie movie = new Movie(name, bookmark);
            list.add(movie);
        }
    }

    // EFFECTS: parses JSONObject, constructing and returning corresponding date
    private Date readDate(JSONObject dateData) {
        Date date;
        boolean isSet = dateData.getBoolean("isSet");
        if (isSet) {
            date = new Date(
                    dateData.getInt("year"),
                    dateData.getInt("month"),
                    dateData.getInt("day"));
        } else {
            date = new Date();
        }
        return date;
    }
}
