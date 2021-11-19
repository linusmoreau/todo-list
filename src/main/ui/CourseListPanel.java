package ui;

import model.Course;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;

// Represents list panel for courses
public class CourseListPanel extends ListPanel {
    private ToDoList toDoList;
    private AssignmentListPanel assignmentPanel;
    private ExamListPanel examPanel;

    // EFFECTS: constructs list panel for courses
    public CourseListPanel(ToDoListAppGUI frame) {
        super(frame);
    }

    // MODIFIES: this
    // EFFECTS: updates list to match given to-do list
    public void update(ToDoList toDoList) {
        this.toDoList = toDoList;
        removeAll();
        for (Course course : toDoList.getCourses()) {
            add(makeComponent(course));
            add(Box.createRigidArea(new Dimension(0, 16)));
        }
        updateUI();
    }

    // EFFECTS: makes panel for the given course
    private JComponent makeComponent(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel titlePanel = makeTitlePanel(course);
        JPanel assignmentPanel = makeAssignmentPanel(course);
        JPanel assignmentLabel = makeAssignmentLabel(course);
        JPanel examPanel = makeExamPanel(course);
        JPanel examLabel = makeExamLabel(course);
        panel.add(titlePanel);
        panel.add(assignmentLabel);
        panel.add(assignmentPanel);
        panel.add(examLabel);
        panel.add(examPanel);
        addVerticalMargin(panel);
        panel.setMaximumSize(new Dimension(10000, panel.getPreferredSize().height));
        return panel;
    }

    // EFFECTS: makes panel displaying exams of the given course
    private JPanel makeExamPanel(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        examPanel = new ExamListPanel(frame);
        examPanel.update(toDoList, course.getExams());
        panel.add(examPanel);
        return panel;
    }

    // EFFECTS: makes panel for the exam label
    private JPanel makeExamLabel(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, MARGIN * 2, MARGIN));
        JLabel examLabel = new JLabel("Exams: " + course.getExams().size());
        JButton addButton = new JButton("Add");
        examLabel.setFont(new Font(ToDoListAppGUI.FONT, Font.PLAIN, 24));
        addButton.addActionListener(e -> examPanel.addToCourse(course));
        panel.add(examLabel);
        panel.add(addButton);
        panel.setBackground(BACKGROUND);
        return panel;
    }

    // EFFECTS: makes panel displaying assignments of the given course
    private JPanel makeAssignmentPanel(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        assignmentPanel = new AssignmentListPanel(frame);
        assignmentPanel.update(toDoList, course.getAssignments());
        panel.add(assignmentPanel);
        return panel;
    }

    // EFFECTS: makes panel for the assignment label
    private JPanel makeAssignmentLabel(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, MARGIN * 2, MARGIN));
        JLabel assignmentLabel = new JLabel("Assignments: " + course.getAssignments().size());
        JButton addButton = new JButton("Add");
        assignmentLabel.setFont(new Font(ToDoListAppGUI.FONT, Font.PLAIN, 24));
        addButton.addActionListener(e -> assignmentPanel.addToCourse(course));
        panel.add(assignmentLabel);
        panel.add(addButton);
        panel.setBackground(BACKGROUND);
        return panel;
    }

    // EFFECTS: makes panel with the course's name and buttons
    private JPanel makeTitlePanel(Course course) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, MARGIN * 2, MARGIN));
        JLabel courseLabel = new JLabel(course.getName());
        JPanel buttonPanel = makeButtonPanel(course);
        courseLabel.setFont(new Font(ToDoListAppGUI.FONT, Font.PLAIN, 32));
        panel.add(courseLabel);
        panel.add(buttonPanel);
        panel.setBackground(BACKGROUND);
        return panel;
    }

    // EFFECTS: makes panel with buttons for editing and deletion
    private JPanel makeButtonPanel(Course course) {
        JPanel panel = new JPanel();
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        editButton.addActionListener(e -> edit(course));
        deleteButton.addActionListener(e -> delete(course));
        panel.add(editButton);
        panel.add(deleteButton);
        panel.setBackground(BACKGROUND);
        return panel;
    }

    // MODIFIES: toDoList
    // EFFECTS: provides dialog for editing course
    private void edit(Course course) {
        String s = (String) JOptionPane.showInputDialog(
                null,
                "Enter new course name:",
                "Edit Course",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                course.getName());
        if ((s != null) && (s.length() > 0)) {
            course.setName(s);
            frame.updateAll();
        }
    }

    // MODIFIES: toDoList
    // EFFECTS: deletes course
    private void delete(Course course) {
        toDoList.remove(course);
        frame.updateAll();
    }

    // MODIFIES: toDoList
    // EFFECTS: provides dialog to add new course
    protected void add() {
        String s = (String) JOptionPane.showInputDialog(
                null,
                "Enter name of course:",
                "Add Course",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        if ((s != null) && (s.length() > 0)) {
            Course course = new Course(s);
            toDoList.add(course);
            frame.updateAll();
        }
    }
}
