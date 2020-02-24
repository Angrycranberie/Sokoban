package Structures;


import java.util.NoSuchElementException;

class IterateurSequenceTableau<T> implements Iterateur<T> {

    SequenceTableau<T> e;
    int position, rang, last;

    IterateurSequenceTableau(SequenceTableau<T> e) {
        this.e = e;
        rang = 0;
        position = e.debut;
        last = -1;
    }

    @Override
    public boolean aProchain() {
        return rang < e.taille;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T prochain() {
        if (aProchain()) {
            last = position;
            position = (position + 1) % e.elements.length;
            rang++;
            return (T) e.elements[last];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void supprime() {
        if (last != -1) {
            // On recule
            position = last;
            // On décale les éléments qui suivent
            int courant = rang;
            while (courant < e.taille) {
                int next = (last + 1) % e.elements.length;
                e.elements[last] = e.elements[next];
                last = next;
                courant++;
            }
            last = -1;
            rang--;
            e.taille--;
        } else {
            throw new IllegalStateException();
        }
    }
}
