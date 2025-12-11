package com.kalebhirshfield.woahcab;

import com.kalebhirshfield.woahcab.utils.WindowManager;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(WindowManager::new);
    }
}
