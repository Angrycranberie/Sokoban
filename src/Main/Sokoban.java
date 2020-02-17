package Main;

import Global.Configuration;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Sokoban {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream in;
        String fichier = Configuration.instance().lis("C:\\Users\\mathd\\Desktop\\Sokoban\\resources\\Niveaux\\Original.txt");
        in = Configuration.charge(fichier);

        // La méthode de chargement suivante ne dépend pas du système de fichier et sera
        // donc utilisable pour un .jar
        // Attention, par contre, le fichier doit se trouver dans le CLASSPATH
        in = Configuration.charge(fichier);
        if (in == null) {
            System.err.println("ERREUR : impossible de trouver le fichier de niveaux nommé " + fichier);
            System.exit(1);
        }


        Configuration.instance().logger().info("Niveaux trouvés");

        LecteurNiveaux l = new LecteurNiveaux(in);
        Jeu j = new Jeu(l);
        int num = 1;
        if (args.length > 0)
            num = Integer.parseInt(args[0]);
        Configuration.instance().logger().info("Affichage du Niveau " + num);
        while (num != 0) {
            if (!j.prochainNiveau()) {
                Configuration.instance().logger().info("Pas assez de niveaux dans le fichier de niveaux");
                System.exit(2);
            }
            num--;
        }
        new InterfaceGraphique(j);
    }
}
