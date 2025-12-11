package com.kalebhirshfield.woahcab;

import com.kalebhirshfield.woahcab.utils.WindowManager;
import javax.swing.*;

public class Main {
    static void main() {
        SwingUtilities.invokeLater(WindowManager::new);
    }
}
