/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import UI.CouleurPropriete;
import java.util.HashMap;

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
    public int calculLoyer(int valDes, HashMap<CouleurPropriete, Groupe> groupes) {
        
        int nb = 0;
        
        for (Groupe gr : groupes.values()) {                                                                                    //Pour chaque valeur dans la Map groupes
            if (this.getCouleur() == gr.getCouleur()) {                                                                         //Si la couleur correspond
                nb = gr.getProprietes().size();                                                                                 //On compte le nombre de couleur
            }
        }
                
        if (this.getProprietaire().getNbProprieteCouleur(couleur) == nb) {                                                      //Si on possede tous les loyers de la meme couleurs
            return this.getPrixLoyer() * 2;
        }
        else return this.getPrixLoyer();
    }

    @Override
   public void acheterPropriete(Joueur j) {
        j.setCash(j.getCash() - getPrixAchat());
        j.addPropriete(this);
        setProprietaire(j);
        j.addProprieteAConstruire(this);
   }
    
   
   
   
   
   
   
    //////////////////////////////////////////////////////////////////////
    ///////////////////////////////////GETTEUR&SETTEUR////////////////////
    //////////////////////////////////////////////////////////////////////
    public CouleurPropriete getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurPropriete couleur) {
        this.couleur = couleur;
    }
    
    
    
}



