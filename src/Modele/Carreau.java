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
public class Carreau {
    private int numero;
    private String nom;

    public Carreau(int numero, String nom) {
        this.numero = numero;
        this.nom = nom;
    }

    
    public boolean estProp(){
        return false;
    }
    
    
    //////////////////////////////////////////////////////////////////////
    ///////////////////////////////////GETTEUR&SETTEUR////////////////////
    //////////////////////////////////////////////////////////////////////
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
