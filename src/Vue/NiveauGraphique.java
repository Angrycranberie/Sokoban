package Vue;

import Patterns.Observateur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public abstract class NiveauGraphique extends JComponent implements Observateur {
    Graphics2D drawable;

    // La partie indépendante de Swing de la lecture des images se trouve dans le descendant
    protected ImageSokoban lisImage(InputStream in) throws IOException {
        return new ImageSokoban(ImageIO.read(in));
    }

    protected void tracer(ImageSokoban i, int x, int y, int l, int h) {
        drawable.drawImage(i.image(), x, y, l, h, null);
    }

    // tracerNiveau est la partie indépendante de Swing du dessin qui se trouve dans le descendant
    abstract void tracerNiveau();

    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;

        // On efface tout
        drawable.clearRect(0, 0, largeur(), hauteur());
        tracerNiveau();
    }

    int hauteur() {
        return getHeight();
    }

    int largeur() {
        return getWidth();
    }

    abstract int hauteurCase();

    abstract int largeurCase();

    @Override
    public void miseAJour() {
        repaint();
    }
}
