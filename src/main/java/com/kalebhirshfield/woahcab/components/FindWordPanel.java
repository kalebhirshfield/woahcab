package com.kalebhirshfield.woahcab.components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.kalebhirshfield.woahcab.utils.SupabaseAuth;
import com.kalebhirshfield.woahcab.utils.SupabaseClient;

import javax.swing.*;
import java.io.IOException;

public class FindWordPanel extends JPanel {
    public FindWordPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        refresh();
    }

    public void refresh() {
        removeAll();
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(_ -> refresh());



        revalidate();
        repaint();
    }
}
