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
        private int prixAchat;
	private int prixLoyer;

    public Propriete(int numero, String nom, int loyer, int prix) {
        super(numero, nom);
        prixLoyer = loyer;
        prixAchat = prix;
        proprietaire = null;
    }

	private int calculLoyer() {
            return 0;
	}

	public Joueur getProprietaire() {
		return this.proprietaire;
	}

	public void acheterPropriete(Joueur j) {
		//TODO
	}
        
        
	public void payerLoyer(Joueur jAch, Joueur JRec) {
		//TODO
	}

	public int getPrix() {
		return prixAchat;
	}

	public void setProprietaire(Joueur jCourant) {
		this.proprietaire = jCourant;
	}

	public int calculLoyer(int nb) {
		return 0;
	}
        
        

}