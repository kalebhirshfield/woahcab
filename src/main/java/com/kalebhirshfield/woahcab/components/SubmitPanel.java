package com.kalebhirshfield.woahcab.components;

import com.google.gson.JsonObject;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SubmitPanel extends JPanel {
    public SubmitPanel(Runnable refresh) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField submitWord = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        submitWord.setMaximumSize(new Dimension(Integer.MAX_VALUE, submitWord.getPreferredSize().height));

        submitButton.addActionListener(_ -> {
            String word = submitWord.getText();
            JsonObject json = new JsonObject();
            json.addProperty("word", word);
            json.addProperty("user_id", SupabaseAuth.getUserId());
            try {
                SupabaseClient.insert("words", json, SupabaseAuth.getAccessToken());
                submitWord.setText("");
                JOptionPane.showMessageDialog(this, "Word submitted successfully");
                refresh.run();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to submit word: " + ex.getMessage());
            }
        });

        JPanel submitButtonPanel = new JPanel();
        submitButtonPanel.setLayout(new BoxLayout(submitButtonPanel, BoxLayout.X_AXIS));
        submitButtonPanel.add(submitButton);

        add(submitWord);
        add(submitButtonPanel);
    }
}
