package Diagramme_de_classes_Monopoly;

public class Gare extends Carreau {
	private Joueur _proprietaire;

	private int calculLoyer() {
		throw new UnsupportedOperationException();
	}

	public Joueur getProprietaire() {
		return this._proprietaire;
	}

	public void acheterPropriete(Joueur aJ) {
		throw new UnsupportedOperationException();
	}

	public void payerLoyer(Joueur aJAch, Joueur aJRec) {
		throw new UnsupportedOperationException();
	}
}