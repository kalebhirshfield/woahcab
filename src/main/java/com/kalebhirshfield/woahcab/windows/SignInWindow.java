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
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");

        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton signInButton = new JButton("Sign In");
        JButton signUpButton = new JButton("Sign Up Instead");

        signInButton.addActionListener(_ -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            try {
                if (SupabaseAuth.signIn(email, password)) {
                    WindowManager.createMainWindow();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        signUpButton.addActionListener(_ -> WindowManager.createSignUpWindow());

        JPanel emailLabelPanel = new JPanel();
        emailLabelPanel.setLayout(new BoxLayout(emailLabelPanel, BoxLayout.X_AXIS));
        emailLabelPanel.add(emailLabel);

        JPanel passwordLabelPanel = new JPanel();
        passwordLabelPanel.setLayout(new BoxLayout(passwordLabelPanel, BoxLayout.X_AXIS));
        passwordLabelPanel.add(passwordLabel);

        HorizontalButtonPanel buttonPanel = new HorizontalButtonPanel(new JButton[]{signInButton, signUpButton});

        add(emailLabelPanel);
        add(emailField);
        add(passwordLabelPanel);
        add(passwordField);
        add(buttonPanel);

        pack();
    }
}
