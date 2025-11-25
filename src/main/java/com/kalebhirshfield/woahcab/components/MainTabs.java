package com.kalebhirshfield.woahcab.components;

import javax.swing.*;

public class MainTabs extends JTabbedPane {
    public MainTabs() {
        setTabPlacement(JTabbedPane.TOP);
        ProfilePanel profilePanel = new ProfilePanel();
        SubmitPanel submitPanel = new SubmitPanel(profilePanel::refresh);
        BrowsePanel browsePanel = new BrowsePanel();

        JScrollPane scrollPane = new JScrollPane(browsePanel);

        addTab("Profile", profilePanel);
        addTab("Submit", submitPanel);
        addTab("Browse", scrollPane);
    }
}
