package ui;

import exceptions.QuitException;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {
    private final ToDoList toDoList;
    private final Scanner scanner;

    /*
     *  TODO: Fix bug when there are no items to edit/delete
     *  TODO: Add sortability
     *  TODO: Add specifications for all methods
     */

    public Interpreter(ToDoList toDoList) {
        this.toDoList = toDoList;
        scanner = new Scanner(System.in);
    }

    // EFFECTS: receives and provides display and options for given category
    public void chooseCategory() {
        while (true) {
            System.out.println("Options: [C]ourses, [A]ssignments, [E]xams, [T]asks, [Q]uotes, [M]ovies, or quit");
            System.out.print("Enter: ");
            String input = scanner.nextLine().toLowerCase();
            try {
                callCategory(input);
            } catch (QuitException e) {
                break;
            }
        }
    }

    private void callCategory(String s) throws QuitException {
        switch (s) {
            case "quit":
                throw new QuitException();
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

    private void invalidInput() {
        System.out.println("Invalid input!");
    }

    private void categoryCourses() throws QuitException {
        displayCourses(toDoList.getCourses());
        chooseOptionCourse();
    }

    private void categoryAssignments() throws QuitException {
        displayAssignments(toDoList.getAssignments());
        chooseOptionAssignment();
    }

    private void categoryExams() throws QuitException {
        displayExams(toDoList.getExams());
        chooseOptionExam();
    }

    private void categoryTasks() throws QuitException {
        displayTasks(toDoList.getTasks());
        chooseOptionTask();
    }

    private void categoryQuotes() throws QuitException {
        displayQuotes(toDoList.getQuotes());
        chooseOptionQuote();
    }

    private void categoryMovies() throws QuitException {
        displayMovies(toDoList.getMovies());
        chooseOptionMovie();
    }

    private void displayCourses(ArrayList<Course> courses) {
        System.out.printf("%-32s %-32s %-32s", "Course Name", "Number of Assignments", "Number of Exams");
        for (Course course : courses) {
            System.out.printf("%-32s %-32s %-32s", course.getName(),
                    course.getAssignments().size(), course.getExams().size());
        }
    }

    private void displayAssignments(ArrayList<Assignment> assignments) {
        System.out.println();
        System.out.printf("%8s %-32s %-32s %-32s", "Index", "Assignment", "Course", "Due Date");
        int i = 1;
        for (Assignment assignment : assignments) {
            System.out.printf("%8s %-32s %-32s %-32s",
                    i, assignment.getName(), assignment.getCourse().getName(), assignment.getDueDate().toString());
            i++;
        }
        System.out.println();
    }

    private void displayExams(ArrayList<Exam> exams) {
        System.out.println();
        System.out.printf("%8s %-32s %-32s", "Index", "Course", "Date");
        int i = 1;
        for (Exam exam : exams) {
            System.out.printf("%8s %-32s %-32s", i, exam.getCourse().getName(), exam.getDate().toString());
            i++;
        }
        System.out.println();
    }

    private void displayTasks(ArrayList<Task> tasks) {
        System.out.println();
        System.out.printf("%-32s %-32s %-32s", "Task", "Date", "Description");
        for (Task task : tasks) {
            System.out.printf("%-32s %-32s %-32s", task.getName(), task.getDate(), task.getDesc());
        }
        System.out.println();
    }

    private void displayQuotes(ArrayList<Quote> quotes) {
        System.out.println();
        System.out.printf("%-128s %-32s", "Quote", "Author");
        for (Quote quote : quotes) {
            System.out.printf("%-128s %-32s", quote.getQuote(), quote.getAuthor());
        }
        System.out.println();
    }

    private void displayMovies(ArrayList<Movie> movies) {
        System.out.println();
        System.out.printf("%-32s %-32s", "Movie Name", "Bookmark");
        for (Movie movie : movies) {
            System.out.printf("%-32s %-32s", movie.getName(), movie.getBookmark());
        }
        System.out.println();
    }

    private void chooseOptionCourse() throws QuitException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "q":   throw new QuitException();
            case "a":   addCourse();
                        break;
            case "e":   editCourse(selectCourse());
                        break;
            case "d":   deleteCourse(selectCourse());
                        break;
            default:    invalidInput();
                        break;
        }
    }

    private void chooseOptionAssignment() throws QuitException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "q":   throw new QuitException();
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

    private void chooseOptionExam() throws QuitException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "q":   throw new QuitException();
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

    private void chooseOptionTask() throws QuitException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "q":   throw new QuitException();
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

    private void chooseOptionQuote() throws QuitException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "q":   throw new QuitException();
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

    private void chooseOptionMovie() throws QuitException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "q":   throw new QuitException();
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

    private void displayOptions() {
        System.out.println("Options: [A]dd, [E]dit, [D]elete, or [Q]uit");
    }

    private void editCourse(Course course) throws QuitException {
        while (true) {
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
    }

    private void changeName(Course course) {
        System.out.print("Enter new name: ");
        String input = scanner.nextLine();
        course.setName(input);
    }

    private void modifyAssignments(Course course) throws QuitException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "q":   throw new QuitException();
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

    private void modifyExams(Course course) throws QuitException {
        displayOptions();
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "q":   throw new QuitException();
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

    private void addCourse() {
        System.out.print("Enter course name: ");
        if (!toDoList.add(new Course(scanner.nextLine()))) {
            System.out.println("A course with entered name already exists.");
        }
    }

    private void addAssignment() {
        addAssignment(selectCourse());
    }

    private void addAssignment(Course course) {
        System.out.print("Enter the assignment name: ");
        String input = scanner.nextLine().toLowerCase();
        Assignment assignment = new Assignment(input, course);
        toDoList.add(assignment);
        System.out.print("Enter assignment due date? (y/n): ");
        input = scanner.nextLine().toLowerCase();
        if (input.equals("y")) {
            System.out.println("Enter values for due date.");
            assignment.setDueDate(setDate());
        }
    }

    private void addExam() {
        addExam(selectCourse());
    }

    private void addExam(Course course) {
        System.out.println("Enter values for exam date.");
        Exam exam = new Exam(setDate(), course);
        toDoList.add(exam);
    }

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

    private void addQuote() {
        System.out.print("Enter quote: ");
        String text = scanner.nextLine();
        System.out.print("Enter author's name: ");
        String author = scanner.nextLine();
        toDoList.add(new Quote(text, author));
    }

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

    private Course selectCourse() {
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

    private Assignment selectAssignment(ArrayList<Assignment> assignments) {
        while (true) {
            System.out.print("Enter assignment index: ");
            int n = scanner.nextInt();
            if (n < 1 || n > assignments.size()) {
                System.out.println("Invalid index!");
            } else {
                return assignments.get(n - 1);
            }
        }
    }

    private Exam selectExam(ArrayList<Exam> exams) {
        while (true) {
            System.out.print("Enter assignment index: ");
            int n = scanner.nextInt();
            if (n < 1 || n > exams.size()) {
                System.out.println("Invalid index!");
            } else {
                return exams.get(n - 1);
            }
        }
    }

    private Task selectTask(ArrayList<Task> tasks) {
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

    private Quote selectQuote(ArrayList<Quote> quotes) {
        while (true) {
            System.out.print("Enter quote index: ");
            int n = scanner.nextInt();
            if (n < 1 || n > quotes.size()) {
                System.out.println("Invalid index!");
            } else {
                return quotes.get(n - 1);
            }
        }
    }

    private Movie selectMovie(ArrayList<Movie> movies) {
        while (true) {
            System.out.print("Enter quote index: ");
            int n = scanner.nextInt();
            if (n < 1 || n > movies.size()) {
                System.out.println("Invalid index!");
            } else {
                return movies.get(n - 1);
            }
        }
    }

    private void deleteCourse(Course course) {
        toDoList.remove(course);
    }

    private void deleteAssignment(Assignment assignment) {
        toDoList.remove(assignment);
    }

    private void deleteExam(Exam exam) {
        toDoList.remove(exam);
    }

    private void deleteTask(Task task) {
        toDoList.remove(task);
    }

    private void deleteQuote(Quote quote) {
        toDoList.remove(quote);
    }

    private void deleteMovie(Movie movie) {
        toDoList.remove(movie);
    }

    private void editAssignment(Assignment assignment) {
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

    private void editExam(Exam exam) {
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

    private Date setDate() {
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter month (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter day (1-31): ");
        int day = scanner.nextInt();
        return new Date(year, month, day);
    }
}
