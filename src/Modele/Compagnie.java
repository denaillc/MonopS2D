/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import UI.CouleurPropriete;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author albertar
 */
public class Compagnie extends Propriete {
    
    public Compagnie(int numero, String nom, int prix) {
        super(numero, nom, 4, prix);
    }
    
    
    @Override
    public int calculLoyer(int valDes, HashMap<CouleurPropriete,Groupe> groupes) {
        int r = 0;
        if (this.getProprietaire().getCompagnies().size() == 1) {
            r = valDes * 4;
        }
        else if (this.getProprietaire().getCompagnies().size() == 2) {
            r = valDes * 10;
        }
        return r;
    }
    
    @Override
   public void acheterPropriete(Joueur j) {
        j.setCash(j.getCash() - getPrixAchat());
        j.addPropriete(this);
        setProprietaire(j);
        j.addCompagnie(this);
   }
}
