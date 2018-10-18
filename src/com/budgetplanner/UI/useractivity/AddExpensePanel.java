package com.budgetplanner.UI.useractivity;

import com.budgetplanner.database.AdvancedExpenseOperations;
import com.budgetplanner.datamodel.Expense;
import com.budgetplanner.datamodel.User;
import com.budgetplanner.datamodel.Validations;

import javax.swing.*;
import java.awt.*;

class AddExpensePanel extends JPanel {

    private static final int NEXT_LINE = 45;
    private User user;
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel paymentMethodLabel;
    private JLabel amountLabel;

    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private ButtonGroup payMethodGroup;
    private JRadioButton cash;
    private JRadioButton cheque;
    private JRadioButton netbanking;
    private JTextField amountTextField;
    private JLabel label;

    private JButton submit;

    AddExpensePanel(User user) {
        this.user = user;
        setLayout(new BorderLayout());
        label = new JLabel();
        label.setIcon(new ImageIcon("C:\\Users\\somesh\\IntelliJIDEAProjects\\BudgetPlanner\\src\\com\\budgetplanner\\abc.jpg"));
        label.setLayout(null);
        add(label);
        createLayout();
    }

    private void createLayout() {
        createLabels();
        createTextFields();

        submit = new JButton("Submit");
        submit.setBounds(80, 265, 400, 30);
        submit.addActionListener(e -> {
            if (Validations.isInvalidString(nameTextField.getText(), 3)) {
                JOptionPane.showMessageDialog(getParent(), "Invalid name");
                return;
            }
            if (Validations.isInvalidString(descriptionTextField.getText(), 3)) {
                JOptionPane.showMessageDialog(getParent(), "Invalid description");
                return;
            }
            if (!Validations.isNumber(amountTextField.getText())) {
                JOptionPane.showMessageDialog(getParent(), "Invalid amount");
                return;
            }
            if (!new AdvancedExpenseOperations(user.getuId())
                    .validateSpendings(Integer.parseInt(amountTextField.getText()), user.getMonthlyBudget())) {
                int choice = JOptionPane.showConfirmDialog(
                        getParent(),
                        "Still wish to insert?",
                        "Amount exceeds your budget",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                System.out.println(choice);
                if (choice != 0)
                    return;
            }
            String method = null;
            if (cash.isSelected()) {
                method = "cash";
            }
            if (cheque.isSelected()) {
                method = "cheque";
            }
            if (netbanking.isSelected()) {
                method = "netbanking";
            }
            Expense expense = new Expense(
                    0,
                    null,
                    nameTextField.getText(),
                    descriptionTextField.getText(),
                    Integer.parseInt(amountTextField.getText()),
                    method
            );
            new AdvancedExpenseOperations(user.getuId()).insert(expense);
            JOptionPane.showMessageDialog(getParent(), "Inserted succesfully");
        });
        label.add(submit);
    }

    private void createTextFields() {
        int y = 55;

        nameTextField = new JTextField();
        nameTextField.setBounds(150, y, 400, 40);
        label.add(nameTextField);
        y += NEXT_LINE;

        descriptionTextField = new JTextField();
        descriptionTextField.setBounds(150, y, 400, 40);
        label.add(descriptionTextField);
        y += NEXT_LINE;


        cash = new JRadioButton("Cash");
        cheque = new JRadioButton("Cheque");
        netbanking = new JRadioButton("Net-Banking");
        cash.setSelected(true);
        cash.setBounds(150, y, 60, 40);
        cheque.setBounds(210, y, 70, 40);
        netbanking.setBounds(280, y, 100, 40);
        label.add(cash);
        label.add(cheque);
        label.add(netbanking);
        payMethodGroup = new ButtonGroup();
        payMethodGroup.add(cash);
        payMethodGroup.add(cheque);
        payMethodGroup.add(netbanking);

//        payMethodTextField = new JTextField();
//        payMethodTextField.setBounds(150, y, 400, 40);
//        add(payMethodTextField);
        y += NEXT_LINE;

        amountTextField = new JTextField();
        amountTextField.setBounds(150, y, 400, 40);
        label.add(amountTextField);
    }

    private void createLabels() {
        int y = 10;
        titleLabel = new JLabel("<html><h1><font color='black'>Add Expense</font><h1></html>");
        titleLabel.setBounds(200, y, 520, 40);
        label.add(titleLabel);
        y += NEXT_LINE;

        nameLabel = new JLabel("Name : ");
        nameLabel.setBounds(10, y, 100, 40);
        label.add(nameLabel);
        y += NEXT_LINE;

        descriptionLabel = new JLabel("Description : ");
        descriptionLabel.setBounds(10, y, 100, 40);
        label.add(descriptionLabel);
        y += NEXT_LINE;

        paymentMethodLabel = new JLabel("Payment method :");
        paymentMethodLabel.setBounds(10, y, 100, 40);
        label.add(paymentMethodLabel);
        y += NEXT_LINE;

        amountLabel = new JLabel("Amount : ");
        amountLabel.setBounds(10, y, 100, 40);
        label.add(amountLabel);
    }
}
