/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.HashSet;

/**
 *
 * @author albertar
 */
public class Gare extends Propriete {
    
    public Gare(int numero, String nom, int loyer, int prix) {
        super(numero, nom, loyer, prix);
    }

    @Override
    public int calculLoyer(int valDes, HashSet<Groupe> groupes) {
        return this.getPrixLoyer() * this.getProprietaire().getGares().size();
    }
    
}
