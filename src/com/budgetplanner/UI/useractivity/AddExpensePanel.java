package com.budgetplanner.UI.useractivity;

import com.budgetplanner.database.AdvancedExpenseOperations;
import com.budgetplanner.datamodel.Expense;
import com.budgetplanner.datamodel.Validations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpensePanel extends JPanel {

    private static final int NEXT_LINE = 45;
    private final int uid;
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel paymentMethodLabel;
    private JLabel amountLabel;

    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JTextField payMethodTextField;
    private JTextField amountTextField;

    private JButton submit;

    public AddExpensePanel(int uid) {
        this.uid = uid;
        setLayout(null);
        createLayout();
    }

    private void createLayout() {
        createLabels();
        createTextFields();

        submit = new JButton("Submit");
        submit.setBounds(80, 265, 400, 30);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Validations.isInvalidString(nameTextField.getText(), 3)) {
                    JOptionPane.showMessageDialog(getParent(), "Invalid name");
                    return;
                }
                if (Validations.isInvalidString(descriptionTextField.getText(), 3)) {
                    JOptionPane.showMessageDialog(getParent(), "Invalid description");
                    return;
                }
                if (Validations.isInvalidString(payMethodTextField.getText(), 3)) {
                    JOptionPane.showMessageDialog(getParent(), "Invalid payment method");
                    return;
                }
                if (!Validations.isNumber(amountTextField.getText())) {
                    JOptionPane.showMessageDialog(getParent(), "Invalid amount");
                    return;
                }
                Expense expense = new Expense(
                        0,
                        null,
                        nameTextField.getText(),
                        descriptionTextField.getText(),
                        Integer.parseInt(amountTextField.getText()),
                        payMethodTextField.getText()
                );
                new AdvancedExpenseOperations(uid).insert(expense);
                JOptionPane.showMessageDialog(getParent(), "Inserted succesfully");
            }
        });
        add(submit);
    }

    private void createTextFields() {
        int y = 55;

        nameTextField = new JTextField();
        nameTextField.setBounds(150, y, 400, 40);
        add(nameTextField);
        y += NEXT_LINE;

        descriptionTextField = new JTextField();
        descriptionTextField.setBounds(150, y, 400, 40);
        add(descriptionTextField);
        y += NEXT_LINE;

        payMethodTextField = new JTextField();
        payMethodTextField.setBounds(150, y, 400, 40);
        add(payMethodTextField);
        y += NEXT_LINE;

        amountTextField = new JTextField();
        amountTextField.setBounds(150, y, 400, 40);
        add(amountTextField);
    }

    private void createLabels() {
        int y = 10;
        titleLabel = new JLabel("<html><h1><font color='black'>Add Expense</font><h1></html>");
        titleLabel.setBounds(200, y, 520, 40);
        add(titleLabel);
        y += NEXT_LINE;

        nameLabel = new JLabel("Name : ");
        nameLabel.setBounds(10, y, 100, 40);
        add(nameLabel);
        y += NEXT_LINE;

        descriptionLabel = new JLabel("Description : ");
        descriptionLabel.setBounds(10, y, 100, 40);
        add(descriptionLabel);
        y += NEXT_LINE;

        paymentMethodLabel = new JLabel("Payment method :");
        paymentMethodLabel.setBounds(10, y, 100, 40);
        add(paymentMethodLabel);
        y += NEXT_LINE;

        amountLabel = new JLabel("Amount : ");
        amountLabel.setBounds(10, y, 100, 40);
        add(amountLabel);
    }
}
