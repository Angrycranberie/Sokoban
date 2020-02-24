package Modele;

import java.io.InputStream;
import java.util.Scanner;

public class LecteurNiveaux {
	Scanner s;
	Niveau n;

	public LecteurNiveaux(InputStream in) {
		s = new Scanner(in);
	}

	String lisLigne() {
		if (s.hasNextLine()) {
			String ligne;
			ligne = s.nextLine();
			// Nettoyage des séparateurs de fin et commentaires
			int i;
			int dernier = -1;
			boolean commentaire = false;
			for (i = 0; (i < ligne.length()) && !commentaire; i++) {
				char c = ligne.charAt(i);
				if (!Character.isWhitespace(c) && (c != ';')) {
					dernier = i;
				}
				if (c == ';') {
					commentaire = true;
				}
			}
			// Un commentaire non vide sera pris comme nom de niveau
			// -> le dernier commentaire non vide sera le nom final
			if (commentaire) {
				char c = ' ';
				while (Character.isWhitespace(c) && (i < ligne.length())) {
					c = ligne.charAt(i);
					if (!Character.isWhitespace(c))
						n.fixeNom(ligne.substring(i));
					i++;
				}
			}
			return ligne.substring(0, dernier + 1);
		} else {
			return null;
		}
	}

	Niveau lisProchainNiveau() {
		n = new Niveau();
		String ligne = "";
		while (ligne.length() == 0) {
			ligne = lisLigne();
			if (ligne == null)
				return null;
		}
		int i = 0;
		while ((ligne != null) && (ligne.length() > 0)) {
			for (int j = 0; j < ligne.length(); j++) {
				char c = ligne.charAt(j);
				n.videCase(i, j);
				switch (c) {
					case ' ':
						break;
					case '#':
						n.ajouteMur(i, j);
						break;
					case '@':
						n.ajoutePousseur(i, j);
						break;
					case '+':
						n.ajoutePousseur(i, j);
						n.ajouteBut(i, j);
						break;
					case '$':
						n.ajouteCaisse(i, j);
						break;
					case '*':
						n.ajouteCaisse(i, j);
						n.ajouteBut(i, j);
						break;
					case '.':
						n.ajouteBut(i, j);
						break;
					default:
						System.err.println("Caractère inconnu : " + c);
				}
			}
			ligne = lisLigne();
			i++;
		}
		if (i > 0)
			return n;
		else
			return null;
	}
}
