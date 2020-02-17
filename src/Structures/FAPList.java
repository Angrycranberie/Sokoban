package Structures;

public class FAPList<E extends Comparable<E>> extends FAP<E> {
    SequenceList<E> s;

    public FAPList() {
        s = new SequenceList<>();
        super.s = s;
    }

    @Override
    public void insere(E element) {
        Maillon<E> precedent, courant;

        precedent = null;
        courant = s.tete;
        while ((courant != null) && (element.compareTo(courant.element) > 0)) {
            precedent = courant;
            courant = courant.suivant;
        }

        Maillon<E> m = new Maillon<>(element, courant);
        if (precedent == null) {
            s.tete = m;
        } else {
            precedent.suivant = m;
        }
        if (courant == null)
            s.queue = m;
    }
}

