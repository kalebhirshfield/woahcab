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
        }

        this.letters.sort(Character::compareTo);

        for (char c : this.letters) {
            JTextField field = new JTextField(1);
            field.setHorizontalAlignment(JTextField.CENTER);
            field.setPreferredSize(new Dimension(32, 32));
            field.setText(String.valueOf(c));
            field.setBackground(Color.LIGHT_GRAY);
            field.setEditable(false);
            field.setFocusable(false);
            add(field);
        }

        revalidate();
        repaint();
    }
}
