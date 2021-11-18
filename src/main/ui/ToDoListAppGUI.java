package ui;

import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents window where all user interface is displayed
public class ToDoListAppGUI extends JFrame {
    protected static final String FONT = "Monospaced";
    protected static final Font DEFAULT_FONT = new Font(ToDoListAppGUI.FONT, Font.PLAIN, 16);
    private static final String FILE_LOCATION = "./data/todolist.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private ToDoList toDoList;
    private Container allPane;
    private CoursePanel coursePanel;
    private AssignmentPanel assignmentPanel;

    // MODIFIES: this
    // EFFECTS: provides graphical user interface for managing to-do list
    public ToDoListAppGUI(ToDoList toDoList) {
        super("To-Do List");
        this.toDoList = toDoList;
        jsonReader = new JsonReader(FILE_LOCATION);
        jsonWriter = new JsonWriter(FILE_LOCATION);

        ImageIcon img = new ImageIcon("./data/icons8-todo-list-96.png");
        setIconImage(img.getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        initWidgets();
    }

    // EFFECTS: Initializes all widgets
    private void initWidgets() {
        allPane = getContentPane();
        initToolbar();
        initTabbedPane();
    }

    // EFFECTS: Initializes side pane containing save and load buttons
    private void initToolbar() {
        JPanel toolbar = new JPanel();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveToDoList());
        toolbar.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> loadToDoList());
        toolbar.add(loadButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        toolbar.add(quitButton);

        toolbar.setBackground(Color.lightGray);
        allPane.add(toolbar, BorderLayout.NORTH);
    }

    // EFFECTS: saves the to-do list to file
    private void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(toDoList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            // File not found
        }
    }

    // MODIFIES: this
    // EFFECTS: loads to-do list from file
    private void loadToDoList() {
        try {
            toDoList = jsonReader.read();
            updateAll();
        } catch (IOException e) {
            // File not found
        }
    }

    // MODIFIES: this
    // EFFECTS: updates displays to match changes in to-do list
    protected void updateAll() {
        coursePanel.update(toDoList);
        assignmentPanel.update(toDoList);
    }

    // EFFECTS: Initializes tabbed pane menu
    private void initTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        allPane.add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addTab("Courses", null, makeCoursePanel(),
                "Courses with associated assignments and exams");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_C);

        tabbedPane.addTab("Assignments", null, makeAssignmentPanel(),
                "Assignments sorted by due date");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_A);

        tabbedPane.addTab("Exams", null, makeExamsPanel(),
                "Exams sorted by date");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_E);

        tabbedPane.addTab("Tasks", null, makeTasksPanel(),
                "Extra-curricular tasks");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_T);

        tabbedPane.addTab("Quotes", null, makeQuotesPanel(),
                "Inspiring and interesting quotes");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_Q);

        tabbedPane.addTab("Movies", null, makeMoviesPanel(),
                "Inspiring and interesting quotes");
        tabbedPane.setMnemonicAt(5, KeyEvent.VK_M);
    }

    // EFFECTS: makes the course panel within a scroll pane
    private JComponent makeCoursePanel() {
        coursePanel = new CoursePanel(toDoList, this);
        return coursePanel;
    }

    // EFFECTS: makes assignment panel
    private JComponent makeAssignmentPanel() {
        assignmentPanel = new AssignmentPanel(toDoList, this);
        return assignmentPanel;
    }

    // EFFECTS: makes exam panel
    private JComponent makeExamsPanel() {
        return new JPanel();
    }

    // EFFECTS: makes task panel
    private JComponent makeTasksPanel() {
        return new JPanel();
    }

    // EFFECTS: makes quote panel
    private JComponent makeQuotesPanel() {
        return new JPanel();
    }

    // EFFECTS: makes movie panel
    private JComponent makeMoviesPanel() {
        return new JPanel();
    }
}