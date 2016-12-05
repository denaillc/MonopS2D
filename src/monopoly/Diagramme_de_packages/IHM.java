package Diagramme_de_packages;

import Diagramme_de_classes_Monopoly.Controleur;

public class IHM {
	public Controleur _monopoly;
	public Controleur _controleur;
	Controleur monopoly;
	Controleur controleur;

	public void message(TypeMessage aType, String aNomProp, int aPrix) {
		throw new UnsupportedOperationException();
	}

	public void message(TypeMessage aType, String aNomProp, int aLoyer, int aCash) {
		throw new UnsupportedOperationException();
	}

	public void message(TypeMessage aType, String aNomProp) {
		throw new UnsupportedOperationException();
	}

	private void notifierObservateurs() {
		throw new UnsupportedOperationException();
	}
}