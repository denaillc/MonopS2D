package Diagramme_de_classes_Monopoly;

import java.util.ArrayList;
import Diagramme_de_classes_Monopoly.Gare;

public class Joueur {
	private String _nomJoueur;
	private int _cash = 1500;
	private ArrayList<Gare> _gares = new ArrayList<Gare>();
	private Carreau _positionCourante;

	public void payerLoyer(int aL) {
		throw new UnsupportedOperationException();
	}

	public void recevoirLoyer(int aL) {
		throw new UnsupportedOperationException();
	}

	public int getCash() {
		return this._cash;
	}

	public int getNbGares() {
		throw new UnsupportedOperationException();
	}
}