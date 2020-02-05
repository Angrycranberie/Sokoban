package APNEE;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class LecteurNiveaux {
	public LecteurNiveaux() {

	}

	public Niveau lisProchainNiveau(Scanner s) {
		if(!s.hasNextLine()) {
			return null;
		}
		int l = 0, c = 0;
		int cTemp = 0, i = 0;

		// read until the end of level
		s.useDelimiter(";");
		String chaineLvl = s.next();

		// read until matching level separation
		s.useDelimiter("\n\n");
		chaineLvl = chaineLvl.concat(s.next());
		if(s.hasNextLine()) s.nextLine();
		if(s.hasNextLine()) s.nextLine();

		// System.out.println(chaineLvl);
		Niveau lvl;

		while(chaineLvl.charAt(i) != ';') {
			if(chaineLvl.charAt(i) == '\n') {
				l++;
				if(c < cTemp) {
					c = cTemp;
				}
				cTemp = 0;
			} else {
				cTemp++;
			}
			i++;
		}

		// System.out.println("lignes : " + l);
		// System.out.println("colonnes : " + c);

		lvl = new Niveau(l, c);
		String[] comments = chaineLvl.split("; ");
		lvl.fixeNom(comments[comments.length - 1].replace("\r", "").replace("\n", ""));

		i = 0;
		for(int j = 0; j < l; j++) {
			for(int k = 0; k < c; k++) {
				if(chaineLvl.charAt(i) != '\n') {
					switch(chaineLvl.charAt(i)) {
						case '@':
							lvl.ajoutePousseur(j, k);
							break;
						case '#':
							lvl.ajouteMur(j, k);
							break;
						case '.':
							lvl.ajouteBut(j, k);
							break;
						case '$':
							lvl.ajouteCaisse(j, k);
							break;
						case ' ':
							lvl.videCase(j, k);
							break;
						default:
							break;
					}
				} else {
					if(k != 0) {
						for(; k < c; k++) {
							lvl.videCase(j, k);
						}
					} else {
						k--;
					}
				}
				i++;
			}
		}

		return lvl;
	}

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("C:\\Users\\mathd\\IdeaProjects\\src\\lvl.txt");
		Scanner s = new Scanner(f);

		LecteurNiveaux lec = new LecteurNiveaux();

		// read all levels from file
		List<Niveau> allLevels = new ArrayList<Niveau>();
		Niveau n = lec.lisProchainNiveau(s);
		while(n != null) {
			allLevels.add(n);
			n = lec.lisProchainNiveau(s);
		}
        RedacteurNiveaux redN = new RedacteurNiveaux();
		for(Niveau level : allLevels) {
			redN.ecritNiveau(level);
		}
	}
}
