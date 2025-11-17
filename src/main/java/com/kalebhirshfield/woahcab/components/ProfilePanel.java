package com.kalebhirshfield.woahcab.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.io.IOException;

public class ProfilePanel extends JPanel {
    public ProfilePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        refresh();
    }

    public void refresh() {
        removeAll();

        try {
            JsonArray words = SupabaseClient.select("words", SupabaseAuth.getAccessToken());
            for (JsonElement jsonWord : words) {
                if (jsonWord.getAsJsonObject().get("user_id").getAsString().equals(SupabaseAuth.getUserId())) {
                    String filter = jsonWord.getAsJsonObject().get("word_id").getAsString();
                    String word = jsonWord.getAsJsonObject().get("word").getAsString();
                    add(new DeletePanel(filter, word, this::refresh));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        revalidate();
        repaint();
    }
}
