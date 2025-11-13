package com.kalebhirshfield.woahcab.components;

import javax.swing.*;

public class HorizontalButtonPanel extends JPanel {
    public HorizontalButtonPanel(JButton[] buttons) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        for (JButton button : buttons) {
            add(button);
        }
    }
}
