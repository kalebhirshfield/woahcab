package com.kalebhirshfield.woahcab.components;

import com.google.gson.JsonArray;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;
import com.kalebhirshfield.woahcab.utils.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class AttemptPanel extends JPanel {
    public AttemptPanel(String user, String word, String wordId, Runnable onClick) {
        setLayout(new BorderLayout(8, 0));

        try {
            JsonArray data = SupabaseClient.select("words?word_id=eq."+wordId, SupabaseAuth.getAccessToken());
            String time = data.get(0).getAsJsonObject().get("created_at").getAsString();

            OffsetDateTime odt = OffsetDateTime.parse(time);
            String displayTime = odt.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"));

            JLabel userLabel = new JLabel(user);
            userLabel.setHorizontalAlignment(SwingConstants.LEFT);
            userLabel.setPreferredSize(new Dimension(90, userLabel.getPreferredSize().height));

            JLabel timeLabel = new JLabel(displayTime);
            timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JButton button = new JButton("Attempt");
            button.addActionListener(_ -> WindowManager.createAttemptWindow(user, word, wordId, onClick));
            button.setPreferredSize(new Dimension(100, button.getPreferredSize().height));

            add(userLabel, BorderLayout.WEST);
            add(timeLabel, BorderLayout.CENTER);
            add(button, BorderLayout.EAST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
