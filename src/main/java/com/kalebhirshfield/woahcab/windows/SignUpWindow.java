package com.kalebhirshfield.woahcab.windows;

import com.kalebhirshfield.woahcab.components.HorizontalButtonPanel;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.WindowManager;

import javax.swing.*;

public class SignUpWindow extends JFrame {
    public SignUpWindow() {
        setTitle("Woahcab - Sign Up");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTextField usernameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton signUpButton = new JButton("Sign Up");
        JButton signInButton = new JButton("Sign In Instead");

        signInButton.addActionListener(_ -> WindowManager.createSignInWindow());

        signUpButton.addActionListener(_ -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            try {
                if (SupabaseAuth.signUp(username, email, password)) {
                    WindowManager.createMainWindow();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        HorizontalButtonPanel buttonPanel = new HorizontalButtonPanel(new JButton[]{signUpButton, signInButton});

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(buttonPanel);
    }
}
