package com.budgetplanner.UI.Authentication;

import com.budgetplanner.UI.Authentication.callbacks.OnLoginEventListener;
import com.budgetplanner.UI.Authentication.callbacks.OnRegistrationCompleteListener;
import com.budgetplanner.UI.useractivity.MainActivity;
import com.budgetplanner.datamodel.User;

import javax.swing.*;
import java.awt.*;

public class AuthActivity extends JFrame implements OnRegistrationCompleteListener, OnLoginEventListener {

    private JLabel centerFrame;
    private JPanel fragmentPanel;


    public AuthActivity() {

        setTitle("Authentication");
        setBounds(0, 0, 800, 720);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        centerFrame = new JLabel();
        centerFrame.setLayout(null);
        add(centerFrame);

        showLoginPanel();

        setVisible(true);

    }

    public static void main(String[] args) {
        new AuthActivity();
    }

    private void showRegisterPanel() {
        if (fragmentPanel instanceof LoginPanel) {
            centerFrame.remove(fragmentPanel);
        }
        fragmentPanel = new RegisterPanel(this);
        fragmentPanel.setBounds(100, 20, 720, 760);
        fragmentPanel.setVisible(true);
        centerFrame.add(fragmentPanel);
        centerFrame.updateUI();
    }

    private void showLoginPanel() {
        if (fragmentPanel instanceof RegisterPanel) {
            centerFrame.remove(fragmentPanel);
        }
        fragmentPanel = new LoginPanel(this);
        fragmentPanel.setBounds(100, 20, 720, 760);
        fragmentPanel.setVisible(true);
        centerFrame.add(fragmentPanel);
        centerFrame.updateUI();
    }

    @Override
    public void onRegistrationComplete() {
        JOptionPane.showMessageDialog(getParent(), "Registration complete");
        showLoginPanel();
    }

    @Override
    public void returnToLogin() {
        showLoginPanel();
    }

    @Override
    public void onLoginSuccessful(User user) {
        System.out.println("Login successful for user : " + user.getUserName());
        new MainActivity(user);
        dispose();
    }

    @Override
    public void onRegisterPressed() {
        showRegisterPanel();
    }
}
