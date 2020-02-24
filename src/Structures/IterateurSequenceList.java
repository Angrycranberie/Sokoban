package Structures;

import java.util.NoSuchElementException;

class IterateurSequenceList<T> implements Iterateur<T> {

    SequenceList<T> e;
    Maillon<T> pprec, prec, courant;
    boolean last;

    IterateurSequenceList(SequenceList<T> e) {
        this.e = e;
        pprec = prec = null;
        courant = e.tete;
        last = false;
    }

    @Override
    public boolean aProchain() {
        return courant != null;
    }

    @Override
    public T prochain() {
        if (aProchain()) {
            pprec = prec;
            prec = courant;
            courant = courant.suivant;
            last = true;
            return prec.element;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void supprime() {
        if (last) {
            if (pprec == null) {
                e.tete = courant;
            } else {
                pprec.suivant = courant;
            }
            if (prec == e.queue) {
                e.queue = pprec;
            }
            prec = pprec;
            last = false;
        } else {
            throw new IllegalStateException();
        }
    }
}


