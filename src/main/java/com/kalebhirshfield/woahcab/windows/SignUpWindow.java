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
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel usernameLabel = new JLabel("Username");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");

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

        JPanel usernameLabelPanel = new JPanel();
        usernameLabelPanel.setLayout(new BoxLayout(usernameLabelPanel, BoxLayout.X_AXIS));
        usernameLabelPanel.add(usernameLabel);

        JPanel emailLabelPanel = new JPanel();
        emailLabelPanel.setLayout(new BoxLayout(emailLabelPanel, BoxLayout.X_AXIS));
        emailLabelPanel.add(emailLabel);

        JPanel passwordLabelPanel = new JPanel();
        passwordLabelPanel.setLayout(new BoxLayout(passwordLabelPanel, BoxLayout.X_AXIS));
        passwordLabelPanel.add(passwordLabel);

        HorizontalButtonPanel buttonPanel = new HorizontalButtonPanel(new JButton[]{signUpButton, signInButton});

        add(usernameLabelPanel);
        add(usernameField);
        add(emailLabelPanel);
        add(emailField);
        add(passwordLabelPanel);
        add(passwordField);
        add(buttonPanel);

        pack();
    }
}
