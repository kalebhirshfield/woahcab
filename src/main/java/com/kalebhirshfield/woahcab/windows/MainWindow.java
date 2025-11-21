package com.kalebhirshfield.woahcab.windows;

import com.kalebhirshfield.woahcab.components.MainTabs;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.WindowManager;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Woahcab");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(_ -> {
            SupabaseAuth.signOut();
            WindowManager.createSignInWindow();
        });

        JPanel signOutButtonPanel = new JPanel();
        signOutButtonPanel.setLayout(new BoxLayout(signOutButtonPanel, BoxLayout.X_AXIS));
        signOutButtonPanel.add(signOutButton);

        add(new MainTabs());
        add(signOutButtonPanel);

        pack();
    }
}
