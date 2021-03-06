package Structures;

import Global.Configuration;

import java.io.FileNotFoundException;
import java.util.Random;

public class TestSequence {
    static int min, max, count;

    static int operation(Sequence<Integer> seq, int code, int pos) {
        int s = Integer.MIN_VALUE;
        System.out.println(seq);
        System.out.print("Affichage avec itérateur :");
        Iterateur<Integer> it = seq.iterateur();
        while (it.aProchain()) {
            System.out.print(" " + it.prochain());
        }
        System.out.println();
        switch (code) {
            case 0:
                s = min;
                System.out.println("Insertion en Tete de " + s);
                seq.insereTete(s);
                break;
            case 1:
                s = max;
                System.out.println("Insertion en Queue de " + s);
                seq.insereQueue(s);
                break;
            case 2:
                if (count > 0) {
                    it = seq.iterateur();
                    System.out.println("Extraction de l'élément de position " + pos);
                    while (pos > 0) {
                        assert (it.aProchain());
                        it.prochain();
                        pos--;
                    }
                    assert (it.aProchain());
                    s = it.prochain();
                    it.supprime();
                }
                break;
            case 3:
                if (count > 0) {
                    s = seq.extraitTete();
                    System.out.println("Extraction en Tete de " + s);
                    assert ((count == 1) == (seq.estVide()));
                }
                break;
        }
        if (code < 2) {
            assert (!seq.estVide());
        } else {
            if (count > 0) {
                assert ((s > min) && (s < max));
                assert ((count == 1) == (seq.estVide()));
            }
        }
        return s;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Random r = new Random();
        Sequence<Integer> s;
        s = Configuration.instance().nouvelleSequence();

        assert (s.estVide());
        min = -1;
        max = 0;
        count = 0;
        for (int i = 0; i < 100; i++) {
            int code = r.nextInt(4);
            int pos = (count > 0) ? r.nextInt(count) : -1;
            int r1 = operation(s, code, pos);
            if (code < 2) {
                count++;
                if (code < 1)
                    min--;
                else
                    max++;
            } else {
                if (count > 0) {
                    if ((code == 3) || ((code == 2) && (pos == 0)))
                        min = r1;
                    if ((code == 2) && (pos == count - 1))
                        max = r1;
                    if (min == max)
                        max++;
                    count--;
                }
            }
        }
    }
}