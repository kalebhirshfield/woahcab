package com.kalebhirshfield.woahcab.windows;

import com.google.gson.JsonObject;
import com.kalebhirshfield.woahcab.components.FindWordPanel;
import com.kalebhirshfield.woahcab.components.SubmitWordPanel;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {
    public MainWindow() {

        setTitle("Woahcab");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JTextField submitWord = new JTextField();
        JButton submitButton = new JButton("Submit");
        tabbedPane.addTab("Submit", new SubmitWordPanel());
        tabbedPane.addTab("Find", new FindWordPanel());

        add(tabbedPane);
    }
}
