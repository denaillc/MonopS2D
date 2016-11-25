/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author albertar
 */

public class Gare extends Carreau {

	private Joueur proprietaire;
	private int prixLoyer;

    public Gare(int numero, String nom) {
        super(numero, nom);
    }

	private int calculLoyer() {
		// TODO - implement Gare.calculLoyer
		throw new UnsupportedOperationException();
	}

	public Joueur getProprietaire() {
		return this.proprietaire;
	}

	/**
	 * 
	 * @param j
	 */
	public void acheterPropriete(Joueur j) {
		// TODO - implement Gare.acheterPropriete
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jAch
	 * @param JRec
	 */
	public void payerLoyer(Joueur jAch, Joueur JRec) {
		// TODO - implement Gare.payerLoyer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numC
	 * @param valDes
	 */
	public int calculPosition(int numC, int valDes) {
		// TODO - implement Gare.calculPosition
		throw new UnsupportedOperationException();
	}

	public int getPrix() {
		// TODO - implement Gare.getPrix
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jCourant
	 */
	public void setProprietaire(Joueur jCourant) {
		this.proprietaire = jCourant;
	}

	/**
	 * 
	 * @param nb
	 */
	public int calculLoyer(int nb) {
		// TODO - implement Gare.calculLoyer
		throw new UnsupportedOperationException();
	}

}