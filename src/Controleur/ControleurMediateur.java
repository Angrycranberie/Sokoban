package Controleur;
import Modele.Jeu;
import Vue.CollecteurEvenements;
import Vue.InterfaceGraphique;

/*
 * Morpion pédagogique

 * Copyright (C) 2016 Guillaume Huard

 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).

 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.

 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.

 * Contact: Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */

public class ControleurMediateur implements CollecteurEvenements {
    Jeu jeu;
    InterfaceGraphique vue;

    public ControleurMediateur(Jeu j) {
        jeu = j;
    }

    @Override
    public void clicSouris(int l, int c) {
        if (jeu.deplaceVers(l, c) && jeu.estTermine())
            System.exit(0);
    }

    @Override
    public void toucheClavier(String touche) {
        switch (touche) {
            case "Left":
                jeu.deplace(0, -1);
                break;
            case "Right":
                jeu.deplace(0, 1);
                break;
            case "Up":
                jeu.deplace(-1, 0);
                break;
            case "Down":
                jeu.deplace(1, 0);
                break;
            case "Quit":
                System.exit(0);
                break;
            case "Full":
                vue.toggleFullscreen();
                break;
            default:
                System.out.println("Touche inconnue : " + touche);
        }
    }

    @Override
    public void ajouteInterfaceGraphique(InterfaceGraphique v) {
        vue = v;
    }
}
