package com.budgetplanner.UI.useractivity;

import com.budgetplanner.datamodel.User;
import org.jetbrains.annotations.TestOnly;

import javax.swing.*;

public class MainActivity extends JFrame {

    private User user;

    private JLabel userNameLabel;
    private JLabel userName;
    private JLabel emailLabel;
    private JLabel email;

    private JButton logOutButton;
    private JButton addExpense;
    private JButton showStats;

    public MainActivity(User user) {
        this.user = user;

        setTitle("Income planner");
        setBounds(0, 0, 800, 720);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        addProfileSection();

        addNavigationButtons();
        setVisible(true);
    }

    @TestOnly
    public static void main(String[] args) {
        new MainActivity(new User(0, "Somesh", null, null, null, null, null, null, "somesh.bhalsing@gmail.com", 0));
    }

    private void addNavigationButtons() {
        addExpense = new JButton("Add expense");
        addExpense.setBounds(60, 60, 320, 40);
        add(addExpense);

        showStats = new JButton("Show statistics");
        showStats.setBounds(420, 60, 320, 40);
        add(showStats);
    }

    private void addProfileSection() {
        userNameLabel = new JLabel("User name : ");
        userNameLabel.setBounds(10, 10, 80, 40);
        add(userNameLabel);

        userName = new JLabel("<html><h3><font color='black'>" + user.getUserName() + "</font></h3></html>");
        userName.setBounds(90, 10, 120, 40);
        add(userName);

        emailLabel = new JLabel("Email address : ");
        emailLabel.setBounds(210, 10, 120, 40);
        add(emailLabel);

        email = new JLabel("<html><h3><font color='black'>" + user.getEmailAddress() + "</font></h3></html>");
        email.setBounds(300, 10, 220, 40);
        add(email);

        logOutButton = new JButton("Log out");
        logOutButton.setBounds(680, 15, 100, 30);
        add(logOutButton);
    }
}
