package Diagramme_de_classes_Monopoly;

public abstract class Propriete extends Carreau {

	private Joueur proprietaire;
	private int prixLoyer;

	/**
	 * 
	 * @param valDes
	 * @param nb
	 */
	public int calculLoyerCompagnie(int valDes, int nb) {
		// TODO - implement Propriete.calculLoyerCompagnie
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jCourant
	 * @param jProprio
	 * @param valDes
	 */
	public void payerLoyerCompagnie(Joueur jCourant, Joueur jProprio, int valDes) {
		// TODO - implement Propriete.payerLoyerCompagnie
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numC
	 * @param valDes
	 */
	public int calculPosition(int numC, int valDes) {
		// TODO - implement Propriete.calculPosition
		throw new UnsupportedOperationException();
	}

	public Joueur getProprietaire() {
		return this.proprietaire;
	}

	public int getPrix() {
		// TODO - implement Propriete.getPrix
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
	 * @param j
	 */
	public abstract void acheterPropriete(Joueur j);

}