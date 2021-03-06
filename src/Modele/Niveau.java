package Modele;

public class Niveau {
	static final int VIDE = 0;
	static final int MUR = 1;
	static final int POUSSEUR = 2;
	static final int CAISSE = 4;
	static final int BUT = 8;
	int l, c;
	int[][] cases;
	String nom;
	int pousseurL, pousseurC;
	int nbButs;
	int nbCaissesSurBut;

	Niveau() {
		cases = new int[1][1];
		l = c = 1;
		pousseurL = pousseurC = -1;
	}

	int ajuste(int cap, int objectif) {
		while (cap <= objectif) {
			cap = cap * 2;
		}
		return cap;
	}

	void redimensionne(int nouvL, int nouvC) {
		int capL = ajuste(cases.length, nouvL);
		int capC = ajuste(cases[0].length, nouvC);
		if ((capL > cases.length) || (capC > cases[0].length)) {
			int[][] nouvelles = new int[capL][capC];
			for (int i = 0; i < cases.length; i++)
				for (int j = 0; j < cases[0].length; j++)
					nouvelles[i][j] = cases[i][j];
			cases = nouvelles;
		}
		if (nouvL >= l)
			l = nouvL + 1;
		if (nouvC >= c)
			c = nouvC + 1;
	}

	void fixeNom(String s) {
		nom = s;
	}

	void videCase(int i, int j) {
		redimensionne(i, j);
		cases[i][j] = VIDE;
	}

	void supprime(int contenu, int i, int j) {
		if (aBut(i, j)) {
			if (aCaisse(i, j) && ((contenu & CAISSE | contenu & BUT) != 0))
				nbCaissesSurBut--;
			if ((contenu & BUT) != 0)
				nbButs--;
		}
		if (aPousseur(i, j) && ((contenu & POUSSEUR) != 0))
			pousseurL = pousseurC = -1;
		cases[i][j] &= ~contenu;
	}

	void ajoute(int contenu, int i, int j) {
		redimensionne(i, j);
		int resultat = cases[i][j] | contenu;
		if ((resultat & BUT) != 0) {
			if (((resultat & CAISSE) != 0) && (!aCaisse(i, j) || !aBut(i, j)))
				nbCaissesSurBut++;
			if (!aBut(i, j))
				nbButs++;
		}
		if (((resultat & POUSSEUR) != 0) && !aPousseur(i, j)) {
			if (pousseurL != -1)
				throw new IllegalStateException("Plusieurs pousseurs sur le terrain !");
			pousseurL = i;
			pousseurC = j;
		}
		cases[i][j] = resultat;
	}

	boolean deplace(int dLig, int dCol) {
		int destL = pousseurL + dLig;
		int destC = pousseurC + dCol;

		if (aCaisse(destL, destC)) {
			int dCaisL = destL + dLig;
			int dCaisC = destC + dCol;

			if (!aMur(dCaisL, dCaisC) && !aCaisse(dCaisL, dCaisC)) {
				supprime(CAISSE, destL, destC);
				ajoute(CAISSE, dCaisL, dCaisC);
			} else {
				return false;
			}
		}
		if (!aMur(destL, destC)) {
			supprime(POUSSEUR, pousseurL, pousseurC);
			ajoute(POUSSEUR, destL, destC);
			return true;
		}
		return false;
	}

	boolean deplaceVers(int l, int c) {
		int dL = l - pousseurL;
		int dC = c - pousseurC;
		int sum = dC + dL;
		sum = sum * sum;
		if ((dC * dL == 0) && (sum == 1))
			return deplace(dL, dC);
		else
			return false;
	}
	void ajouteMur(int i, int j) {
		ajoute(MUR, i, j);
	}

	void ajoutePousseur(int i, int j) {
		ajoute(POUSSEUR, i, j);
	}

	void ajouteCaisse(int i, int j) {
		ajoute(CAISSE, i, j);
	}

	void ajouteBut(int i, int j) {
		ajoute(BUT, i, j);
	}

	public int lignes() {
		return l;
	}

	public int colonnes() {
		return c;
	}

	String nom() {
		return nom;
	}

	boolean estVide(int l, int c) {
		return cases[l][c] == VIDE;
	}

	public boolean aMur(int l, int c) {
		return (cases[l][c] & MUR) != 0;
	}

	public boolean aBut(int l, int c) {
		return (cases[l][c] & BUT) != 0;
	}

	public boolean aPousseur(int l, int c) {
		return (cases[l][c] & POUSSEUR) != 0;
	}

	public boolean aCaisse(int l, int c) {
		return (cases[l][c] & CAISSE) != 0;
	}

	public boolean estTermine() {
		return nbCaissesSurBut == nbButs;
	}
}
