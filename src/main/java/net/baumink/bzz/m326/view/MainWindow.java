package net.baumink.bzz.m326.view;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

    private JPanel contentPane;

    public MainWindow() {
        super();
        setTitle("M236");
        setLayout(new FlowLayout());
        setSize(new Dimension(800, 500));
        add(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void switchPanel(Views viewEnum) {
        this.contentPane.removeAll();
        switch (viewEnum) {
            case Lager:
                //this.contentPane.add(...);
                break;
            case Vertrieb:
                //this.contentPane.add(...);
                break;

            case Lieferant:
                //this.contentPane.add(...);
                break;

        }
        this.revalidate();
        this.repaint();
    }
}
