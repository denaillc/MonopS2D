/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import UI.CouleurPropriete;
import java.util.HashSet;
import java.util.Iterator;

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
    public int calculLoyer(int valDes, HashSet<Groupe> groupes) {
        
        int nb = 0;
        
        Iterator<Groupe> it = groupes.iterator();
        while (it.hasNext()) {
            Groupe g = it.next();
            if(this.getCouleur() == g.getCouleur()) {
                nb = g.getProprietes().size();
            }
        }
        
        
        if (this.getProprietaire().getNbProprieteCouleur(couleur) == nb) {
            return this.getPrixLoyer() * 2;
        }
        else return this.getPrixLoyer();
    }

    public CouleurPropriete getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurPropriete couleur) {
        this.couleur = couleur;
    }
    
    
    
}



