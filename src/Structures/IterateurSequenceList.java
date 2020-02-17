package Structures;

import java.util.NoSuchElementException;

class IterateurSequenceList implements Iterateur {

    SequenceList e;
    Maillon pprec, prec, courant;
    boolean last;

    IterateurSequenceList(SequenceList e) {
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
    public int prochain() {
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

