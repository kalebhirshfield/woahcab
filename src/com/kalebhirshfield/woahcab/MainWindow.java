package com.kalebhirshfield.woahcab;

import com.kalebhirshfield.woahcab.ui.TodayPanel;
import com.kalebhirshfield.woahcab.ui.YesterdayPanel;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Woahcab");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        YesterdayPanel yesterdayPanel = new YesterdayPanel();
        TodayPanel todayPanel = new TodayPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, todayPanel, yesterdayPanel);

        splitPane.setDividerLocation(getHeight()/2);

        splitPane.setContinuousLayout(true);

        add(splitPane);

    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}
