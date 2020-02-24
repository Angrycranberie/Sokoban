package Vue;


import Modele.Jeu;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

// L'interface runnable déclare une méthode run
public class InterfaceGraphique implements Runnable {
    Jeu j;
    CollecteurEvenements control;
    JFrame frame;
    boolean maximized;

    public InterfaceGraphique(Jeu jeu, CollecteurEvenements c) {
        j = jeu;
        control = c;
    }

    public static void demarrer(Jeu j, CollecteurEvenements c) {
        InterfaceGraphique vue = new InterfaceGraphique(j, c);
        c.ajouteInterfaceGraphique(vue);
        SwingUtilities.invokeLater(vue);
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
        NiveauGraphique niv = null;
        try {
            niv = new VueNiveau(j);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        niv.addMouseListener(new AdaptateurSouris(niv, control));
        frame.addKeyListener(new AdaptateurClavier(control));
        frame.add(niv);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}
