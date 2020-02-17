package Main;


import javax.swing.*;
import java.awt.*;

// L'interface runnable déclare une méthode run
public class InterfaceGraphique implements Runnable {
    Jeu j;
    JFrame frame;
    boolean maximized;

    InterfaceGraphique(Jeu jeu) {
        j = jeu;
        SwingUtilities.invokeLater(this);
    }

    public void toggleFullscreen() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        if (maximized) {
            device.setFullScreenWindow(null);
            maximized = false;
        } else {
            device.setFullScreenWindow(frame);
            maximized = true;
        }
    }

    public void run() {
        frame = new JFrame("Ma fenetre a moi");
        frame.add(new NiveauGraphique(j));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        //toggleFullscreen();
        frame.setVisible(true);
    }
}