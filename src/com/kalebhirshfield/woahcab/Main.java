package com.kalebhirshfield.woahcab;

import com.kalebhirshfield.woahcab.utils.WindowManager;
import javax.swing.*;
import com.kalebhirshfield.woahcab.utils.ConnectionManager;

public class Main {
    static void main() {
        new ConnectionManager();
        SwingUtilities.invokeLater(WindowManager::new);
    }
}
