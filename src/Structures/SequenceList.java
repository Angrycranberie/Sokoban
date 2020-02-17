package Structures;

class SequenceList implements Sequence {
    Maillon tete, queue;

    // Les méthodes implémentant l'interface
    // doivent être publiques
    @Override
    public void insereTete(int element) {
        Maillon m = new Maillon(element, tete);
        if (queue == null)
            queue = m;
        tete = m;
    }

    public void insereQueue(int element) {
        Maillon m = new Maillon(element, null);
        if (queue == null) {
            tete = queue = m;
        } else {
            queue.suivant = m;
            queue = m;
        }
    }

    public int extraitTete() {
        int resultat;
        // Exception si tete == null (sequence vide)
        resultat = tete.element;
        tete = tete.suivant;
        if (tete == null) {
            queue = null;
        }
        return resultat;
    }

    public boolean estVide() {
        return tete == null;
    }

    @Override
    public String toString() {
        String resultat = "SequenceListe [ ";
        boolean premier = true;
        Maillon m = tete;
        while (m != null) {
            if (!premier)
                resultat += ", ";
            resultat += m.element;
            m = m.suivant;
            premier = false;
        }
        resultat += "]";
        return resultat;
    }
}
