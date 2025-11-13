package com.kalebhirshfield.woahcab.components;

import com.google.gson.JsonObject;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.io.IOException;

public class SubmitWordPanel extends JPanel {
    public SubmitWordPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField submitWord = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            String word = submitWord.getText();
            JsonObject json = new JsonObject();
            json.addProperty("word", word);
            json.addProperty("user_id", SupabaseAuth.getUserId());
            try {
                SupabaseClient.insert("words", json, SupabaseAuth.getAccessToken());
                JOptionPane.showMessageDialog(this, "Word successfully submitted");
                submitWord.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to submit word: " + ex.getMessage());
            }
        });

        add(new JLabel("Submit a Word"));
        add(submitWord);
        add(submitButton);
    }
}
