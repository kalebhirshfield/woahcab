package com.kalebhirshfield.woahcab.components;

import javax.swing.*;

public class HorizontalLabelPanel extends JPanel {
    public HorizontalLabelPanel(JLabel[] labels) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        for (JLabel label : labels) {
            add(label);
        }
    }
}
