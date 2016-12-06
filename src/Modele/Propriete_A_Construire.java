/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Diagramme_de_classes_Monopoly.CouleurPropriete;

/**
 *
 * @author albertar
 */
public class Propriete_A_Construire extends Propriete {
    
    private CouleurPropriete couleur;

    public Propriete_A_Construire(int numero, String nom, int loyer, int prix, String couleur) {
        super(numero, nom, loyer, prix);
        this.couleur = CouleurPropriete.valueOf(couleur);
    }

    @Override
    public int calculLoyer(int valDes, int nb) {
        //this.getProprietaire().getPropriete()
        if this.getProprietaire().getNbProprieteCouleur(couleur) == 
    }

    public CouleurPropriete getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurPropriete couleur) {
        this.couleur = couleur;
    }
    
    
    
}



