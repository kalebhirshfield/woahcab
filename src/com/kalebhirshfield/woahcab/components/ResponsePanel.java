package com.kalebhirshfield.woahcab.components;

import javax.swing.*;

public class ResponsePanel extends JPanel {

    public ResponsePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Yesterday's Word Response"));

        JLabel word = new JLabel("Word");
        JTextArea responseArea = new JTextArea("Come back later to review the response!");
        responseArea.setEditable(false);
        JButton correctButton = new JButton("Correct");
        JButton incorrectButton = new JButton("Incorrect");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(correctButton);
        buttonPanel.add(incorrectButton);

        add(word);
        add(responseArea);
        add(buttonPanel);
    }

}
