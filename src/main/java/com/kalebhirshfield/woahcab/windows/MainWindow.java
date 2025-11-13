package com.kalebhirshfield.woahcab.windows;

import com.kalebhirshfield.woahcab.components.ResponsePanel;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {

        setTitle("Woahcab");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        ResponsePanel responsePanel = new ResponsePanel();

        add(responsePanel);

    }
}
