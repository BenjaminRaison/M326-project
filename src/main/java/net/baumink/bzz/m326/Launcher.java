package net.baumink.bzz.m326;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        });

        JFrame mainWindow = new MainWindow();
    }
}
