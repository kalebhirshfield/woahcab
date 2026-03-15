package com.kalebhirshfield.woahcab.windows;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.kalebhirshfield.woahcab.components.HorizontalLabelPanel;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HistoryWindow extends JFrame {
    public HistoryWindow() {
        setTitle("Past Words");
        setSize(200, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200, 200));

        try {
            JsonArray words = SupabaseClient.select("user_word_progress?user_id=eq."+ SupabaseAuth.getUserId()+"&completed=eq.TRUE", SupabaseAuth.getAccessToken());
            for (JsonElement word : words) {
                String wordId = word.getAsJsonObject().get("word_id").getAsString();
                String result = word.getAsJsonObject().get("completed").getAsString();
                JsonArray wordData = SupabaseClient.select("words?word_id=eq."+wordId, SupabaseAuth.getAccessToken());
                String wordText = wordData.get(0).getAsJsonObject().get("word").getAsString();

                JLabel label = new JLabel(wordText);
                label.setHorizontalAlignment(SwingConstants.LEFT);

                if (result.equals("true")) {
                    result = "Correct";
                } else {
                    result = "Failed";
                }

                JLabel resultLabel = new JLabel(result);
                resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);

                HorizontalLabelPanel labelPanel = new HorizontalLabelPanel(new JLabel[]{label, resultLabel});
                add(labelPanel);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
