package Main;

public class Jeu {
    Niveau n;
    LecteurNiveaux l;

    Jeu(LecteurNiveaux lect) {
        l = lect;
    }

    public Niveau niveau() {
        return n;
    }

    public boolean prochainNiveau() {
        Niveau nouveau = l.lisProchainNiveau();
        if (nouveau != null) {
            n = nouveau;
            return true;
        } else {
            return false;
        }
    }
}

