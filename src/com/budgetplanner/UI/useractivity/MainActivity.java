package com.budgetplanner.UI.useractivity;

import com.budgetplanner.UI.Authentication.AuthActivity;
import com.budgetplanner.database.AdvancedExpenseOperations;
import com.budgetplanner.datamodel.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends JFrame {

    private final JLabel centerFrame;
    private User user;

    private JLabel userNameLabel;
    private JLabel userName;
    private JLabel emailLabel;
    private JLabel email;

    private JButton logOutButton;
    private JButton addExpense;
    private JButton showStats;

    private JPanel fragmentPanel;
    private List<String[]> list;

    private String from;
    private String to;

    public MainActivity(User user) {
        this.user = user;

        setTitle("Income planner");
        setBounds(0, 0, 850, 720);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        addProfileSection();

        addNavigationButtons();

        centerFrame = new JLabel();
        centerFrame.setLayout(null);
        centerFrame.setBounds(10, 100, 830, 630);
        add(centerFrame);

        setVisible(true);
    }

    private void addNavigationButtons() {
        addExpense = new JButton("Add expense");
        addExpense.setBounds(60, 60, 320, 40);
        addExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddExpensePanel();
            }
        });
        add(addExpense);

        showStats = new JButton("Show statistics");
        showStats.setBounds(420, 60, 320, 40);
        showStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getParent(), "Select from date ");
                com.qt.datapicker.DatePicker dt = new com.qt.datapicker.DatePicker(null);
                dt.register(new Observer() {
                    @Override
                    public void update(Observable o, Object arg) {
                        Calendar calendar = (Calendar) arg;
                        com.qt.datapicker.DatePicker dp = (com.qt.datapicker.DatePicker) o;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        from = sdf.format(calendar.getTime());
                        JOptionPane.showMessageDialog(getParent(), "Select to date ");
                        com.qt.datapicker.DatePicker dt = new com.qt.datapicker.DatePicker(null);
                        dt.register(new Observer() {
                            @Override
                            public void update(Observable o, Object arg) {
                                Calendar calendar = (Calendar) arg;
                                com.qt.datapicker.DatePicker dp = (com.qt.datapicker.DatePicker) o;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                to = sdf.format(calendar.getTime());
                                list = new AdvancedExpenseOperations(user.getuId()).getSpendingArray(from, to);
                                showExpenseListPanel();
                            }
                        });
                        dt.start(null);
                    }
                });
                dt.start(null);
            }
        });
        add(showStats);
    }

    private void showAddExpensePanel() {
        if (fragmentPanel instanceof ExpenseListPanel) {
            centerFrame.remove(fragmentPanel);
        }
        fragmentPanel = new AddExpensePanel(user.getuId());
        fragmentPanel.setBounds(10, 10, 780, 550);
        fragmentPanel.setVisible(true);
        centerFrame.add(fragmentPanel);
        centerFrame.updateUI();
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
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AuthActivity();
                dispose();
            }
        });
        add(logOutButton);
    }

    private void showExpenseListPanel() {
        if (fragmentPanel instanceof AddExpensePanel) {
            centerFrame.remove(fragmentPanel);
        }
        fragmentPanel = new ExpenseListPanel(list);
        fragmentPanel.setBounds(10, 10, 780, 550);
        fragmentPanel.setVisible(true);
        centerFrame.add(fragmentPanel);
        centerFrame.updateUI();
    }
}
