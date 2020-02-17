package APNEE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sokoban {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream in;
        // La méthode de chargement suivante ne dépend pas du système de fichier et sera
        // donc utilisable pour un .jar
        // Attention, par contre, le fichier doit se trouver dans le CLASSPATH
        in = new FileInputStream("Niveaux\\Original.txt");

        LecteurNiveaux l = new LecteurNiveaux(in);
        RedacteurNiveau r = new RedacteurNiveau(System.out);
        Niveau n = l.lisProchainNiveau();
        while (n != null) {
            System.out.println("Niveau lu :");
            r.ecrisNiveau(n);
            n = l.lisProchainNiveau();
        }
    }
}
