package Main;

class Niveau {
	static final int VIDE = 0;
	static final int MUR = 1;
	static final int POUSSEUR = 2;
	static final int CAISSE = 4;
	static final int BUT = 8;
	int l, c;
	int[][] cases;
	String nom;

	Niveau() {
		cases = new int[1][1];
		l = c = 1;
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

	void ajoute(int contenu, int i, int j) {
		redimensionne(i, j);
		cases[i][j] |= contenu;
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

	int lignes() {
		return l;
	}

	int colonnes() {
		return c;
	}

	String nom() {
		return nom;
	}

	boolean estVide(int l, int c) {
		return cases[l][c] == VIDE;
	}

	boolean aMur(int l, int c) {
		return (cases[l][c] & MUR) != 0;
	}

	boolean aBut(int l, int c) {
		return (cases[l][c] & BUT) != 0;
	}

	boolean aPousseur(int l, int c) {
		return (cases[l][c] & POUSSEUR) != 0;
	}

	boolean aCaisse(int l, int c) {
		return (cases[l][c] & CAISSE) != 0;
	}
}
