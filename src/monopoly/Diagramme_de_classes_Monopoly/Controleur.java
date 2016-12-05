package Diagramme_de_classes_Monopoly;

import Diagramme_de_packages.IHM;
import java.util.ArrayList;
import Diagramme_de_classes_Monopoly.Joueur;

public class Controleur {
	public IHM _ihm;
	private Carreau _carreaux;
	private ArrayList<Joueur> _joueurs = new ArrayList<Joueur>();

	private Carreau avancer(Joueur aJ, int aNb) {
		throw new UnsupportedOperationException();
	}

	public void miseAJour(TypeCommande aType) {
		throw new UnsupportedOperationException();
	}

	public Joueur getJoueurCourant() {
		throw new UnsupportedOperationException();
	}
}