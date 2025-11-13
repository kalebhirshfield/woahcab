package com.kalebhirshfield.woahcab.components;

import javax.swing.*;

public class WordTabs extends JTabbedPane {
    public WordTabs() {
        setTabPlacement(JTabbedPane.TOP);
        YourWordsPanel yourWordsPanel = new YourWordsPanel();
        SubmitWordPanel submitWordPanel = new SubmitWordPanel(yourWordsPanel::refresh);

        addTab("Your Words", yourWordsPanel);
        addTab("Submit Word", submitWordPanel);
        addTab("Find Words", new FindWordPanel());
    }
}
