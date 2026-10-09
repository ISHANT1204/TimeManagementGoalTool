package gui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private final User admin;

    public AdminDashboard(User admin) {

        this.admin = admin;

        setTitle("Admin Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel(
                "Welcome, Admin " + admin.getName(),
                SwingConstants.CENTER
        );

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JButton usersButton = new JButton("Manage Users");
        JButton settingsButton = new JButton("Goal Settings");
        JButton usageButton = new JButton("System Usage");
        JButton logoutButton = new JButton("Logout");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 50, 30, 50)
        );

        buttonPanel.add(usersButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(usageButton);
        buttonPanel.add(logoutButton);

        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        usersButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Manage Users screen will open here."
                )
        );

        settingsButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Goal Settings screen will open here."
                )
        );

        usageButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "System Usage screen will open here."
                )
        );

        logoutButton.addActionListener(e -> {

            dispose();

            new LoginFrame().setVisible(true);
        });
    }
}