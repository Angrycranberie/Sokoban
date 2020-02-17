package Structures;

public class SequenceList<E> implements Sequence<E> {
    Maillon<E> tete, queue;

    // Les méthodes implémentant l'interface
    // doivent être publiques
    @Override
    public void insereQueue(E element) {
        Maillon<E> m = new Maillon<>(element, null);
        if (queue == null) {
            tete = queue = m;
        } else {
            queue.suivant = m;
            queue = m;
        }
    }

    @Override
    public void insereTete(E element) {
        Maillon<E> m = new Maillon<>(element, tete);
        if (tete == null) {
            tete = queue = m;
        } else {
            tete = m;
        }
    }

    @Override
    public E extraitTete() {
        E resultat;
        // Exception si tete == null (sequence vide)
        resultat = tete.element;
        tete = tete.suivant;
        if (tete == null) {
            queue = null;
        }
        return resultat;
    }

    @Override
    public boolean estVide() {
        return tete == null;
    }

    @Override
    public String toString() {
        String resultat = "SequenceListe [ ";
        boolean premier = true;
        Maillon<E> m = tete;
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

    @Override
    public Iterateur<E> iterateur() {
        return new IterateurSequenceList<>(this);
    }
}

