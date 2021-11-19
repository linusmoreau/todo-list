package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.exceptions.BackException;
import ui.exceptions.EmptyException;
import ui.exceptions.QuitException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Provides user dialogue and interprets user inputs
public class ToDoListAppConsole {
    private static final String FILE_LOCATION = "./data/todolist.json";
    private final Scanner scanner;
    private final Sorter sorter;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private ToDoList toDoList;

    // MODIFIES: this
    // EFFECTS: makes dialogue interpreter for console-based user interface
    public ToDoListAppConsole(ToDoList toDoList) {
        this.toDoList = toDoList;
        scanner = new Scanner(System.in);
        sorter = new Sorter();
        jsonReader = new JsonReader(FILE_LOCATION);
        jsonWriter = new JsonWriter(FILE_LOCATION);
        System.out.println("Welcome to the To-Do List!");
        chooseCategory();
    }

    // EFFECTS: displays the commands for courses category and guidance for entry
    private void displayCourseOptions() {
        System.out.println();
        System.out.println("Options: [A]dd, [E]dit, [D]elete, [V]iew, back, or quit");
        System.out.print("Enter: ");
    }

    // EFFECTS: displays the basic commands and guidance for entry
    private void displayOptions() {
        System.out.println();
        System.out.println("Options: [A]dd, [E]dit, [D]elete, back, or quit");
        System.out.print("Enter: ");
    }

    // EFFECTs: displays options for the main menu
    private void displayMenu() {
        System.out.println();
        System.out.println("Menu: [C]ourses, [A]ssignments, [E]xams, [T]asks, [Q]uotes, [M]ovies, "
                + "[S]ave, [L]oad, or quit");
        System.out.print("Enter: ");
    }

    // EFFECTS: receives and provides display and options for given category
    public void chooseCategory() {
        while (true) {
            displayMenu();
            String input = scanner.nextLine().toLowerCase();
            try {
                baseCommands(input);
                if (!saveCommands(input)) {
                    callCategory(input);
                }
            } catch (QuitException e) {
                break;
            } catch (EmptyException | BackException ignored) {
                // pass
            }
        }
    }

    // EFFECTS: calls the given category for dialogue
    private void callCategory(String s) throws QuitException, EmptyException, BackException {
        switch (s) {
            case "c":   categoryCourses();
                        break;
            case "a":   categoryAssignments();
                        break;
            case "e":   categoryExams();
                        break;
            case "t":   categoryTasks();
                        break;
            case "q":   categoryQuotes();
                        break;
            case "m":   categoryMovies();
                        break;
            default:    invalidInput();
                        break;
        }
    }

    // EFFECTS: dialogue for invalid input
    private void invalidInput() {
        System.out.println("Invalid input!");
    }

