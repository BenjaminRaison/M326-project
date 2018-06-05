package net.baumink.bzz.m326;

import net.baumink.bzz.m326.view.MainWindow;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });

        new MainWindow();
    }
}
