package com.kalebhirshfield.woahcab.components;

import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DeletePanel extends JPanel {
    public DeletePanel(String filter, String word, Runnable refresh) {
        setLayout(new BorderLayout(8, 0));

        JLabel wordLabel = new JLabel(word);
        wordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        wordLabel.setPreferredSize(new Dimension(90, wordLabel.getPreferredSize().height));

        JButton button = new JButton("Delete");
        button.addActionListener(_ -> {
            try {
                SupabaseClient.delete("words", "word_id=eq."+filter, SupabaseAuth.getAccessToken());
                refresh.run();
                JOptionPane.showMessageDialog(this, "Word deleted successfully");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to delete word: " + ex.getMessage());
            }
        });
        button.setPreferredSize(new Dimension(100, button.getPreferredSize().height));

        add(wordLabel, BorderLayout.WEST);
        add(button, BorderLayout.EAST);
    }
}
