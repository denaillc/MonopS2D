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

public abstract class Propriete extends Carreau {

	private Joueur proprietaire;
        private int prixAchat;
	private int prixLoyer;

    public Propriete(int numero, String nom, int loyer, int prix) {
        super(numero, nom);
        prixLoyer = loyer;
        prixAchat = prix;
        proprietaire = null;
    }
    
    

	

	public Joueur getProprietaire() {
		return this.proprietaire;
	}

	public void acheterPropriete(Joueur j) {
		j.setCash(j.getCash()-getPrixAchat());
                j.addPropriete(this);
                setProprietaire(j);
	}
        
        public abstract int calculLoyer (int valDes, int nb);

	public int getPrix() {
		return prixAchat;
	}

	public void setProprietaire(Joueur jCourant) {
		this.proprietaire = jCourant;
	}


    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    public int getPrixLoyer() {
        return prixLoyer;
    }

    public void setPrixLoyer(int prixLoyer) {
        this.prixLoyer = prixLoyer;
    }
        
        

}