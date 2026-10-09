package gui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {

    private final User user;

    public UserDashboard(User user) {

        this.user = user;

        setTitle("User Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel(
                "Welcome, " + user.getName(),
                SwingConstants.CENTER
        );

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JButton goalsButton = new JButton("My Goals");
        JButton timeButton = new JButton("Track Time");
        JButton progressButton = new JButton("My Progress");
        JButton logoutButton = new JButton("Logout");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 50, 30, 50)
        );

        buttonPanel.add(goalsButton);
        buttonPanel.add(timeButton);
        buttonPanel.add(progressButton);
        buttonPanel.add(logoutButton);

        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        goalsButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "My Goals screen will open here."
                )
        );

        timeButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Track Time screen will open here."
                )
        );

        progressButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "My Progress screen will open here."
                )
        );

        logoutButton.addActionListener(e -> {

            dispose();

            new LoginFrame().setVisible(true);
        });
    }
}