package com.kalebhirshfield.woahcab.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.io.IOException;

public class BrowsePanel extends JPanel {
    public BrowsePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        refresh();
    }

    public void refresh() {
        removeAll();
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(_ -> refresh());
        add(refresh);

        try {
            JsonArray words = SupabaseClient.select("words?user_id=not.eq."+SupabaseAuth.getUserId(), SupabaseAuth.getAccessToken());
            for (JsonElement jsonWord : words) {
                String userId = jsonWord.getAsJsonObject().get("user_id").getAsString();
                JsonArray profile = SupabaseClient.select("profiles?user_id=eq."+userId, SupabaseAuth.getAccessToken());
                JsonElement profileJson = profile.get(0);
                String name = profileJson.getAsJsonObject().get("name").getAsString();
                add(new AttemptPanel(name, this::refresh));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        revalidate();
        repaint();
    }
}
