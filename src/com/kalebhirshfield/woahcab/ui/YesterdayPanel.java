package com.kalebhirshfield.woahcab.ui;

import javax.swing.*;

public class YesterdayPanel extends JPanel {
    private JLabel responseLabel;
    private JButton correctButton;
    private JButton incorrectButton;

    public YesterdayPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Yesterday's Word Response"));

        responseLabel = new JLabel("Was your word guessed correctly?");
        responseLabel.setAlignmentX(LEFT_ALIGNMENT);
        correctButton = new JButton("Correct");
        correctButton.setAlignmentX(LEFT_ALIGNMENT);
        incorrectButton = new JButton("Incorrect");
        incorrectButton.setAlignmentX(RIGHT_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(correctButton);
        buttonPanel.add(incorrectButton);

        add(responseLabel);
        add(buttonPanel);
    }

}
