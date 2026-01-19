package com.kalebhirshfield.woahcab.windows;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.kalebhirshfield.woahcab.components.AttemptPanel;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RecentWindow extends JFrame {
    public RecentWindow() {
        setTitle("Recent Words");
        setSize(200, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(500, 200));

        try {
            JsonArray words = SupabaseClient.select("user_word_progress?user_id=eq."+SupabaseAuth.getUserId()+"&completed=eq.FALSE", SupabaseAuth.getAccessToken());
            for (JsonElement jsonWord : words) {
                String wordId = jsonWord.getAsJsonObject().get("word_id").getAsString();
                JsonArray wordData = SupabaseClient.select("words?word_id=eq."+wordId, SupabaseAuth.getAccessToken());
                String word = wordData.get(0).getAsJsonObject().get("word").getAsString();
                String userId = jsonWord.getAsJsonObject().get("user_id").getAsString();
                JsonArray profile = SupabaseClient.select("profiles?user_id=eq."+userId, SupabaseAuth.getAccessToken());
                String name = profile.get(0).getAsJsonObject().get("name").getAsString();
                add(new AttemptPanel(name, word, wordId, this::repaint));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
