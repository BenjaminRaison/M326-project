package net.baumink.bzz.m326;

import net.baumink.bzz.m326.view.MainWindow;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getCanonicalName();
            }
            JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
        });

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.err.println("Failed to set look and theme!" + e.getMessage());
        }

        new MainWindow();
    }
}
