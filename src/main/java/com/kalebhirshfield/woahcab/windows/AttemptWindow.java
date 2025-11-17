package com.kalebhirshfield.woahcab.windows;

import com.google.gson.JsonArray;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class AttemptWindow extends JFrame {
    private final String word;
    private final String wordId;
    private final Runnable onClick;
    private Integer attempts = 0;

    public AttemptWindow(String name, String word, String wordId, Runnable onClick) {
        setTitle("Attempting "+name+"'s word");
        setSize(200, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.word = word;
        this.wordId = wordId;
        this.onClick = onClick;

        createAttempt();
    }

    public void createAttempt() {
        JPanel attempt = new JPanel();
        attempt.setLayout(new FlowLayout(FlowLayout.CENTER));

        ArrayList<JTextField> letterFields = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            JTextField letterField = new JTextField(1);
            letterField.setHorizontalAlignment(JTextField.CENTER);
            letterField.setPreferredSize(new Dimension(32, 32));
            letterField.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    if(!letterField.getText().isEmpty()) {
                        evt.consume();
                    }
                }
            });
            letterFields.add(letterField);
            attempt.add(letterField);
        }

        JButton button = new JButton("Check");
        button.addActionListener(_ -> {
            StringBuilder guess = new StringBuilder();

            for (JTextField letterField : letterFields) {
                if (letterField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all letters");
                    return;
                }

                guess.append(letterField.getText().toLowerCase());
            }

            attempts++;

            if (guess.toString().equals(word)) {
                try {
                    JsonArray progress = SupabaseClient.select("user_word_progress?word_id=eq."+wordId, SupabaseAuth.getAccessToken());
                    JOptionPane.showMessageDialog(this, "Please fill in all letters");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                dispose();
                return;
            }

            if (attempts >= 6) {
                JOptionPane.showMessageDialog(this, "Maximum attempts reached! The word was: " + word);
                dispose();
                return;
            }

            remove(button);
            attempt.revalidate();
            attempt.repaint();
            onClick.run();
            createAttempt();
        });

        add(attempt);
        add(button);
    }
}
