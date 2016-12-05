import java.util.*;
import Diagramme_de_classes_Monopoly.*;

public class Joueur {

	private Collection<Gare> gares;
	private Carreau positionCourante;
	Collection<Propriete> proprietes;
	private String nomJoueur;
	private int cash = 1500;

	/**
	 * 
	 * @param l
	 */
	public void payerLoyer(int l) {
		// TODO - implement Joueur.payerLoyer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param l
	 */
	public void recevoirLoyer(int l) {
		// TODO - implement Joueur.recevoirLoyer
		throw new UnsupportedOperationException();
	}

	public int getCash() {
		return this.cash;
	}

	public Carreau getPositionCourante() {
		return this.positionCourante;
	}

	/**
	 * 
	 * @param numC
	 */
	public void setPositionCourante(Carreau numC) {
		this.positionCourante = numC;
	}

	/**
	 * 
	 * @param cash
	 * @param prix
	 */
	public void setCash(int cash, int prix) {
		// TODO - implement Joueur.setCash
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 */
	public void addCompagnie(Compagnie c) {
		// TODO - implement Joueur.addCompagnie
		throw new UnsupportedOperationException();
	}

	public int getNbGare() {
		// TODO - implement Joueur.getNbGare
		throw new UnsupportedOperationException();
	}

	public int getNbCompagnie() {
		// TODO - implement Joueur.getNbCompagnie
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 */
	public void addProprieteAConstruire(ProprieteAConstruire c) {
		// TODO - implement Joueur.addProprieteAConstruire
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 */
	public void addGare(Gare c) {
		// TODO - implement Joueur.addGare
		throw new UnsupportedOperationException();
	}

	public int getNbProprieteCouleur() {
		// TODO - implement Joueur.getNbProprieteCouleur
		throw new UnsupportedOperationException();
	}

	public int getNbGares() {
		// TODO - implement Joueur.getNbGares
		throw new UnsupportedOperationException();
	}

}