package net.baumink.bzz.m326;

import net.baumink.bzz.m326.ui.view.MainWindow;

import javax.swing.*;

/**
 *
 * @author Benjamin Raison, Jonas Gredig
 * @version 1.0
 */
public class Launcher {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        setupErrorHandling();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            System.err.println("Failed to set look and feel!" + e.getMessage());
        }
        new MainWindow();
    }

    private static void setupErrorHandling() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getCanonicalName();
            }
            JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        });
    }
}
