package com.budgetplanner.UI.Authentication;

import com.budgetplanner.UI.Authentication.callbacks.OnRegistrationCompleteListener;
import com.budgetplanner.database.AdvancedUserOperations;
import com.budgetplanner.datamodel.User;
import com.budgetplanner.datamodel.Validations;

import javax.swing.*;

class RegisterPanel extends JPanel {

    private static final int NEXT_LINE = 45;
    private OnRegistrationCompleteListener mOnRegistrationCompleteListener;
    private JTextField nameTextField;
    private JPasswordField passwordField;
    private JTextField streetTextField;
    private JTextField cityTextField;
    private static final String[] states = {
            "Andaman and Nicobar Islands",
            "Andhra Pradesh", "Arunachal Pradesh",
            "Assam",
            "Bihar",
            "Chandigarh",
            "Chhattisgarh",
            "Dadra and Nagar Haveli",
            "Daman and Diu",
            "Delhi",
            "Goa",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jammu and Kashmir",
            "Jharkhand",
            "Karnataka",
            "Kerala",
            "Lakshadweep",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Meghalaya",
            "Mizoram",
            "Nagaland",
            "Orissa",
            "Pondicherry",
            "Punjab",
            "Rajasthan",
            "Sikkim",
            "Tamil Nadu",
            "Telangana",
            "Tripura",
            "Uttaranchal",
            "Uttar Pradesh",
            "West Bengal"
    };
    private JComboBox<String> stateTextField;
    private JTextField phoneNoTextField;
    private JTextField emailAddressTextField;
    private JTextField monthlyIncomeTextField;
    private JComboBox<String> countryTextField;

    RegisterPanel(OnRegistrationCompleteListener mOnRegistrationCompleteListener) {

        super();
        setLayout(null);
        this.mOnRegistrationCompleteListener = mOnRegistrationCompleteListener;
        createLayout();
    }

    private void createLayout() {
        createLabels();
        createTextFields();
        createButtons();
    }

    private void createButtons() {

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, 550, 175, 40);
        registerButton.addActionListener(e -> {
            System.out.println("Registration clicked");
            User user;
            try {
                user = new User(
                        0,
                        nameTextField.getText(),
                        new String(passwordField.getPassword()),
                        streetTextField.getText(),
                        cityTextField.getText(),
                        stateTextField.getItemAt(stateTextField.getSelectedIndex()),
                        countryTextField.getItemAt(countryTextField.getSelectedIndex()),
                        phoneNoTextField.getText(),
                        emailAddressTextField.getText(),
                        Integer.parseInt(monthlyIncomeTextField.getText())
                );
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(getParent(), "validation failed");
                return;
            }
            if (Validations.validateUser(user)) {
                if (new AdvancedUserOperations().insert(user)) {
                    mOnRegistrationCompleteListener.onRegistrationComplete();
                } else {
                    JOptionPane.showMessageDialog(getParent(), "Operation failed");
                }
            } else {
                JOptionPane.showMessageDialog(getParent(), "validation failed");
            }
        });
        add(registerButton);


        JButton loginButton = new JButton("Back to login");
        loginButton.setBounds(325, 550, 175, 40);
        loginButton.addActionListener(e -> mOnRegistrationCompleteListener.returnToLogin());
        add(loginButton);


    }

    private void createTextFields() {
        int y = 70;

        nameTextField = new JTextField();
        nameTextField.setBounds(150, y, 400, 40);
        add(nameTextField);
        y += NEXT_LINE;

        passwordField = new JPasswordField();
        passwordField.setBounds(150, y, 400, 40);
        add(passwordField);
        y += NEXT_LINE * 2;

        streetTextField = new JTextField();
        streetTextField.setBounds(150, y, 400, 40);
        add(streetTextField);
        y += NEXT_LINE;

        cityTextField = new JTextField();
        cityTextField.setBounds(150, y, 400, 40);
        add(cityTextField);
        y += NEXT_LINE;

        stateTextField = new JComboBox<>();
        for (String state : states) {
            stateTextField.addItem(state);
        }
        stateTextField.setBounds(150, y, 400, 40);
        add(stateTextField);
        y += NEXT_LINE;

        countryTextField = new JComboBox<>();
        countryTextField.addItem("India");
        countryTextField.setBounds(150, y, 400, 40);
        add(countryTextField);
        y += NEXT_LINE;

        phoneNoTextField = new JTextField();
        phoneNoTextField.setBounds(150, y, 400, 40);
        add(phoneNoTextField);
        y += NEXT_LINE;

        emailAddressTextField = new JTextField();
        emailAddressTextField.setBounds(150, y, 400, 40);
        add(emailAddressTextField);
        y += NEXT_LINE;

        monthlyIncomeTextField = new JTextField();
        monthlyIncomeTextField.setBounds(150, y, 400, 40);
        add(monthlyIncomeTextField);

    }

    private void createLabels() {
        int y = 10;

        JLabel titleLabel = new JLabel("<html><h1><font color='black'>Registration</font><h1></html>");
        titleLabel.setBounds(200, y, 520, 40);
        add(titleLabel);
        y += NEXT_LINE;

        JLabel usernameLabel = new JLabel("User Name : ");
        usernameLabel.setBounds(10, y, 100, 40);
        add(usernameLabel);
        y += NEXT_LINE;

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setBounds(10, y, 100, 40);
        add(passwordLabel);
        y += NEXT_LINE;

        JLabel addressLabel = new JLabel("Address : ");
        addressLabel.setBounds(10, y, 100, 40);
        add(addressLabel);
        y += NEXT_LINE;

        JLabel streetLabel = new JLabel("Street : ");
        streetLabel.setBounds(10, y, 100, 40);
        add(streetLabel);
        y += NEXT_LINE;

        JLabel cityLabel = new JLabel("City : ");
        cityLabel.setBounds(10, y, 100, 40);
        add(cityLabel);
        y += NEXT_LINE;

        JLabel stateLabel = new JLabel("State : ");
        stateLabel.setBounds(10, y, 100, 40);
        add(stateLabel);
        y += NEXT_LINE;

        JLabel countryLabel = new JLabel("Country : ");
        countryLabel.setBounds(10, y, 100, 40);
        add(countryLabel);
        y += NEXT_LINE;

        JLabel phoneNoLabel = new JLabel("Phone number : ");
        phoneNoLabel.setBounds(10, y, 100, 40);
        add(phoneNoLabel);
        y += NEXT_LINE;

        JLabel emailAddressLabel = new JLabel("Email address : ");
        emailAddressLabel.setBounds(10, y, 100, 40);
        add(emailAddressLabel);
        y += NEXT_LINE;

        JLabel monthlyIncomeLabel = new JLabel("Monthly Income : ");
        monthlyIncomeLabel.setBounds(10, y, 100, 40);
        add(monthlyIncomeLabel);

    }


}




































