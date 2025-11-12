package com.kalebhirshfield.woahcab.utils;

import com.kalebhirshfield.woahcab.windows.*;
import javax.swing.*;

public class WindowManager {
    private static final SignInWindow signInWindow;
    private static final MainWindow mainWindow;

    static {
        signInWindow = new SignInWindow();
        mainWindow = new MainWindow();
        showSignInWindow();
    }

    public static void showSignInWindow() {
        signInWindow.setVisible(true);
        mainWindow.setVisible(false);
    }

    public static void showMainWindow() {
        signInWindow.setVisible(false);
        mainWindow.setVisible(true);
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }
}
