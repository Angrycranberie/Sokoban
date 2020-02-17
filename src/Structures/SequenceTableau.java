package Structures;

class SequenceTableau implements Sequence {
    int[] elements;
    int taille, debut;

    public SequenceTableau() {
        // Taille par défaut
        elements = new int[1];
        debut = 0;
        taille = 0;
    }

    private void redimensionne(int nouvelleCapacite) {
        int[] nouveau;

        if (nouvelleCapacite > elements.length) {
            nouveau = new int[nouvelleCapacite];
            int aCopier = taille;
            for (int i = 0; i < aCopier; i++)
                nouveau[i] = extraitTete();
            debut = 0;
            taille = aCopier;
            elements = nouveau;
        }
    }

    @Override
    public void insereTete(int element) {
        if (taille == elements.length)
            redimensionne(taille * 2);
        debut = debut - 1;
        if (debut < 0)
            debut = elements.length - 1;
        elements[debut] = element;
        taille++;
    }

    public void insereQueue(int element) {
        if (taille == elements.length)
            redimensionne(taille * 2);
        elements[(debut + taille) % elements.length] = element;
        taille++;
    }

    public int extraitTete() {
        // Resultat invalide si la sequence est vide
        int resultat = elements[debut];
        debut = (debut + 1) % elements.length;
        taille--;
        return resultat;
    }

    public boolean estVide() {
        return taille == 0;
    }

    @Override
    public String toString() {
        String resultat = "SequenceTableau [ ";
        int pos = debut;
        for (int i = 0; i < taille; i++) {
            if (i > 0)
                resultat += ", ";
            resultat += elements[pos];
            pos = (pos + 1) % elements.length;
        }
        resultat += "]";
        return resultat;
    }
}

