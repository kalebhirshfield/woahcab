package com.kalebhirshfield.woahcab.windows;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AttemptWindow extends JFrame {
    private final String word;
    private final String wordId;
    private final Runnable onClick;
    private JsonArray attempts = new JsonArray();

    public AttemptWindow(String name, String word, String wordId, Runnable onClick) {
        setTitle("Attempting "+name+"'s word");
        setSize(200, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);

        this.word = word;
        this.wordId = wordId;
        this.onClick = onClick;

        try {
            JsonArray progress = SupabaseClient.select("user_word_progress?word_id=eq."+wordId, SupabaseAuth.getAccessToken());
            if (!progress.isEmpty()) {
                this.attempts = progress.get(0).getAsJsonObject().get("attempts").getAsJsonArray();
            } else {
                JsonObject object = new JsonObject();
                object.addProperty("word_id", wordId);
                SupabaseClient.insert("user_word_progress", object, SupabaseAuth.getAccessToken());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (JsonElement attempt : attempts) {
            createAttempt(attempt.getAsString());
        }
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

        JPanel buttonPanel = new JPanel();

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

            try {
                attempts.add(new JsonPrimitive(guess.toString()));
                JsonObject update = new JsonObject();
                update.add("attempts", attempts);

                if (guess.toString().equals(word)) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You guessed the word: " + word);
                    update.addProperty("completed", true);
                    update.addProperty("correct", true);
                    SupabaseClient.update("user_word_progress", "word_id=eq."+wordId, update, SupabaseAuth.getAccessToken());
                    onClick.run();
                    dispose();
                    return;
                }

                if (attempts.size() >= 6) {
                    JOptionPane.showMessageDialog(this, "Maximum attempts reached! The word was: " + word);
                    update.addProperty("completed", true);
                    update.addProperty("correct", false);
                    SupabaseClient.update("user_word_progress", "word_id=eq."+wordId, update, SupabaseAuth.getAccessToken());
                    onClick.run();
                    dispose();
                    return;
                }

                SupabaseClient.update("user_word_progress", "word_id=eq."+wordId, update, SupabaseAuth.getAccessToken());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            remove(attempt);
            remove(buttonPanel);
            attempt.revalidate();
            attempt.repaint();
            onClick.run();
            createAttempt(guess.toString());
            createAttempt();
            revalidate();
            repaint();
        });

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(button);

        add(attempt);
        add(buttonPanel);

        pack();
    }

    public void createAttempt(String word) {
        JPanel attempt = new JPanel();
        attempt.setLayout(new FlowLayout(FlowLayout.CENTER));

        ArrayList<JTextField> letterFields = new ArrayList<>();

        HashMap<Character, Integer> letterCount = new HashMap<>();
        for (int i = 0; i < this.word.length(); i++) {
            Character key = this.word.charAt(i);
            letterCount.merge(key, 1, Integer::sum);
        }

        Color[] letterColour = new Color[word.length()];

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == this.word.charAt(i)) {
                letterColour[i] = Color.GREEN;
                letterCount.merge(word.charAt(i), -1, Integer::sum);
            }
        }

        for (int i = 0; i < word.length(); i++) {
            if (letterColour[i] == Color.GREEN) {
                continue;
            }
            int count = letterCount.getOrDefault(word.charAt(i), 0);
            if (count > 0) {
                letterColour[i] = Color.YELLOW;
                letterCount.merge(word.charAt(i), -1, Integer::sum);
            } else {
                letterColour[i] = Color.LIGHT_GRAY;
            }
        }

        for (int i = 0; i < word.length(); i++) {
            JTextField letterField = new JTextField(1);
            letterField.setHorizontalAlignment(JTextField.CENTER);
            letterField.setPreferredSize(new Dimension(32, 32));
            letterField.setText(String.valueOf(word.charAt(i)));
            letterField.setBackground(letterColour[i]);

            letterField.setEditable(false);
            letterField.setFocusable(false);
            letterFields.add(letterField);
            attempt.add(letterField);
        }

        attempt.revalidate();
        attempt.repaint();
        onClick.run();
        add(attempt);
        revalidate();
        repaint();

        pack();
    }
}
