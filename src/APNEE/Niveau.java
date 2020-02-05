package APNEE;

import java.security.PublicKey;
import java.util.Arrays;

public class Niveau{
	private String nom;
	private int lignes, colones;
	private char[][] tabNiv;


	public Niveau(int l, int c) {
		nom = "noName";
		lignes = l;
		colones = c;
		tabNiv = new char[l][c];
	}

	public void fixeNom(String s) {
		nom = s;
	}

	public void videCase(int i, int j) {
		tabNiv[i][j] = ' ';
	}

	public void ajouteMur(int i, int j) {
		tabNiv[i][j] = '#';
	}

	public void ajoutePousseur(int i, int j) {
		tabNiv[i][j] = '@';
	}

	public void ajouteCaisse(int i, int j) {
		tabNiv[i][j] = '$';
	}

	public void ajouteBut(int i, int j) {
		tabNiv[i][j] = '.';
	}

	public int lignes() {
		return lignes;
	}

	public int colones() {
		return colones;
	}

	public String nom() {
		return nom;
	}

	public boolean estVide(int i, int j) {
		return(tabNiv[i][j] == ' ');
	}

	public boolean aMur(int i, int j) {
		return(tabNiv[i][j] == '#');
	}

	public boolean aBut(int i, int j) {
		return(tabNiv[i][j] == '.');
	}

	public boolean aPousseur(int i, int j) {
		return(tabNiv[i][j] == '@');
	}

	public boolean aCaisse(int i, int j) {
		return(tabNiv[i][j] == '$');
	}

	public void affiche() {
		for(int i = 0; i< lignes; i++){
			for (int j = 0; j < colones; j++) {
				System.out.print(tabNiv[i][j]);
			}
			System.out.println();
		}

		System.out.println("; " + nom);
		System.out.println();
	}
}
