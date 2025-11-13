package com.kalebhirshfield.woahcab.components;

import com.kalebhirshfield.woahcab.utils.SupabaseAuth;

import javax.swing.*;

public class FindWordPanel extends JPanel {
    public FindWordPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String accessToken = SupabaseAuth.getAccessToken();
        add(new JLabel("Find a Word"));
    }
}
