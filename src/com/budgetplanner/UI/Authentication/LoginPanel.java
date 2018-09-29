package com.budgetplanner.UI.Authentication;

import com.budgetplanner.UI.Authentication.callbacks.OnLoginEventListener;
import com.budgetplanner.database.AdvancedUserOperations;
import com.budgetplanner.datamodel.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginPanel extends JPanel {

    private static final int NEXT_LINE = 70;
    private OnLoginEventListener mOnLoginEventListener;

    private JTextField userNameTextField;
    private JPasswordField passwordField;

    LoginPanel(OnLoginEventListener mOnLoginEventListener) {
        this.mOnLoginEventListener = mOnLoginEventListener;
        setLayout(null);
        createLayout();
    }

    private void createLayout() {
        int y = 100;

        JLabel titleLabel = new JLabel("<html><h1><font color='black'>Login</font><h1></html>");
        titleLabel.setBounds(200, y, 520, 40);
        add(titleLabel);
        y += NEXT_LINE;

        JLabel userNameLabel = new JLabel("User name : ");
        userNameLabel.setBounds(40, y, 100, 40);
        add(userNameLabel);

        userNameTextField = new JTextField();
        userNameTextField.setBounds(180, y, 250, 40);
        add(userNameTextField);
        y += NEXT_LINE;

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setBounds(40, y, 100, 40);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, y, 250, 40);
        add(passwordField);
        y += NEXT_LINE * 2;

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(60, y, 150, 40);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new AdvancedUserOperations().authenticateUser(
                        userNameTextField.getText(),
                        new String(passwordField.getPassword())
                );
                if (user != null)
                    mOnLoginEventListener.onLoginSuccessful(user);
                else {
                    userNameTextField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(getParent(), "Invalid data");
                }
            }
        });
        add(loginButton);

        JButton registerButtton = new JButton("New user? Register");
        registerButtton.setBounds(260, y, 150, 40);
        registerButtton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mOnLoginEventListener.onRegisterPressed();
            }
        });
        add(registerButtton);
    }


}