    // EFFECTS: saves the to-do list to file
    private void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(toDoList);
            jsonWriter.close();
            System.out.println("Saved to-do list to " + FILE_LOCATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + FILE_LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads to-do list from file
    private void loadToDoList() {
        try {
            toDoList = jsonReader.read();
            System.out.println("Loaded to-do list from " + FILE_LOCATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + FILE_LOCATION);
        }
    }

    // EFFECTS: displays courses and provides option dialogue
    private void categoryCourses() throws QuitException, EmptyException, BackException {
        displayCourses(toDoList.getCourses());
        chooseOptionCourse();
    }

    // EFFECTS: displays assignments and provides option dialogue
    private void categoryAssignments() throws QuitException, EmptyException, BackException {
        displayAssignments(toDoList.getAssignments());
        chooseOptionAssignment();
    }

    // EFFECTS: displays exams and provides option dialogue
    private void categoryExams() throws QuitException, EmptyException, BackException {
        displayExams(toDoList.getExams());
        chooseOptionExam();
    }

    // EFFECTS: displays tasks and provides option dialogue
    private void categoryTasks() throws QuitException, EmptyException, BackException {
        displayTasks(toDoList.getTasks());
        chooseOptionTask();
    }

    // EFFECTS: displays quotes and provides option dialogue
    private void categoryQuotes() throws QuitException, EmptyException, BackException {
        displayQuotes(toDoList.getQuotes());
        chooseOptionQuote();
    }

    // EFFECTS: displays movies and provides option dialogue
    private void categoryMovies() throws QuitException, EmptyException, BackException {
        displayMovies(toDoList.getMovies());
        chooseOptionMovie();
    }

    // EFFECTS: displays the assignments and exams of the given course
    private void displayCourse(Course course) {
        displayAssignments(course.getAssignments());
        displayExams(course.getExams());
    }

    // EFFECTS: displays courses and relevant information
    private void displayCourses(ArrayList<Course> courses) {
        System.out.println();
        if (courses.isEmpty()) {
            System.out.println("No courses to display!");
        } else {
            System.out.printf("%-32s %-32s %-32s", "Course Name", "Number of Assignments", "Number of Exams");
            for (Course course : courses) {
                System.out.println();
                System.out.printf("%-32s %-32s %-32s", course.getName(),
                        course.getAssignments().size(), course.getExams().size());
            }
            System.out.println();
        }
    }

    // EFFECTS: displays assignments and relevant information
    private void displayAssignments(ArrayList<Assignment> assignments) {
        System.out.println();
        if (assignments.isEmpty()) {
            System.out.println("No assignments to display!");
        } else {
            ArrayList<Assignment> show = sorter.sortedAssignments(assignments);
            System.out.printf("%-8s %-32s %-32s %-32s", "Index", "Assignment", "Course", "Due Date");
            int i = 1;
            for (Assignment assignment : show) {
                System.out.println();
                System.out.printf("%-8s %-32s %-32s %-32s",
                        i, assignment.getName(), assignment.getCourse().getName(), assignment.getDueDate().toString());
                i++;
            }
            System.out.println();
        }
    }

    // EFFECTS: displays exams and relevant information
    private void displayExams(ArrayList<Exam> exams) {
        System.out.println();
        if (exams.isEmpty()) {
            System.out.println("No exams to display!");
        } else {
            ArrayList<Exam> show = sorter.sortedExams(exams);
            System.out.printf("%-8s %-32s %-32s", "Index", "Course", "Date");
            int i = 1;
            for (Exam exam : show) {
                System.out.println();
                System.out.printf("%-8s %-32s %-32s", i, exam.getCourse().getName(), exam.getDate().toString());
                i++;
            }
            System.out.println();
        }
    }

    // EFFECTS: displays tasks and relevant information
    private void displayTasks(ArrayList<Task> tasks) {
        System.out.println();
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display!");
        } else {
            ArrayList<Task> show = sorter.sortedTasks(tasks);
            System.out.printf("%-8s %-32s %-32s %-32s", "Index", "Task", "Date", "Description");
            int i = 1;
            for (Task task : show) {
                System.out.println();
                System.out.printf("%-8s %-32s %-32s %-32s", i, task.getName(), task.getDate(), task.getDesc());
                i++;
            }
            System.out.println();
        }
    }

    // EFFECTS: displays quotes and relevant information
    private void displayQuotes(ArrayList<Quote> quotes) {
        System.out.println();
        if (quotes.isEmpty()) {
            System.out.println("No quotes to display!");
        } else {
            System.out.printf("%-8s %-128s %-32s", "Index", "Quote", "Author");
            int i = 1;
            for (Quote quote : quotes) {
                System.out.println();
                System.out.printf("%-8s %-128s %-32s", i, quote.getQuote(), quote.getAuthor());
                i++;
            }
            System.out.println();
        }
    }

    // EFFECTS: displays movies and relevant information
    private void displayMovies(ArrayList<Movie> movies) {
        System.out.println();
        if (movies.isEmpty()) {
            System.out.println("No movies to display!");
        } else {
            System.out.printf("%-8s %-32s %-32s", "Index", "Movie Name", "Bookmark");
            int i = 1;
            for (Movie movie : movies) {
                System.out.println();
                System.out.printf("%-8s %-32s %-32s", i, movie.getName(), movie.getBookmark());
                i++;
            }
            System.out.println();
        }
    }

    // EFFECTS: conducts dialogue for options about courses
    private void chooseOptionCourse() throws QuitException, EmptyException, BackException {
        displayCourseOptions();
        String input = scanner.nextLine().toLowerCase();
        baseCommands(input);
        switch (input) {
            case "a":   addCourse();
                        break;
            case "e":   editCourse(selectCourse());
                        break;
            case "d":   deleteCourse(selectCourse());
                        break;
            case "v":   displayCourse(selectCourse());
                        break;
            default:    invalidInput();
                        break;
        }
    }

