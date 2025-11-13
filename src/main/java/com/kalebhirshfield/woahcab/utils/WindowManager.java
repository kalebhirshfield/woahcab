package com.kalebhirshfield.woahcab.utils;

import com.kalebhirshfield.woahcab.windows.SignInWindow;
import com.kalebhirshfield.woahcab.windows.MainWindow;


public class WindowManager {
    private static SignInWindow signInWindow;
    private static MainWindow mainWindow;

    static {
        createSignInWindow();
    }

    public static void createMainWindow() {
        mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        signInWindow.dispose();
    }

    public static void createSignInWindow() {
        signInWindow = new SignInWindow();
        signInWindow.setVisible(true);
        if (mainWindow != null) {
            mainWindow.dispose();
            mainWindow = null;
        }
    }
}
