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
public class Compagnie extends Propriete {
    
    public Compagnie(int numero, String nom, int loyer, int prix) {
        super(numero, nom, loyer, prix);
    }

    @Override
    public int calculLoyer(int valDes, int nbCompagnies) {
        int r = 0;
        if (nbCompagnies == 1) {
            r = valDes * 4;
        }
        else if (nbCompagnies == 2) {
            r = valDes * 10;
        }
        return r;
    }
    
    
}
