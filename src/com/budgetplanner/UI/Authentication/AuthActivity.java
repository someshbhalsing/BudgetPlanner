package com.budgetplanner.UI.Authentication;

import com.budgetplanner.UI.Authentication.callbacks.OnLoginEventListener;
import com.budgetplanner.UI.Authentication.callbacks.OnRegistrationCompleteListener;
import com.budgetplanner.datamodel.User;
import org.jetbrains.annotations.TestOnly;

import javax.swing.*;
import java.awt.*;

public class AuthActivity extends JFrame implements OnRegistrationCompleteListener, OnLoginEventListener {

    private JLabel centerFrame;
    private JPanel fragmentPanel;


    private AuthActivity() {

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

    @TestOnly
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
        System.out.println("Registration complete");
    }

    @Override
    public void returnToLogin() {
        showLoginPanel();
    }

    @Override
    public void onLoginSuccessful(User user) {
        System.out.println("Login successful for user : " + user.getUserName());
    }

    @Override
    public void onRegisterPressed() {
        showRegisterPanel();
    }
}
