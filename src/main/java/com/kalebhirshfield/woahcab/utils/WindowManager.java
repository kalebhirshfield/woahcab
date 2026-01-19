package com.kalebhirshfield.woahcab.utils;

import com.kalebhirshfield.woahcab.windows.*;


public class WindowManager {
    private static SignInWindow signInWindow;
    private static SignUpWindow signUpWindow;
    private static MainWindow mainWindow;

    static {
        createSignInWindow();
    }

    public static void createSignInWindow() {
        signInWindow = new SignInWindow();
        signInWindow.setVisible(true);
        if (mainWindow != null) {
            mainWindow.dispose();
            mainWindow = null;
        }
        if (signUpWindow != null) {
            signUpWindow.dispose();
            signUpWindow = null;
        }
    }

    public static void createSignUpWindow() {
        signUpWindow = new SignUpWindow();
        signUpWindow.setVisible(true);
        signInWindow.dispose();
    }

    public static void createMainWindow() {
        mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        signInWindow.dispose();
        if (signUpWindow != null) {
            signUpWindow.dispose();
            signUpWindow = null;
        }
    }

    public static void createAttemptWindow(String name, String word, String wordId, Runnable onClick) {
        AttemptWindow attemptWindow = new AttemptWindow(name, word, wordId, onClick);
        attemptWindow.setVisible(true);
    }

    public static void createHistoryWindow() {
        RecentWindow recentWindow = new RecentWindow();
        recentWindow.setVisible(true);
    }
}
