package com.kalebhirshfield.woahcab.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LettersPanel extends JPanel {
    ArrayList<Character> letters;

    public LettersPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        letters = new ArrayList<>();
    }

    public void update(ArrayList<Character> letters) {
        removeAll();
        for (char c : letters) {
            if (!this.letters.contains(c)) {
                this.letters.add(c);
            }

            JLabel letterLabel = new JLabel(String.valueOf(c));
            letterLabel.setHorizontalAlignment(SwingConstants.CENTER);
            letterLabel.setOpaque(true);
            letterLabel.setBackground(Color.LIGHT_GRAY);
            add(letterLabel);
        }

        revalidate();
        repaint();
    }
}
