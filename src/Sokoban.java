import Controleur.ControleurMediateur;
import Global.Configuration;
import Modele.Jeu;
import Modele.LecteurNiveaux;
import Vue.CollecteurEvenements;
import Vue.InterfaceGraphique;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sokoban {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream in;
        String fichier = Configuration.instance().lis("FichierNiveaux");
        in = new FileInputStream(fichier);

        // La méthode de chargement suivante ne dépend pas du système de fichier et sera
        // donc utilisable pour un .jar
        // Attention, par contre, le fichier doit se trouver dans le CLASSPATH
        //in = Configuration.charge(fichier);


        Configuration.instance().logger().info("Niveaux trouvés");

        LecteurNiveaux l = new LecteurNiveaux(in);
        Jeu j = new Jeu(l);
        CollecteurEvenements control = new ControleurMediateur(j);
        InterfaceGraphique.demarrer(j, control);
    }
}
