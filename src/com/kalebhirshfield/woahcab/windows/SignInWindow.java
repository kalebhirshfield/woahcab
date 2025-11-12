package com.kalebhirshfield.woahcab.windows;

import javax.swing.*;

import com.kalebhirshfield.woahcab.utils.ConnectionManager;
import com.kalebhirshfield.woahcab.utils.WindowManager;

public class SignInWindow extends JFrame {
    public SignInWindow() {

        setTitle("Woahcab - Sign In");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            try {
                ConnectionManager.connect(password);
                WindowManager.showMainWindow();
                this.dispose();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signInButton);
    }
}
