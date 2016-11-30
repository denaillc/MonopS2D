/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author albertar
 */

public class Propriete extends Carreau {

	private Joueur proprietaire;
	private int prixLoyer;

    public Propriete(int numero, String nom) {
        super(numero, nom);
        prixLoyer = 0;
        proprietaire = null;
    }

	private int calculLoyer() {
            return 0;
	}

	public Joueur getProprietaire() {
		return this.proprietaire;
	}

	public void acheterPropriete(Joueur j) {
		
	}
        
        
	public void payerLoyer(Joueur jAch, Joueur JRec) {
		
	}

	public int calculPosition(int numC, int valDes) {
            return 0;
	}

	public int getPrix() {
		return 0;
	}

	public void setProprietaire(Joueur jCourant) {
		this.proprietaire = jCourant;
	}

	public int calculLoyer(int nb) {
		return 0;
	}

}