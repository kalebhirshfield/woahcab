package com.kalebhirshfield.woahcab.components;

import javax.swing.*;

public class FindWordPanel extends JPanel {
    public FindWordPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        refresh();
    }

    public void refresh() {
        removeAll();
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(_ -> refresh());



        revalidate();
        repaint();
    }
}
