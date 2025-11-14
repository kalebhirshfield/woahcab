package com.kalebhirshfield.woahcab.components;

import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.io.IOException;

public class DeleteWordPanel extends JPanel {
    public DeleteWordPanel(String filter, String word, Runnable refresh) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel(word));
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
        add(button);
    }
}
