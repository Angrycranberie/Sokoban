package Modele;

import Patterns.Observable;

public class Jeu extends Observable {
    Niveau n;
    LecteurNiveaux l;

    public Jeu(LecteurNiveaux lect) {
        l = lect;
        prochainNiveau();
    }

    public Niveau niveau() {
        return n;
    }

    public boolean deplaceVers(int x, int y) {
        boolean resultat = n.deplaceVers(x, y);
        valideDeplacement(resultat);
        return resultat;
    }

    public boolean deplace(int x, int y) {
        boolean resultat = n.deplace(x, y);
        valideDeplacement(resultat);
        return resultat;
    }

    private void valideDeplacement(boolean b) {
        if (b) {
            if (n.estTermine())
                prochainNiveau();
            metAJour();
        }
    }

    public boolean prochainNiveau() {
        n = l.lisProchainNiveau();
        return false;
    }

    public boolean estTermine() {
        return n == null;
    }
}
