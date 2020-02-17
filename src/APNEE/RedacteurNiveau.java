package APNEE;

import java.io.OutputStream;
import java.io.PrintStream;

public class RedacteurNiveau {
    PrintStream sortie;

    RedacteurNiveau(OutputStream out) {
        sortie = new PrintStream(out);
    }

    void ecrisNiveau(Niveau n) {
        for (int i = 0; i < n.lignes(); i++) {
            int dernier = 0;
            for (int j = 0; j < n.colonnes(); j++)
                if (!n.estVide(i, j))
                    dernier = j;
            for (int j = 0; j <= dernier; j++)
                if (n.aMur(i, j))
                    sortie.print('#');
                else if (n.aBut(i, j))
                    if (n.aPousseur(i, j))
                        sortie.print('+');
                    else if (n.aCaisse(i, j))
                        sortie.print('*');
                    else
                        sortie.print('.');
                else if (n.aPousseur(i, j))
                    sortie.print('@');
                else if (n.aCaisse(i, j))
                    sortie.print('$');
                else
                    sortie.print(' ');
            sortie.println();
        }
        if (n.nom() != null)
            sortie.println("; " + n.nom());
    }
}
