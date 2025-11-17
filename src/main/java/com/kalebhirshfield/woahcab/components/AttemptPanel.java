package com.kalebhirshfield.woahcab.components;

import javax.swing.*;

public class AttemptPanel extends JPanel {
    public AttemptPanel(String user, Runnable onClick) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel(user));
        JButton button = new JButton("Attempt");
        button.addActionListener(_ -> {
            onClick.run();
        });
        add(button);
    }
}
