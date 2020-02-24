package Global;

import Structures.Sequence;
import Structures.SequenceList;
import Structures.SequenceTableau;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {
    static Configuration instance = null;
    Properties prop;
    Logger logger;

    public static Configuration instance() throws FileNotFoundException {
        if (instance == null)
            instance = new Configuration();
        return instance;
    }

    public static InputStream charge(String nom) {
        // La méthode de chargement suivante ne dépend pas du système de fichier et sera
        // donc utilisable pour un .jar
        // Attention, par contre, le fichier doit se trouver dans le CLASSPATH
        return ClassLoader.getSystemClassLoader().getResourceAsStream(nom);
    }

    static void chargerProprietes(Properties p, FileInputStream in, String nom) {

        try {
            p.load(in);
        } catch (IOException e) {
            System.err.println("Impossible de charger " + nom);
            System.err.println(e.toString());
            System.exit(1);
        }
    }

    protected Configuration() throws FileNotFoundException {
        // On charge les propriétés
        FileInputStream in = new FileInputStream("C:\\Users\\mathd\\Desktop\\Sokoban\\resources\\defaut.cfg");
        Properties defaut = new Properties();
        chargerProprietes(defaut, in, "defaut.cfg");
        // Il faut attendre le dernier moment pour utiliser le logger
        // car celui-ci s'initialise avec les propriétés
        String message = "Fichier de propriétés defaut.cfg chargé";
        String nom = System.getProperty("user.home") + "/.sokoban";
        try {
            in = new FileInputStream(nom);
            prop = new Properties(defaut);
            chargerProprietes(prop, in, nom);
            logger().info(message);
            logger().info("Fichier de propriétés " + nom + " chargé");
        } catch (FileNotFoundException e) {
            prop = defaut;
            logger().info(message);
        }
    }

    public <E> Sequence<E> nouvelleSequence() {
        Sequence<E> resultat;
        // On crée la fabrique de séquences
        String type = lis("Sequence");
        switch (type) {
            case "Liste":
                resultat = new SequenceList<>();
                break;
            case "Tableau":
                resultat = new SequenceTableau<>();
                break;
            default:
                throw new NoSuchElementException("Sequences de type " + type + " non supportées");
        }
        return resultat;
    }

    public String lis(String nom) {
        String value = prop.getProperty(nom);
        if (value != null) {
            return value;
        } else {
            throw new NoSuchElementException("Propriété " + nom + " manquante");
        }
    }

    public Logger logger() {
        if (logger == null) {
            System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s : %5$s%n");
            logger = Logger.getLogger("Sokoban.Logger");
            logger.setLevel(Level.parse(lis("LogLevel")));
        }
        return logger;
    }
}

