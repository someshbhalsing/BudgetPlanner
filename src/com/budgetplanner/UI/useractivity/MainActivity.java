package com.budgetplanner.UI.useractivity;

import com.budgetplanner.UI.Authentication.AuthActivity;
import com.budgetplanner.database.AdvancedExpenseOperations;
import com.budgetplanner.datamodel.User;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends JFrame {

    private final JLabel centerFrame;
    private User user;

    private JLabel userNameLabel;
    private JLabel userName;
    private JLabel emailLabel;
    private JLabel email;
    private JLabel periodLabel;
    private JLabel period;
    private JLabel expenditureLabel;
    private JLabel expenditure;

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
        centerFrame.setBounds(10, 140, 830, 590);
        add(centerFrame);

        periodLabel = new JLabel("Period : ");
        periodLabel.setBounds(20, 110, 55, 30);
        periodLabel.setVisible(false);
        add(periodLabel);

        period = new JLabel();
        period.setBounds(75, 110, 150, 30);
        period.setVisible(false);
        add(period);

        expenditureLabel = new JLabel("Expenditure : ");
        expenditureLabel.setBounds(275, 110, 85, 30);
        expenditureLabel.setVisible(false);
        add(expenditureLabel);

        expenditure = new JLabel();
        expenditure.setBounds(360, 110, 55, 30);
        expenditure.setVisible(false);
        add(expenditure);

        setVisible(true);
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
        logOutButton.addActionListener(e -> {
            new AuthActivity();
            dispose();
        });
        add(logOutButton);
    }

    private void addNavigationButtons() {
        addExpense = new JButton("Add expense");
        addExpense.setBounds(60, 60, 320, 40);
        addExpense.addActionListener(e -> showAddExpensePanel());
        add(addExpense);

        showStats = new JButton("Show statistics");
        showStats.setBounds(420, 60, 320, 40);
        showStats.addActionListener(e -> {
            JOptionPane.showMessageDialog(getParent(), "Select from date ");
            com.qt.datapicker.DatePicker dt = new com.qt.datapicker.DatePicker(null);
            dt.register((o, arg) -> {
                Calendar calendar = (Calendar) arg;
                com.qt.datapicker.DatePicker dp = (com.qt.datapicker.DatePicker) o;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                from = sdf.format(calendar.getTime());
                JOptionPane.showMessageDialog(getParent(), "Select to date ");
                com.qt.datapicker.DatePicker dt1 = new com.qt.datapicker.DatePicker(null);
                dt1.register((o1, arg1) -> {
                    Calendar calendar1 = (Calendar) arg1;
                    com.qt.datapicker.DatePicker dp1 = (com.qt.datapicker.DatePicker) o1;
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    to = sdf1.format(calendar1.getTime());
                    AdvancedExpenseOperations aeo = new AdvancedExpenseOperations(user.getuId());
                    list = aeo.getSpendingArray(from, to);
                    int expenditureAmount = aeo.getTotalSpendings(from, to);
                    expenditure.setText(String.valueOf(expenditureAmount));
                    period.setText(from + " to " + to);
                    period.setVisible(true);
                    periodLabel.setVisible(true);
                    expenditure.setVisible(true);
                    expenditureLabel.setVisible(true);
                    showExpenseListPanel();
                });
                dt1.start(null);
            });
            dt.start(null);
        });
        add(showStats);
    }

    private void showAddExpensePanel() {
        if (fragmentPanel instanceof ExpenseListPanel) {
            centerFrame.remove(fragmentPanel);
        }
        fragmentPanel = new AddExpensePanel(user);
        fragmentPanel.setBounds(10, 10, 780, 550);
        fragmentPanel.setVisible(true);
        period.setVisible(false);
        periodLabel.setVisible(false);
        expenditure.setVisible(false);
        expenditureLabel.setVisible(false);
        centerFrame.add(fragmentPanel);
        centerFrame.updateUI();
    }

    private void showExpenseListPanel() {
        centerFrame.remove(fragmentPanel);
        fragmentPanel = new ExpenseListPanel(list);
        fragmentPanel.setBounds(10, 10, 780, 520);
        fragmentPanel.setVisible(true);
        centerFrame.add(fragmentPanel);
        centerFrame.updateUI();
    }
}