    // EFFECTS: conducts dialogue for options about assignments
    private void chooseOptionAssignment() throws QuitException, EmptyException, BackException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        baseCommands(input);
        switch (input) {
            case "a":   addAssignment();
                        break;
            case "e":   editAssignment(selectAssignment(toDoList.getAssignments()));
                        break;
            case "d":   deleteAssignment(selectAssignment(toDoList.getAssignments()));
                        break;
            default:    invalidInput();
                        break;
        }
    }

    // EFFECTS: conducts dialogue for options about exams
    private void chooseOptionExam() throws QuitException, EmptyException, BackException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        baseCommands(input);
        switch (input) {
            case "a":   addExam();
                        break;
            case "e":   editExam(selectExam(toDoList.getExams()));
                        break;
            case "d":   deleteExam(selectExam(toDoList.getExams()));
                        break;
            default:    invalidInput();
                        break;
        }
    }

    // EFFECTS: conducts dialogue for options about tasks
    private void chooseOptionTask() throws QuitException, EmptyException, BackException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        baseCommands(input);
        switch (input) {
            case "a":   addTask();
                        break;
            case "e":   editTask(selectTask(toDoList.getTasks()));
                        break;
            case "d":   deleteTask(selectTask(toDoList.getTasks()));
                        break;
            default:    invalidInput();
                        break;
        }
    }

    // EFFECTS: conducts dialogue for options about quotes
    private void chooseOptionQuote() throws QuitException, EmptyException, BackException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        baseCommands(input);
        switch (input) {
            case "a":   addQuote();
                        break;
            case "e":   editQuote(selectQuote(toDoList.getQuotes()));
                        break;
            case "d":   deleteQuote(selectQuote(toDoList.getQuotes()));
                        break;
            default:    invalidInput();
                        break;
        }
    }

    // EFFECTS: conducts dialogue for options about movies
    private void chooseOptionMovie() throws QuitException, EmptyException, BackException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        baseCommands(input);
        switch (input) {
            case "a":   addMovie();
                        break;
            case "e":   editMovie(selectMovie(toDoList.getMovies()));
                        break;
            case "d":   deleteMovie(selectMovie(toDoList.getMovies()));
                        break;
            default:    invalidInput();
                        break;
        }
    }

    // EFFECTS: provides dialogue for editing a course's attributes
    private void editCourse(Course course) throws QuitException, EmptyException, BackException {
        System.out.println("Editable: [N]ame, [A]ssignments, [E]xams");
        System.out.print("Enter: ");
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "n":
                changeName(course);
                return;
            case "a":
                modifyAssignments(course);
                return;
            case "e":
                modifyExams(course);
                return;
            default:
                System.out.println("No such command!");
        }
    }

    // EFFECTS: provides dialogue for changing a course's name
    private void changeName(Course course) {
        System.out.print("Enter new name: ");
        String input = scanner.nextLine();
        course.setName(input);
    }

    // EFFECTS: provides dialogue for modifying data about a course's assignments
    private void modifyAssignments(Course course) throws QuitException, EmptyException, BackException {
        displayAssignments(course.getAssignments());
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        baseCommands(input);
        switch (input) {
            case "a":   addAssignment(course);
                        break;
            case "e":   editAssignment(selectAssignment(course.getAssignments()));
                        break;
            case "d":   deleteAssignment(selectAssignment(course.getAssignments()));
                        break;
            default:    invalidInput();
                        break;
        }
    }

    // EFFECTS: provides dialogue for modifying data about a course's exams
    private void modifyExams(Course course) throws QuitException, EmptyException, BackException {
        displayExams(course.getExams());
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        baseCommands(input);
        switch (input) {
            case "a":   addExam(course);
                        break;
            case "e":   editExam(selectExam(course.getExams()));
                        break;
            case "d":   deleteExam(selectExam(course.getExams()));
                        break;
            default:    invalidInput();
                        break;
        }
    }

    // EFFECTS: provides dialogue for adding a new course
    private void addCourse() {
        System.out.print("Enter course name: ");
        if (!toDoList.add(new Course(scanner.nextLine()))) {
            System.out.println("A course with entered name already exists.");
        }
    }

    // EFFECTS: provides dialogue for adding a new assignment
    private void addAssignment() throws EmptyException {
        addAssignment(selectCourse());
    }

    // EFFECTS: provides dialogue for adding a new assignment
    private void addAssignment(Course course) {
        System.out.print("Enter the assignment name: ");
        Assignment assignment = new Assignment(scanner.nextLine(), course);
        toDoList.add(assignment);
        System.out.print("Enter assignment due date? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Enter values for due date.");
            assignment.setDueDate(setDate());
        }
    }

    // EFFECTS: provides dialogue for adding a new exam
    private void addExam() throws EmptyException {
        addExam(selectCourse());
    }

    // EFFECTS: provides dialogue for adding a new exam
    private void addExam(Course course) {
        System.out.println("Enter values for exam date.");
        Exam exam = new Exam(setDate(), course);
        toDoList.add(exam);
    }

    // EFFECTS: provides dialogue for adding a new task
    private void addTask() {
        String input;
        System.out.print("Enter task name: ");
        Task task = new Task(scanner.nextLine());
        System.out.print("Enter task date? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.println("Enter values for date.");
            task.setDate(setDate());
        }
        System.out.print("Enter task description? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Enter description: ");
            task.setDesc(scanner.nextLine());
        }
        toDoList.add(task);
    }

    // EFFECTS: provides dialogue for adding a new quote
    private void addQuote() {
        System.out.print("Enter quote: ");
        String text = scanner.nextLine();
        System.out.print("Enter author's name: ");
        String author = scanner.nextLine();
        toDoList.add(new Quote(text, author));
    }

    // EFFECTS: provides dialogue for adding a new movie
    private void addMovie() {
        System.out.print("Enter movie name: ");
        Movie movie = new Movie(scanner.nextLine());
        System.out.print("Enter bookmark? (y/n): ");
        String input = scanner.nextLine();
        if (input.equals("y")) {
            System.out.print("Enter bookmark: ");
            movie.setBookmark(scanner.nextLine());
        }
        toDoList.add(movie);
    }

    // EFFECTS: provides dialogue selecting an existing course
    private Course selectCourse() throws EmptyException {
        if (toDoList.getCourses().isEmpty()) {
            System.out.println("No courses to select from!");
            throw new EmptyException();
        }
        while (true) {
            System.out.print("Enter course to select: ");
            String input = scanner.nextLine();
            Course course = toDoList.getCourses().get(input);
            if (course == null) {
                System.out.println("No such course exists!");
            } else {
                return course;
            }
        }
    }

    // EFFECTS: provides dialogue selecting an existing assignment
    private Assignment selectAssignment(ArrayList<Assignment> assignments) throws EmptyException {
        if (assignments.isEmpty()) {
            System.out.println("No assignments to select from!");
            throw new EmptyException();
        }
        while (true) {
            System.out.print("Enter assignment index: ");
            int n = scanner.nextInt();
            scanner.nextLine();
            if (n < 1 || n > assignments.size()) {
                System.out.println("Invalid index!");
            } else {
                return assignments.get(n - 1);
            }
        }
    }

    // EFFECTS: provides dialogue selecting an existing exam
    private Exam selectExam(ArrayList<Exam> exams) throws EmptyException {
        if (exams.isEmpty()) {
            System.out.println("No exams to select from!");
            throw new EmptyException();
        }
        while (true) {
            System.out.print("Enter assignment index: ");
            int n = scanner.nextInt();
            scanner.nextLine();
            if (n < 1 || n > exams.size()) {
                System.out.println("Invalid index!");
            } else {
                return exams.get(n - 1);
            }
        }
    }

    // EFFECTS: provides dialogue selecting an existing task
    private Task selectTask(ArrayList<Task> tasks) throws EmptyException {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to select from!");
            throw new EmptyException();
        }
        while (true) {
            System.out.print("Enter task index: ");
            int n = scanner.nextInt();
            if (n < 1 || n > tasks.size()) {
                System.out.println("Invalid index!");
            } else {
                return tasks.get(n - 1);
            }
        }
    }

    // EFFECTS: provides dialogue selecting an existing quote
    private Quote selectQuote(ArrayList<Quote> quotes) throws EmptyException {
        if (quotes.isEmpty()) {
            System.out.println("No quotes to select from!");
            throw new EmptyException();
        }
        while (true) {
            System.out.print("Enter quote index: ");
            int n = scanner.nextInt();
            scanner.nextLine();
            if (n < 1 || n > quotes.size()) {
                System.out.println("Invalid index!");
            } else {
                return quotes.get(n - 1);
            }
        }
    }

    // EFFECTS: provides dialogue selecting an existing movie
    private Movie selectMovie(ArrayList<Movie> movies) throws EmptyException {
        if (movies.isEmpty()) {
            System.out.println("No movies to select from!");
            throw new EmptyException();
        }
        while (true) {
            System.out.print("Enter movie index: ");
            int n = scanner.nextInt();
            scanner.nextLine();
            if (n < 1 || n > movies.size()) {
                System.out.println("Invalid index!");
            } else {
                return movies.get(n - 1);
            }
        }
    }

    // MODIFIES: toDoList
    // EFFECTS:  deletes course
    private void deleteCourse(Course course) {
        toDoList.remove(course);
    }

    // MODIFIES: toDoList
    // EFFECTS:  deletes assignment
    private void deleteAssignment(Assignment assignment) {
        toDoList.remove(assignment);
    }

    // MODIFIES: toDoList
    // EFFECTS:  deletes exam
    private void deleteExam(Exam exam) {
        toDoList.remove(exam);
    }

    // MODIFIES: toDoList
    // EFFECTS:  deletes task
    private void deleteTask(Task task) {
        toDoList.remove(task);
    }

    // MODIFIES: toDoList
    // EFFECTS:  deletes quote
    private void deleteQuote(Quote quote) {
        toDoList.remove(quote);
    }

    // MODIFIES: toDoList
    // EFFECTS:  deletes movie
    private void deleteMovie(Movie movie) {
        toDoList.remove(movie);
    }

    // MODIFIES: an assignment
    // EFFECTS:  provides dialogue for editing the attributes of an assignment
    private void editAssignment(Assignment assignment) throws EmptyException {
        String input;
        System.out.print("Edit assignment name? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Enter new assignment name: ");
            assignment.setName(scanner.nextLine());
        }
        System.out.print("Edit assignment course? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            assignment.setCourse(selectCourse());
        }
        System.out.print("Edit assignment due date? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.println("Enter values for due date.");
            assignment.setDueDate(setDate());
        }
    }

    // MODIFIES: an exam
    // EFFECTS:  provides dialogue for editing the attributes of an exam
    private void editExam(Exam exam) throws EmptyException {
        String input;
        System.out.print("Edit exam date? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.println("Enter values for exam date.");
            exam.setDate(setDate());
        }
        System.out.print("Edit exam course? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            exam.setCourse(selectCourse());
        }
    }

    // MODIFIES: a task
    // EFFECTS:  provides dialogue for editing the attributes of a task
    private void editTask(Task task) {
        String input;
        System.out.print("Edit task name? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Enter new task name: ");
            task.setName(scanner.nextLine());
        }
        System.out.print("Edit task date? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.println("Enter values for date.");
            task.setDate(setDate());
        }
        System.out.print("Edit task description? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.println("Enter new description");
            task.setDesc(scanner.nextLine());
        }
        toDoList.add(task);
    }

    // MODIFIES: a quote
    // EFFECTS:  provides dialogue for editing the attributes of a quote
    private void editQuote(Quote quote) {
        String input;
        System.out.print("Edit quote? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Enter new quote: ");
            quote.setQuote(scanner.nextLine());
        }
        System.out.print("Edit author? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Enter new author: ");
            quote.setAuthor(scanner.nextLine());
        }
    }

    // MODIFIES: a movie
    // EFFECTS:  provides dialogue for editing the attributes of a movie
    private void editMovie(Movie movie) {
        String input;
        System.out.print("Edit movie name? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Enter new movie name: ");
            movie.setName(scanner.nextLine());
        }
        System.out.print("Edit bookmark? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.print("Enter new bookmark: ");
            movie.setBookmark(scanner.nextLine());
        }
    }

    // EFFECTS: provides dialogue for constructing a date
    private Date setDate() {
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter month (1-12): ");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter day (1-31): ");
        int day = scanner.nextInt();
        scanner.nextLine();
        return new Date(year, month, day);
    }

    // EFFECTS: checks if string corresponds to base commands and throws corresponding exception if it does
    //          else does nothing
    private void baseCommands(String command) throws QuitException, BackException {
        if (command.equalsIgnoreCase("quit")) {
            throw new QuitException();
        } else if (command.equalsIgnoreCase("back")) {
            throw new BackException();
        }
    }

    // EFFECTS: checks if string corresponds to save commands
    //          does corresponding operations if it does and returns true
    //          else returns false
    private boolean saveCommands(String command) {
        if (command.equalsIgnoreCase("s")) {
            saveToDoList();
        } else if (command.equalsIgnoreCase("l")) {
            loadToDoList();
        } else {
            return false;
        }
        return true;
    }
}
