package Structures;

import java.util.Iterator;

class Maillon<T> {
    private T e;
    private Maillon<T> suivant;

    public Maillon(T e, Maillon<T> s) {
        this.e = e;
        suivant = s;
    }

    public Maillon(T e) {
        this.e = e;
        suivant = null;
    }

    public Maillon<T> getSuivant() {
        return suivant;
    }

    public T getE() {
        return e;
    }

    public void setSuivant(Maillon<T> suivant) {
        this.suivant = suivant;
    }

    public String toString2(int nbOfTabs) {
        nbOfTabs++;
        if(suivant == null){
            return "Maillon{" +
                    "e=" + e.toString() + "}";
        }
        return "Maillon{" +
                "e=" + e.toString() +
                ","+ "\n" + "\t".repeat(nbOfTabs) + "suivant=" + suivant.toString2(nbOfTabs) +
                "\n"+"\t".repeat(nbOfTabs-1)+"}";
    }
}

public class SequenceList<T> implements Iterable<T> {
    private Maillon<T> tete;
    private Maillon<T> queue;

    public SequenceList() {
        queue = null;
    }

    public SequenceList(Maillon<T> t) {
        tete = t;
        queue = t;
    }

    public void ajoutTete(Maillon<T> t) {
        t.setSuivant(tete);
        tete = t;
        if(queue == null){
            queue = t;
        }
    }

    public void ajoutQueue(Maillon<T> q) {
        queue.setSuivant(q);
        queue = q;
    }

    @Override
    public String toString() {
        return "SequenceList{\n\t" +
                tete.toString2(1) +
                "\n}";
    }
    
    public Iterator<T> iterator() {
        return new SequenceListIterator();
    }

    class SequenceListIterator implements Iterator<T> {
        private Maillon<T> curr = tete;

        public boolean hasNext() {
            return curr != null;
        }

        public T next() {
            T e = curr.getE();
            curr = curr.getSuivant();
            return e;
        }
    }

    public static void main(String[] args) {
        SequenceList<Integer> s = new SequenceList<Integer>();

        Maillon<Integer> m1 = new Maillon<Integer>(1);
        Maillon<Integer> m2 = new Maillon<Integer>(2);
        Maillon<Integer> m3 = new Maillon<Integer>(3);
        Maillon<Integer> m4 = new Maillon<Integer>(4);
        Maillon<Integer> m5 = new Maillon<Integer>(5);
        Maillon<Integer> m6 = new Maillon<Integer>(6);
        Maillon<Integer> m7 = new Maillon<Integer>(7);
        Maillon<Integer> m8 = new Maillon<Integer>(8);
        Maillon<Integer> m9 = new Maillon<Integer>(9);
        Maillon<Integer> m10 = new Maillon<Integer>(10);
        Maillon<Integer> m11 = new Maillon<Integer>(11);

        s.ajoutTete(m2);
        s.ajoutTete(m1);
        s.ajoutQueue(m3);
        s.ajoutQueue(m4);
        s.ajoutQueue(m5);
        s.ajoutQueue(m6);
        s.ajoutQueue(m7);
        s.ajoutQueue(m8);
        s.ajoutQueue(m9);
        s.ajoutQueue(m10);
        s.ajoutQueue(m11);
/* 
        // Equivalent to this:
        Iterator<Integer> it = seq.iterator();
        while(it.hasNext()) {
            Integer i = it.next();
            System.out.println(i);
        }
*/
        for(Integer i : s) {
            System.out.println(i);
        }

        System.out.printf(s.toString());
    }
}
