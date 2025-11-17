package com.kalebhirshfield.woahcab.components;

import com.kalebhirshfield.woahcab.utils.WindowManager;

import javax.swing.*;

public class AttemptPanel extends JPanel {
    public AttemptPanel(String user, String word, String wordId, Runnable onClick) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel(user));
        JButton button = new JButton("Attempt");
        button.addActionListener(_ -> WindowManager.createAttemptWindow(user, word, wordId, onClick));
        add(button);
    }
}
