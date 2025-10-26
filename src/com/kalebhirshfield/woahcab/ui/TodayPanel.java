package com.kalebhirshfield.woahcab.ui;

import javax.swing.*;

public class TodayPanel extends JPanel {
    private JTextField wordInput;
    private JButton submitButton;

    public TodayPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Today's Word"));

        wordInput = new JTextField(1);
        wordInput.setAlignmentX(LEFT_ALIGNMENT);
        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(LEFT_ALIGNMENT);

        add(wordInput);
        add(submitButton);
    }
}
