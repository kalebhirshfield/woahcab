package com.kalebhirshfield.woahcab.windows;

import com.kalebhirshfield.woahcab.components.HorizontalButtonPanel;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.WindowManager;

import javax.swing.*;

public class SignInWindow extends JFrame {
    public SignInWindow() {
        setTitle("Woahcab - Sign In");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton signInButton = new JButton("Sign In");
        JButton signUpButton = new JButton("Sign Up");

        signInButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            try {
                if (SupabaseAuth.signIn(email, password)) {
                    WindowManager.showMainWindow();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        signUpButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            try {
                if (SupabaseAuth.signUp(email, password)) {
                    WindowManager.showMainWindow();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        HorizontalButtonPanel buttonPanel = new HorizontalButtonPanel(new JButton[]{signInButton, signUpButton});

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(buttonPanel);
    }
}
