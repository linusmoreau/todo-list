package ui;

import model.Exam;
import model.Course;
import model.Sorter;
import model.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents list panel for exams
public class ExamListPanel extends ListPanel {
    private static final Sorter sorter = new Sorter();

    // EFFECTS: constructs list panel for exams
    public ExamListPanel(ToDoListAppGUI frame) {
        super(frame);
    }

    // MODIFIES: this
    // EFFECTS: updates exam display to match given exams
    public void update(ToDoList toDoList, ArrayList<Exam> exams) {
        this.toDoList = toDoList;
        exams = sorter.sortedExams(exams);
        removeAll();
        for (Exam exam : exams) {
            add(makeComponent(exam));
        }
        updateUI();
    }

    // EFFECTS: makes panel for the given exam
    private JComponent makeComponent(Exam exam) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Color background = Color.lightGray;
        JPanel coursePanel = makeTextPanel(exam);
        JPanel buttonPanel = makeButtonPanel(exam);
        coursePanel.setBackground(background);
        buttonPanel.setBackground(background);
        panel.add(coursePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.setMaximumSize(new Dimension(10000, panel.getPreferredSize().height));
        panel.setBackground(background);
        return panel;
    }

    // EFFECTS: makes panel for text displaying exam info
    private JPanel makeTextPanel(Exam exam) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 16, 8));

        JLabel courseLabel = new JLabel(exam.getCourse().getName());
        JLabel dateLabel = new JLabel(exam.getDate().toString());

        courseLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);
        dateLabel.setFont(ToDoListAppGUI.DEFAULT_FONT);

        courseLabel.setPreferredSize(new Dimension(256, courseLabel.getPreferredSize().height));
        dateLabel.setPreferredSize(new Dimension(512, dateLabel.getPreferredSize().height));

        panel.add(dateLabel);
        panel.add(courseLabel);

        return panel;
    }

    // EFFECTS: makes panel for buttons allowing for editing and deletion
    private JPanel makeButtonPanel(Exam exam) {
        JPanel panel = new JPanel();
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        editButton.addActionListener(e -> edit(exam));
        deleteButton.addActionListener(e -> delete(exam));
        panel.add(editButton);
        panel.add(deleteButton);
        return panel;
    }

    // MODIFIES: toDoList
    // EFFECTS:  makes changes to exam, affecting the model and rest of GUI
    private void edit(Exam exam) {
        JOptionExam panel = new JOptionExam(toDoList.getCourses().getNames(),
                exam.getDate(), exam.getCourse().getName());
        if (panel.getConfirmed()) {
            exam.setDate(panel.getDate());
            exam.setCourse(toDoList.getCourses().get(panel.getCourseName()));
            frame.updateAll();
        }
    }

    // MODIFIES: toDoList
    // EFFECTS: deletes exam, affecting the model and rest of GUI
    private void delete(Exam exam) {
        toDoList.remove(exam);
        frame.updateAll();
    }

    // MODIFIES: toDoList
    // EFFECTS: provides dialog for adding a new exam
    protected void addToCourse() {
        addToCourse(null);
    }

    // MODIFIES: toDoList
    // EFFECTS: provides dialog for adding a new exam
    // to given course
    protected void addToCourse(Course course) {
        if (toDoList.getCourses().size() > 0) {
            JOptionExam panel;
            if (course == null) {
                panel = new JOptionExam(toDoList.getCourses().getNames());
            } else {
                panel = new JOptionExam(toDoList.getCourses().getNames(),
                        null, course.getName());
            }
            if (panel.getConfirmed()) {
                Exam exam = new Exam(panel.getDate(), toDoList.getCourses().get(panel.getCourseName()));
                toDoList.add(exam);
                frame.updateAll();
            }
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Exams can only be added to existing courses. Currently there are no courses.",
                    "Add exam warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
