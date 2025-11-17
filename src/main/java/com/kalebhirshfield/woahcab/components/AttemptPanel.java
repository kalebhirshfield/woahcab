package com.kalebhirshfield.woahcab.components;

import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.io.IOException;

public class AttemptPanel extends JPanel {
    public AttemptPanel(String user, Runnable onClick) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel(user));
        JButton button = new JButton("Attempt");
        button.addActionListener(_ -> {

        });
        add(button);
    }
}
