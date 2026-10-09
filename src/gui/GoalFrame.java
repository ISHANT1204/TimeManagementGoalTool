package gui;

import dao.GoalDAO;
import model.Goal;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GoalFrame extends JFrame {

    private final User user;
    private final GoalDAO goalDAO;

    private JTable goalTable;
    private DefaultTableModel tableModel;

    public GoalFrame(User user) {

        this.user = user;
        this.goalDAO = new GoalDAO();

        setTitle("My Goals");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel(
                "My Goals - " + user.getName(),
                SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        tableModel = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Title",
                        "Description",
                        "Target",
                        "Deadline",
                        "Status"
                },
                0
        );

        goalTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(goalTable);

        JButton addButton = new JButton("Add Goal");
        JButton refreshButton = new JButton("Refresh");
        JButton deleteButton = new JButton("Delete Goal");
        JButton closeButton = new JButton("Close");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addGoal());

        refreshButton.addActionListener(e -> loadGoals());

        deleteButton.addActionListener(e -> deleteGoal());

        closeButton.addActionListener(e -> dispose());

        loadGoals();
    }

    private void loadGoals() {

        tableModel.setRowCount(0);

        List<Goal> goals = goalDAO.getGoalsByUser(user.getUserId());

        for (Goal goal : goals) {

            tableModel.addRow(new Object[]{
                    goal.getGoalId(),
                    goal.getTitle(),
                    goal.getDescription(),
                    goal.getTarget(),
                    goal.getDeadline(),
                    goal.getStatus()
            });
        }
    }

    private void addGoal() {

        JTextField titleField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField targetField = new JTextField();
        JTextField deadlineField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Goal Title:"));
        panel.add(titleField);

        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        panel.add(new JLabel("Target Hours:"));
        panel.add(targetField);

        panel.add(new JLabel("Deadline (YYYY-MM-DD):"));
        panel.add(deadlineField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add New Goal",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            String title = titleField.getText().trim();
            String description = descriptionField.getText().trim();
            double target = Double.parseDouble(targetField.getText().trim());
            String deadline = deadlineField.getText().trim();

            if (title.isEmpty() || deadline.isEmpty() || target <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid goal details."
                );

                return;
            }

            Goal goal = new Goal(
                    0,
                    user.getUserId(),
                    title,
                    description,
                    target,
                    deadline,
                    "ACTIVE"
            );

            boolean success = goalDAO.addGoal(goal);

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Goal added successfully."
                );

                loadGoals();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add goal."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Target must be a valid number."
            );
        }
    }

    private void deleteGoal() {

        int selectedRow = goalTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a goal first."
            );

            return;
        }

        int goalId = (int) tableModel.getValueAt(selectedRow, 0);

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this goal?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        boolean success = goalDAO.deleteGoal(goalId);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Goal deleted successfully."
            );

            loadGoals();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to delete goal."
            );
        }
    }
}