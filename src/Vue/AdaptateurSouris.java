package Vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdaptateurSouris extends MouseAdapter {
    NiveauGraphique n;
    CollecteurEvenements control;

    AdaptateurSouris(NiveauGraphique niv, CollecteurEvenements c) {
        n = niv;
        control = c;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int l = e.getY() / n.hauteurCase();
        int c = e.getX() / n.largeurCase();
        control.clicSouris(l, c);
    }
}

