package ui;

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
    private JTabbedPane tabbedPane;
    private JPanel sidePane;
    private Container allPane;

    // MODIFIES: this
    // EFFECTS: provides graphical user interface for managing to-do list
    public ToDoListAppGUI(ToDoList toDoList) {
        super("To-Do List");
        this.toDoList = toDoList;
        sorter = new Sorter();
        jsonReader = new JsonReader(FILE_LOCATION);
        jsonWriter = new JsonWriter(FILE_LOCATION);

        initWidgets();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
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
        sidePane = new JPanel();
        sidePane.setPreferredSize(new Dimension(200, 200));
        sidePane.setBackground(Color.blue);
        allPane.add(sidePane, BorderLayout.WEST);
    }

    // EFFECTS: Initializes tabbed pane menu
    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Courses", null, makePanel(),"Courses with associated assignments and exams");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_C);

        tabbedPane.addTab("Assignments", null, makePanel(),"Assignments sorted by due date");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_A);

        tabbedPane.addTab("Exams", null, makePanel(),"Exams sorted by date");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_E);

        tabbedPane.addTab("Tasks", null, makePanel(),"Extra-curricular tasks");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_T);

        tabbedPane.addTab("Quotes", null, makePanel(),"Inspiring and interesting quotes");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_Q);

        tabbedPane.addTab("Movies", null, makePanel(),"Inspiring and interesting quotes");
        tabbedPane.setMnemonicAt(5, KeyEvent.VK_M);

        allPane.add(tabbedPane, BorderLayout.CENTER);
    }

    private JComponent makePanel() {
        return new JPanel();
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
        } catch (IOException e) {
            // File not found
        }
    }
}
