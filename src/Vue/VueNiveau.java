package Vue;

import Global.Configuration;
import Modele.Jeu;
import Modele.Niveau;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueNiveau extends NiveauGraphique {
    ImageSokoban pousseur, mur, sol, caisse, but, caisseSurBut;
    Jeu j;
    int hauteurCase, largeurCase;

    VueNiveau(Jeu jeu) throws FileNotFoundException {
        j = jeu;
        j.ajouteObservateur(this);
        pousseur = lisImage("Pousseur");
        mur = lisImage("Mur");
        sol = lisImage("Sol");
        caisse = lisImage("Caisse");
        but = lisImage("But");
        caisseSurBut = lisImage("CaisseSurBut");
    }

    private ImageSokoban lisImage(String nom) throws FileNotFoundException {
        String ressource = Configuration.instance().lis(nom);
        Configuration.instance().logger().info("Lecture de l'image " + ressource + " comme " + nom);
        FileInputStream in = new FileInputStream(ressource);
        try {
            // Chargement d'une image utilisable dans Swing
            return super.lisImage(in);
        } catch (Exception e) {
            Configuration.instance().logger().severe("Impossible de charger l'image " + ressource);
            System.exit(1);
        }
        return null;
    }

    @Override
    int hauteurCase() {
        return hauteurCase;
    }

    @Override
    int largeurCase() {
        return largeurCase;
    }

    @Override
    void tracerNiveau() {
        Niveau n = j.niveau();

        largeurCase = largeur() / n.colonnes();
        hauteurCase = hauteur() / n.lignes();
        // On prend des cases carrées
        largeurCase = Math.min(largeurCase, hauteurCase);
        hauteurCase = largeurCase;

        // On efface tout
        for (int ligne = 0; ligne < n.lignes(); ligne++)
            for (int colonne = 0; colonne < n.colonnes(); colonne++) {
                int x = colonne * largeurCase;
                int y = ligne * hauteurCase;
                // Tracé du sol
                if (n.aBut(ligne, colonne))
                    tracer(but, x, y, largeurCase, hauteurCase);
                else
                    tracer(sol, x, y, largeurCase, hauteurCase);
                // Tracé des objets
                if (n.aMur(ligne, colonne))
                    tracer(mur, x, y, largeurCase, hauteurCase);
                else if (n.aPousseur(ligne, colonne))
                    tracer(pousseur, x, y, largeurCase, hauteurCase);
                else if (n.aCaisse(ligne, colonne))
                    if (n.aBut(ligne, colonne))
                        tracer(caisseSurBut, x, y, largeurCase, hauteurCase);
                    else
                        tracer(caisse, x, y, largeurCase, hauteurCase);
            }

    }
}

