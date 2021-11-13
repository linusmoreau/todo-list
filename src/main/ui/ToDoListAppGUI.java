package ui;

import model.Course;
import model.Sorter;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents window where all user interface is displayed
public class ToDoListAppGUI extends JFrame implements ActionListener {
    private static final String FILE_LOCATION = "./data/todolist.json";
    private final Sorter sorter;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private ToDoList toDoList;
    private Container allPane;
    private CoursePanel coursePanel;

    // MODIFIES: this
    // EFFECTS: provides graphical user interface for managing to-do list
    public ToDoListAppGUI(ToDoList toDoList) {
        super("To-Do List");
        this.toDoList = toDoList;
        sorter = new Sorter();
        jsonReader = new JsonReader(FILE_LOCATION);
        jsonWriter = new JsonWriter(FILE_LOCATION);

        ImageIcon img = new ImageIcon("./data/icons8-todo-list-96.png");
        setIconImage(img.getImage());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        initWidgets();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    // EFFECTS: Initializes all widgets
    private void initWidgets() {
        allPane = getContentPane();
        initSidePane();
        initTabbedPane();
    }

    // EFFECTS: Initializes side pane containing save and load buttons
    private void initSidePane() {
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
            loadUpdate();
        } catch (IOException e) {
            // File not found
        }
    }

    private void loadUpdate() {
        coursePanel.updateCourses(toDoList);
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

    private JComponent makeCoursePanel() {
        coursePanel = new CoursePanel(toDoList);
        return new JScrollPane(coursePanel);
    }

    private JComponent makeAssignmentPanel() {
        return new JPanel();
    }

    private JComponent makeExamsPanel() {
        return new JPanel();
    }

    private JComponent makeTasksPanel() {
        return new JPanel();
    }

    private JComponent makeQuotesPanel() {
        return new JPanel();
    }

    private JComponent makeMoviesPanel() {
        return new JPanel();
    }
}