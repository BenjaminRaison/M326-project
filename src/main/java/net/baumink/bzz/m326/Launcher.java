package net.baumink.bzz.m326;

import javax.swing.*;

/**
 * The type Launcher.
 *  @author Benjamin Raison, Jonas Gredig
 *  @version 1.0
 */
public class Launcher {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        });
    }
}
